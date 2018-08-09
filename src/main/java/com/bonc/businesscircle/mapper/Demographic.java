package com.bonc.businesscircle.mapper;

import com.bonc.businesscircle.pojo.BasisPortrait;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Demographic {

    /*******人口统计学特征 男 女 未知 年龄********/
    @Select("select SEX_M_NUM as sexMNum,SEX_F_NUM as sexFNum,SEX_N_NUM as sexNNum,AGE_50_NUM as age50Num," +
            " AGE_60_NUM as age60Num,AGE_70_NUM as age70Num,AGE_80_NUM as age80Num,AGE_90_NUM as age90Num," +
            " AGE_00_NUM as age00Num from DM_OSS_NES_I_BUS_CIR_BASE_INFO_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date}")
    List<BasisPortrait> getSexNumAnAge(@Param("uuid") String uuid, @Param("date") String date);

    /*******人口统计学特征 学生 白领 其他*******/
    @Select("select WORK_STUDENT_NUM as workStudentNum,WORK_WHITE_COLLAR_NUM as workWhiteCollarNum,WORK_OTHER_NUM as workOtherNum from DM_OSS_NES_I_BUS_CIR_BASE_INFO_MON" +
            " where uuid = #{uuid} and STAT_MON = #{date}")
    List<BasisPortrait> getWorkNum(@Param("uuid") String uuid, @Param("date") String date);
}
