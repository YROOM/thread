package com.yroom.study;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author YROOM
 * @date 2022/5/3 12:01
 */
public class Shaobing {
    public static void main(String[] args) {
        Queue<String> shaoBingQueue = new LinkedList<>();
        List<String> xiaoBaiMsg = new LinkedList<>();
        List<String> roadPeopleMsg = new LinkedList<>();

        Thread xiaoBai = new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {
                String tmp = String.format("第%d个烧饼" , i+1);
                shaoBingQueue.add(tmp);
                xiaoBaiMsg.add(String.format("%d小白制作了[%s]" , System.currentTimeMillis() , tmp));

            }
        });


        Thread roadPeople = new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {
                roadPeopleMsg.add(String.format("%d路人甲购买了[%s]" , System.currentTimeMillis() , shaoBingQueue.poll()));
            }
        });

        xiaoBai.start();
        roadPeople.start();


        try{
            xiaoBai.join();
            roadPeople.join();
        }catch (Exception e){
            SmallTool.printTimeAndThread("join error"+e.getMessage());
        }

        System.out.println(xiaoBaiMsg.stream().collect(Collectors.joining("\n")));
        System.out.println("------------------");
        System.out.println(roadPeopleMsg.stream().collect(Collectors.joining("\n")));
    }

}
