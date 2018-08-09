package com.bonc.businesscircle.mapper;

import com.bonc.businesscircle.pojo.LayerTime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResidentTime {

    /*******圈区域内的驻留时长********/
    @Select("select AVG_HOUR as perCapita from dm_oss_nes_i_bus_cir_day where uuid = #{uuid} and STAT_DATE = #{date}")
    Object getResidentTime(@Param("uuid") String uuid,@Param("date") String date);

    /*******圈区域内每层的驻留时长人数********/
    @Select("select AVG_LAYER_HOUR_FF1,AVG_LAYER_NUM_FF1,AVG_LAYER_HOUR_F1,AVG_LAYER_NUM_F1,AVG_LAYER_HOUR_F2,AVG_LAYER_NUM_F2," +
            " AVG_LAYER_HOUR_F3,AVG_LAYER_NUM_F3,AVG_LAYER_HOUR_F4,AVG_LAYER_NUM_F4,AVG_LAYER_HOUR_F5,AVG_LAYER_NUM_F5," +
            " AVG_LAYER_HOUR_F6,AVG_LAYER_NUM_F6,AVG_LAYER_HOUR_ST,AVG_LAYER_NUM_ST from dm_oss_nes_i_bus_cir_day where " +
            " uuid = #{uuid} and STAT_DATE = #{date}")
    List<LayerTime> getLayerTime(@Param("uuid") String uuid, @Param("date") String date);
}
