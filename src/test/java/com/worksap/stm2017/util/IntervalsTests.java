package com.worksap.stm2017.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalsTests {
    public static void main(String[] args) {
        String a = "06:00~07:00";
        String b = "06:20~06:40";
        String c = "06:30~07:30";
        String d = "08:00~09:20";
        String e = "08:50~10:10";

        List<String> merged = new ArrayList<>();

//        merged.add(d);
        merged.add(a);
//        merged.add(e);
        merged.add(c);
        merged = Intervals.mergeList(merged);

        System.out.println(Intervals.merge(a,b).equals("06:00~07:00"));
        System.out.println(Intervals.merge(a,c).equals("06:00~07:30"));
        System.out.println(merged);
        String[] tmp = {"9:30~10:30"};
        List<String> tmplist = Arrays.asList(tmp);
        Intervals.formatList(tmplist);
        System.out.println(tmplist);

    }
}
