package com.english.listening;

import com.english.listening.pojo.ClassInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class Tests {

    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(1);
        ClassInfo classInfo1 = new ClassInfo();
        classInfo1.setClassId(2);
        ClassInfo classInfo2 = new ClassInfo();
        classInfo2.setClassId(3);
////
////        List<ClassInfo> taskList = new ArrayList<>();
////        taskList.add(classInfo2);
////        taskList.add(classInfo1);
////        taskList.add(classInfo);
////        CompletableFuture[] completableFutures =taskList.stream().
////                map(integer -> CompletableFuture.supplyAsync(()-> integer.getClassId()).thenApply(h->h.hashCode()).whenComplete((integer1, throwable) -> {
////
////                    System.out.println(integer1);
////                })).toArray(CompletableFuture[]::new);
////
////        System.out.println(taskList);

        List<ClassInfo> list = new ArrayList<>();
        list.add(classInfo);
        list.add(classInfo1);
        list.add(classInfo2);


        list.stream().filter(num -> num.getClassId()>1).forEach(e-> System.out.println(e.getClassId()));





    }

    public static void test4() throws Exception {

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        });

        CompletableFuture<String> completableFuture2=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task2 doing...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result2";
        });

        CompletableFuture<Object> anyResult=CompletableFuture.anyOf(completableFuture1,completableFuture2);

        System.out.println("第一个完成的任务结果:"+anyResult.get());

        CompletableFuture<Void> allResult=CompletableFuture.allOf(completableFuture1,completableFuture2);

        //阻塞等待所有任务执行完成
        allResult.join();
        System.out.println("所有任务执行完成");

    }
}
