package com.bonc.businesscircle.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Crossroads {

    /*******路口人流量统计********/
    /*@Select("select CROSSING_NUM as C_Num from DM_OSS_NES_I_BUS_CIR_REAL_H" +
            " where uuid = #{uuid} and STAT_HOUR = #{date}")
    Object getCrossroadsNum(@Param("uuid") String uuid, @Param("date") String date);*/

    @Select({"<script> " +
            "SELECT CROSSING_NUM as C_Num FROM DM_OSS_NES_I_BUS_CIR_REAL_H WHERE 1=1 " +
            "<if test='uuid!=null'>" +
            "AND uuid = #{uuid}" +
            "</if>" +
            "<if test='date!=null'>" +
            "AND STAT_HOUR = #{date}" +
            "</if>" +
            "<if test='date==null'>" +
            "AND STAT_HOUR = (select max(STAT_HOUR) from DM_OSS_NES_I_BUS_CIR_REAL_H)" +
            "</if>" +
            "</script>"})
    Object getCrossroadsNum(@Param("uuid") String uuid, @Param("date") String date);
}
