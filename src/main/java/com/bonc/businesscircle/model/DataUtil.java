package com.bonc.businesscircle.model;

import java.math.BigDecimal;

/**
 * @ClassName DataUtil
 * @Description 两个整数相除
 * @Author chenzhixiang
 * @Date 2018/8/317:25
 * @Version 1.0
 * @return 具有两位小数的数据
 **/
public class DataUtil {

    public static String divide(String a,String b){
        //先将String类型的数据转为Integer
        int c = Integer.parseInt(a);
        int d = Integer.parseInt(b);
        //除数为0时对数据处理
        if (d == 0){
            c = 0;
            d = 1;
        }
        double f1 = new BigDecimal((float)c/d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String f2 = String.valueOf(f1);
        return f2;
    }
}
