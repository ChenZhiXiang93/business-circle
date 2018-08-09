package com.bonc.businesscircle.model;

import javafx.scene.input.DataFormat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public class dd {

    public static void main(String[] args) throws ParseException {

      /*  DateBefore.stampToDate(DateBefore.dateToStamp("20180726"));
        System.out.println(DateBefore.dateAfter(DateBefore.stampToDate(DateBefore.dateToStamp("20180712")),-30));
        *//*DateBefore.getBetweenDates(DateBefore.stampToDate(DateBefore.dateToStamp("20180712")),-30),DateBefore.dateToStamp("20180726"));*//*

        System.out.println(DateBefore.ago("20180712"));
        System.out.println("s"+DateBefore.getYestoday());*/


        int a=1099;
        int b=93;
        double f1 = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("ddd==="+f1);
    }

}
