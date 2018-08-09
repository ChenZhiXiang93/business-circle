package com.bonc.businesscircle.util;

import java.util.Random;
import java.util.UUID;

public class SequenceTool {
	private static final String LoanOrder_Lock_Key_prefix = "cfmLLock-";
    public static String getNewId(Integer id, String prefix) {
        String newId = prefix;
        int curLen = id.toString().length();
        if (curLen < 11) {
            for (int i = 1; i <= (11 - curLen); i++) {
                newId += "0";
            }
        }
        newId += id;
        return newId;
    }

    public static String getNewValue() {
        long now = System.currentTimeMillis();
        return SequenceTool.getRandomString(3) + now;
    }

    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomNumber(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getNewOrderNo() {
        long now = System.currentTimeMillis();
        return SequenceTool.getRandomString(12) + now;
    }
    
    public static String getUUID(){
    	UUID uuid = UUID.randomUUID();
    	return uuid.toString().replace("-", "");
    }
    
    public static String getLoanOrderLockKey(String loanOrderClientUuid){
    	return LoanOrder_Lock_Key_prefix + loanOrderClientUuid;
    }
    /**
     * 判断null或者 空字符串
     *
     * @param obj
     *
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null || obj.toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 名字替换为姓的第一个字+N个*
     *
     * @param name
     *
     * @return boolean
     */
    public static String getStarName(String name) {
        String res = name.substring(0,1);
        for (int i= 0 ;i<name.length()-1;i++){
            res = res + "*";
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(SequenceTool.getNewValue());
        System.out.println(getUUID());
    }

}
