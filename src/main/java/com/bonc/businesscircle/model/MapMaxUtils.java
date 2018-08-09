package com.bonc.businesscircle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MapMaxUtils
 * @Description TODO 取Map中value最大值的key
 * @Author chenzhixiang
 * @Date 2018/7/3117:34
 * @Version 1.0
 **/
public class MapMaxUtils {
    public static String getMaxKey(Map<String,Integer> map){
        List<Map.Entry<String,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        return list.get(list.size()-1).getKey();
    }
}
