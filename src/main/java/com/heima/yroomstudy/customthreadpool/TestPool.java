package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author YROOM
 * @date 2022/8/21 20:49
 */
@Slf4j(topic = "c.testpool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10);
        for (int i = 0; i < 15; i++) {
            int j = i;

            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", "任务" + j);
            });

        }

    }

}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {


    private BlockingQueue<Runnable> taskQueue;
    /**
     * 线程集合
     */
    private Set<Worker> workers = new HashSet();


    /**
     * 核心线程数
     */
    private int coreSize;

    /**
     * 获取任务的超时时间
     */
    private long timeOut;

    private TimeUnit timeUnit;


    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker{},{}", worker, task);
                worker.start();
                workers.add(worker);
            } else {

                taskQueue.put(task);
            }
        }

    }


    public ThreadPool(int coreSize, long timeOut, TimeUnit timeUnit, int queueCapcity) {
        this.coreSize = coreSize;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            while (task != null || (task = taskQueue.poll(timeOut, timeUnit)) != null) {
                try {
                    log.debug("正在执行{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker removed{}", this);
                workers.remove(this);
            }

        }
    }
}

@Slf4j(topic = "c.Blocking")
class BlockingQueue<T> {
    private Deque<T> deque = new ArrayDeque<>();


    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWaitSet = lock.newCondition();

    private Condition emptyWaitSet = lock.newCondition();

    private int capcity;


    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     * 带超时的阻塞获取
     *
     * @return
     */

    public T poll(long timeOut, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeOut);
            while (deque.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }

                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fullWaitSet.signal();
            return deque.removeFirst();
        } finally {
            lock.unlock();
        }
    }


    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fullWaitSet.signal();
            return deque.removeFirst();
        } finally {
            lock.unlock();
        }
    }


    public void put(T task) {
        lock.lock();
        try {
            while (deque.size() == capcity) {
                try {
                    log.debug("等待加入任务队列{}...", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列{}", task);
            deque.addLast(task);

            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }

    }


    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (deque.size() == capcity) {
                try {
                    log.debug("等待加入任务队列{}...", task);
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列{}", task);
            deque.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }

    }


    public int size() {
        lock.unlock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }
    
    
    
    
    
}
