package com.lei.jvm.stu.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author leihaoyuan
 * @Date 2021/5/11 18:26
 * @Version 1.0
 * @Description
 */
@Slf4j
public class LinkedListTest {

    public static void main(String[] args) {
        int elementCount = 100000;
        int loopCount = 100000;

        StopWatch stopWatch =StopWatch.createStarted();

        linkedListGet(elementCount,loopCount);
        stopWatch.split();
        log.info("linkedListGet：{}",stopWatch.getSplitTime());

        arrayListGet(elementCount,loopCount);
        stopWatch.split();
        log.info("arrayListGet：{}",stopWatch.getSplitTime());

        linkedListAdd(elementCount,loopCount);
        stopWatch.split();
        log.info("linkedListAdd：{}",stopWatch.getSplitTime());

        arrayListAdd(elementCount,loopCount);
        stopWatch.split();
        log.info("arrayListAdd：{}",stopWatch.getSplitTime());

        stopWatch.stop();


    }


    /**
     * linkedList访问
     * @param elementCount
     * @param loopCount
     */
    private static void linkedListGet(int elementCount,int loopCount){
        List<Integer> list = IntStream.rangeClosed(1,elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1,loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(i)));
    }

    /**
     * arrayList访问
     * @param elementCount
     * @param loopCount
     */
    private static void arrayListGet(int elementCount,int loopCount){
        List<Integer> list = IntStream.rangeClosed(1,elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1,loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(i)));
    }

    /**
     * linedList新增
     * @param elementCount
     * @param loopCount
     */
    private static void linkedListAdd(int elementCount,int loopCount){
        List<Integer> list = IntStream.rangeClosed(1,elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1,loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(i)));
    }

    /**
     * arrayList新增
     * @param elementCount
     * @param loopCount
     */
    private static void arrayListAdd(int elementCount,int loopCount){
        List<Integer> list = IntStream.rangeClosed(1,elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1,loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(i)));
    }







}
