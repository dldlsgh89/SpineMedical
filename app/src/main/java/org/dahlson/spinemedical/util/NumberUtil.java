package org.dahlson.spinemedical.util;

import java.util.Calendar;

public class NumberUtil {

    public static String smallerThanTen(int num){
        String returnNum = num < 10
                ? "0" + String.valueOf(num) : String.valueOf(num);
        return returnNum;
    }

}
