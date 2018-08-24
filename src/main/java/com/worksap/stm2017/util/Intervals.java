package com.worksap.stm2017.util;

import java.text.ParseException;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class Intervals {
    public static boolean validate(final String intervals) {
        String[] intervalList = intervals.split(";");

        for(String interval: intervalList) {
            String[] hms_str = interval.split("[:~ ]");
            Integer[] hms = new Integer[4];

            if(hms_str.length != 4)
                return false;

            for(int i = 0; i < 4; i++) {
                try {
                    hms[i] = Integer.parseInt(hms_str[i]);
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            if(!valid_h(hms[0]) || !valid_h(hms[2]) || !valid_m(hms[1])
                    || !valid_m(hms[3]) || (hms[0]*100+hms[1] > hms[2]*100+hms[3])
            )
                return false;
        }

        return true;
    }

    public static String merge(String interval_a, String interval_b) {
        List<String> part_a = new ArrayList<>(Arrays.asList(interval_a.split("~")));
        List<String> part_b = new ArrayList<>(Arrays.asList(interval_b.split("~")));

        part_a.addAll(part_b);
        Collections.sort(part_a);

        return part_a.get(0) + "~" + part_a.get(3);
    }

    public static String format(String interval) {
        String[] part = interval.split("[~: ]");
        return String.format("%02d:%02d~%02d:%02d", Integer.parseInt(part[0]), Integer.parseInt(part[1]),
                Integer.parseInt(part[2]), Integer.parseInt(part[3]));
    }

    public static void formatList(List<String> slots) {
        for(int i = 0; i < slots.size(); i++) {
            String tmp = slots.get(i);
            slots.set(i, format(tmp));
        }
    }

    public static List<String> mergeList(final List<String> slots) {
        List<String> sorted = new ArrayList<>(slots);
        List<String> res = new ArrayList<>();
        int pos = 0;

        formatList(sorted);
        Collections.sort(sorted);
        res.add(sorted.get(0));

        for(int i = 1; i< sorted.size(); i++) {
            String s = sorted.get(i);
            if(isOverlap(res.get(pos), s)) {
                res.set(pos, merge(res.get(pos), s));
            }
            else {
                res.add(s);
                pos++;
            }
        }

        return res;
    }

    public static boolean isOverlap(final String interval_a, final String interval_b) {
        String[] part_a = interval_a.split("[~: ]");
        String[] part_b = interval_b.split("[~: ]");

        return !((Integer.parseInt(part_a[0])*100 + Integer.parseInt(part_a[1]) >
                Integer.parseInt(part_b[2])*100 + Integer.parseInt(part_b[3])) ||
                (Integer.parseInt(part_a[2])*100 + Integer.parseInt(part_a[3]) <
                Integer.parseInt(part_b[0])*100 + Integer.parseInt(part_b[1])));
    }

    public static boolean isAinB(final String interval_a, final String interval_b) {
        String[] part_a = interval_a.split("[~: ]");
        String[] part_b = interval_b.split("[~: ]");

        return (Integer.parseInt(part_a[0])*100 + Integer.parseInt(part_a[1]) >=
                Integer.parseInt(part_b[0])*100 + Integer.parseInt(part_b[1])) &&
                (Integer.parseInt(part_a[2])*100 + Integer.parseInt(part_a[3]) <=
                Integer.parseInt(part_b[2])*100 + Integer.parseInt(part_b[3]));
    }

    public static int interval_m(final String itvl) {
        String[] part = itvl.split("[~: ]");

        return Integer.parseInt(part[2]) * 60 + Integer.parseInt(part[3]) - Integer.parseInt(part[0]) * 60 - Integer.parseInt(part[1]);
    }

    private static boolean valid_h(int h) {
        return h < 24 && h > -1;
    }

    private static boolean valid_m(int m) {
        return m < 60 && m > -1;
    }
}
