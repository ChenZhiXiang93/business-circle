package com.bonc.businesscircle.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 根据当前日期得到上个月的日期
 * 201808 --> 201807
 */
public class DateFormat {
    public static String getTime(String time) {
        int i = Integer.parseInt(time.substring(0,4));
        int j = Integer.parseInt(time.substring(4));
        if (j<2) {
            i--;
            j=12;
            String dat = String.valueOf(i)+j;
            return dat;
        }else {
            j = j-1;
            if (j<10){
                String s =0+String.valueOf(j);
                String dat = String.valueOf(i)+s;
                return dat;
            }else {
                String dat = String.valueOf(i)+j;
                return dat;
            }
        }
    }
}
