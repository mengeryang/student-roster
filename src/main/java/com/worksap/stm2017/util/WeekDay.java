package com.worksap.stm2017.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekDay {
    final static public String[] WEEKDAYS = {"0","1","2","3","4","5","6"};
    final static public Map<String, String> WeekMap() {
        Map<String, String> res = new HashMap<>();
        res.put("1", "Mon");
        res.put("2", "Tue");
        res.put("3", "Wed");
        res.put("4", "Thu");
        res.put("5", "Fri");
        res.put("6", "Sat");
        res.put("0", "Sun");
        return res;
    }
    final static public List<String> WeekList() {
        return Arrays.asList(WEEKDAYS);
    }
}
