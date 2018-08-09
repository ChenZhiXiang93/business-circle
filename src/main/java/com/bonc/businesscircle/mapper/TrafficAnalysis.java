package com.bonc.businesscircle.mapper;

import com.bonc.businesscircle.pojo.DayTraffic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TrafficAnalysis {

    /*******查询实时客流量********/
    @Select({"<script> " +
            "SELECT BUS_CIR_NUM FROM DM_OSS_NES_I_BUS_CIR_REAL_H WHERE 1=1 " +
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
    Object getRealTraffic(@Param("uuid") String uuid, @Param("date") String date);

    /*******查询当日客流量,当日回头客,当日新客********/
    @Select("select STAT_DATE as statDate,BUS_CIR_NUM as busCirNum,BUS_CIR_H_NUM as busCirHNum,BUS_CIR_N_NUM as busCirNNum from " +
            "DM_OSS_NES_I_BUS_CIR_DAY where uuid = #{uuid} and STAT_DATE > #{startDate} and STAT_DATE < #{endDate}")
    List<DayTraffic> getTodayTraffic(@Param("uuid") String uuid, @Param("startDate") String startDate,@Param("endDate") String endDate);
}
