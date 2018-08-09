package com.bonc.businesscircle.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Consumption {

    /*******商圈访客的消费能力及消费倾向********/
    @Select("select CONSUME_LOW_NUM as consumeLowNum,CONSUME_MED_LOW_NUM as consumeMedLowNum,CONSUME_MED_HIGH_NUM as consumeMedHighNum," +
            " CONSUME_HIGH_NUM as consumeHighNum,CONSTEND_LOW_NUM as constendLowNum,CONSTEND_MED_LOW_NUM as constendMedLowNum,CONSTEND_MED_HIGH_NUM as constendMedHighNum," +
            " CONSTEND_HIGH_NUM as constendHighNum,FINANCE_NUM as financeNum,ENT_NUM as entNum,INFORMATION_NUM as information,BUS_CUS_NUM as busCusNum from DM_OSS_NES_I_BUS_CIR_CONSUME_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date}")
    List<com.bonc.businesscircle.pojo.Consumption> getConsumptionNum(@Param("uuid") String uuid, @Param("date") String date);
}
