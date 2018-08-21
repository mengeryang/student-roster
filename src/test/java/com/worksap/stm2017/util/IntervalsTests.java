package com.worksap.stm2017.util;

public class IntervalsTests {
    public static void main(String[] args) {
        String a = "06:00~07:00";
        String b = "06:20~06:40";
        String c = "06:30~07:30";

        System.out.println(Intervals.merge(a,b).equals("06:00~07:00"));
        System.out.println(Intervals.merge(a,c).equals("06:00~07:30"));

    }
}
