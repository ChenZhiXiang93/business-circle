package com.bonc.businesscircle.pojo;

import com.bonc.businesscircle.model.BaseEntity;

public class TrafficAnalysis extends BaseEntity {

    private String statHour;   //账期小时
    private String busCirId;   //商圈ID
    private String busCirName;  //商圈名称
    private Integer busCirNum;  //实时客流量
    private Integer crossingNum;  //路口人流量

    public TrafficAnalysis() {
    }

    public String getStatHour() {
        return statHour;
    }

    public void setStatHour(String statHour) {
        this.statHour = statHour;
    }

    public String getBusCirId() {
        return busCirId;
    }

    public void setBusCirId(String busCirId) {
        this.busCirId = busCirId;
    }

    public String getBusCirName() {
        return busCirName;
    }

    public void setBusCirName(String busCirName) {
        this.busCirName = busCirName;
    }

    public Integer getBusCirNum() {
        return busCirNum;
    }

    public void setBusCirNum(Integer busCirNum) {
        this.busCirNum = busCirNum;
    }

    public Integer getCrossingNum() {
        return crossingNum;
    }

    public void setCrossingNum(Integer crossingNum) {
        this.crossingNum = crossingNum;
    }
}
