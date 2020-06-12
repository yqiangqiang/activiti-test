package com.yqq;

/**
 * @author 17993
 * @date 2020/6/11 14:34
 */
public class RecurrenceTest {

    public static int i=0;

    public static void main(String[] args) {
        new RecurrenceTest().method1();
    }

    public void method1() {
        i = i+1;
        System.out.println("第" + i + "次递归");
        if (i < 5) {
            method1();
        }else {
            return;
        }

    }

}
