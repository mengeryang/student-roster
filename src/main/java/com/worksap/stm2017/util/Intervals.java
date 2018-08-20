package com.worksap.stm2017.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Intervals {
    public static boolean validate(final String intervals) {
        String[] intervalList = intervals.split(";");

        for(String interval: intervalList) {
            String[] hms_str = interval.split("[:/]");
            Integer[] hms = new Integer[4];

            if(hms_str.length != 4)
                return false;

            for(int i = 0; i < 4; i++) {
                try {
                    hms[i] = Integer.parseInt(hms_str[i]);
                } catch (PatternSyntaxException e) {
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

    public static String merge(String intervals) {
        //TODO

        return intervals;
    }

    public static boolean isOverlap(final String interval_a, final String interval_b) {
        String[] part_a = interval_a.split("[/:]");
        String[] part_b = interval_b.split("[/:]");

        return !((Integer.parseInt(part_a[0])*100 + Integer.parseInt(part_a[1]) >
                Integer.parseInt(part_b[2])*100 + Integer.parseInt(part_b[3])) ||
                (Integer.parseInt(part_a[2])*100 + Integer.parseInt(part_a[3]) <
                Integer.parseInt(part_b[0])*100 + Integer.parseInt(part_b[1])));
    }

    public static boolean isAinB(final String interval_a, final String interval_b) {
        String[] part_a = interval_a.split("[/:]");
        String[] part_b = interval_b.split("[/:]");

        return (Integer.parseInt(part_a[0])*100 + Integer.parseInt(part_a[1]) >=
                Integer.parseInt(part_b[0])*100 + Integer.parseInt(part_b[1])) &&
                (Integer.parseInt(part_a[2])*100 + Integer.parseInt(part_a[3]) <=
                Integer.parseInt(part_b[2])*100 + Integer.parseInt(part_b[3]));
    }

    private static boolean valid_h(int h) {
        return h < 24 && h > -1;
    }

    private static boolean valid_m(int m) {
        return m < 60 && m > -1;
    }
}
