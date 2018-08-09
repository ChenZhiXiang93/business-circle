package com.bonc.businesscircle.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JokerJodas
 */
public class DateUitl {
    /**
     * 将时间戳转化成1990-03-23的形式
     *
     * @param time
     * @return boolean
     */
    public static String turnDate(long time) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static void main(String[] args) throws Exception {

        System.out.println("\'"+1651561 + "\'," + "\'"+1651561 + "\',");

    }
}
