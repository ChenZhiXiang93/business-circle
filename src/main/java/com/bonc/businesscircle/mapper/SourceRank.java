package com.bonc.businesscircle.mapper;

import com.bonc.businesscircle.pojo.RankTop;
import com.bonc.businesscircle.pojo.SourceStatistical;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SourceRank {

    /*******统计当月到访商圈的所有用户的居住常驻地，并按用户量排名********/
    @Select("select REST_WORK_AREA as restWorkArea,BUS_CIR_H_NUM as busCirHNum from DM_OSS_NES_I_BUS_CIR_COME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date} and REST_WORK_TYPE = '2' GROUP BY restWorkArea ORDER BY busCirHNum DESC")
    List<SourceStatistical> getSourceRank(@Param("uuid") String uuid, @Param("date") String date);

    /*******查询常驻地排行********//*
    @Select("select REST_WORK_AREA as restWorkArea,BUS_CIR_H_NUM as busCirHNum from DM_OSS_NES_I_BUS_CIR_COME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date} and REST_WORK_TYPE = '2' ORDER BY busCirHNum DESC limit 5")
    List<SourceStatistical> getLiveRank(@Param("uuid") String uuid, @Param("date") String date);*/

    @Select("select a.REST_WORK_AREA name,a.BUS_CIR_H_NUM currentMoth,b.BUS_CIR_H_NUM beforeMoth,tnum as currnetRank,ynum as beforeRank," +
            "(b.ynum-a.tnum) as changeNum from (select REST_WORK_AREA,BUS_CIR_H_NUM,(@row_number:=@row_number + 1) " +
            " as tnum from dm_oss_nes_i_bus_cir_come_mon,(SELECT @row_number:=0) AS t  where STAT_MON= #{currentDate} " +
            "and uuid = #{uuid} and REST_WORK_TYPE = #{type} order by BUS_CIR_H_NUM desc limit 5 ) a left join " +
            "(select *,(@customer:=@customer + 1) as ynum from dm_oss_nes_i_bus_cir_come_mon,(SELECT @customer:=0)" +
            " AS tt  where STAT_MON= #{beforeMonth} and uuid = #{uuid} and REST_WORK_TYPE = #{type} order by BUS_CIR_H_NUM desc" +
            ") b on a.REST_WORK_AREA=b.REST_WORK_AREA")
    List<RankTop> getLiveRank(@Param("uuid") String uuid, @Param("currentDate") String currentDate,@Param("type") String type,@Param("beforeMonth") String beforeMonth);

    /*******查询工作常驻地排行********//*
    @Select("select REST_WORK_AREA as restWorkArea,BUS_CIR_H_NUM as busCirHNum from DM_OSS_NES_I_BUS_CIR_COME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date} and REST_WORK_TYPE = '1'  ORDER BY busCirHNum DESC limit 5")
    List<SourceStatistical> getWorkRank(@Param("uuid") String uuid, @Param("date") String date);

    *//******根据地址查询到访人数 工作类型********//*
    @Select("select BUS_CIR_H_NUM as busCirHNum from DM_OSS_NES_I_BUS_CIR_COME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date} and REST_WORK_AREA = #{name} and REST_WORK_TYPE = '1'")
    Integer getSourcesWork(@Param("uuid") String uuid,@Param("name") String name,@Param("date") String date);

    *//******根据地址查询到访人数 居住类型********//*
    @Select("select BUS_CIR_H_NUM as busCirHNum from DM_OSS_NES_I_BUS_CIR_COME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date} and REST_WORK_AREA = #{name} and REST_WORK_TYPE = '2'")
    Integer getSourcesLive(@Param("uuid") String uuid,@Param("name") String name,@Param("date") String date);*/
}
