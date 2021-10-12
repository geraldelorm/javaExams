package com.turntabl;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("01", 91);
        System.out.println(map);
        if(map.containsKey("02")){
            int exi = map.get("01");
            map.put("01", exi + 2);
        } else {
            map.put("02", 5000);
        }
        System.out.println(map);
        System.out.println(map.values().stream().mapToInt(v -> v).sum());
        System.out.println(map.values().stream().reduce(0, (total, val) -> total + val));
        System.out.println(map.values().stream().reduce(0, Integer::sum));

        //Products products = new Stocks("0001", "EXE", "APPL", 500);
    }
}
