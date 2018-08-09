package com.bonc.businesscircle.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateBefore {

    /**
     * 在原有的日期上往后推，计算得出新的日期.
     *
     * @param billingDate 日期
     * @param days 天数
     * @return 计算后的日期
     */
    public static java.sql.Date dateAfter(Date billingDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(billingDate);
        cal.add(Calendar.DATE, days);
        java.sql.Date date = new java.sql.Date(cal.getTime().getTime());
        return date;
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s){
        String res;
        long lt = new Long(s);
        Date date = new Date(lt);
        System.out.println("date"+date);
        return date;
    }

    /**
     * 获取两个日期之间的日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期集合
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     *@Author chenzhixiang
     *@Description //TODO
     *@Date 22:03 2018/7/31
     *@Param 当前日期
     *@return 向前推30天的日期
    **/
    public static String  ago(String str){
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd");
        Date date=null;
        try {
            date = format.parse(str);
            long ago_30=getDateAddDays(date,-30).getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ago_30);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Date getDateAddDays(Date date, int add_days) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(5, add_days);
        return time.getTime();
    }

    /**
     * 获取上一个月
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     *@Author chenzhixiang
     *@Description //获取昨天的日期
     *@Date 15:48 2018/8/1
     *@Param
     *@return 20180801-->20180731
    **/
    public static String getYestoday(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        String date = new SimpleDateFormat("yyyyMMdd").format(time);
        return date;
    }

    /**
     *@Author chenzhixiang
     *@Description 获取当前日期
     *@Date 16:14 2018/8/1
     *@Param
     *@return
    **/
    public static String GetNowDate(){
        String temp_str="";
        Date dt = new Date();
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        temp_str=sdf.format(dt);
        return temp_str;
    }
}
