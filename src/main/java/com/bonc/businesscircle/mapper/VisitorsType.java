package com.bonc.businesscircle.mapper;

import com.bonc.businesscircle.pojo.Consumption;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VisitorsType {

    /*******展示该商圈访客的分析标签。分类为购物狂、电影发烧友、美食达人三类********/
    @Select("select SHOPPING_NUM as shoppingNum,FOOD_NUM as foodNum,FILM_NUM as filmNum" +
            " from DM_OSS_NES_I_BUS_CIR_CONSUME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date}")
    List<Consumption> getVisitorsTypeNum(@Param("uuid") String uuid, @Param("date") String date);
}
