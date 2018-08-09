package com.bonc.businesscircle.pojo;

import com.bonc.businesscircle.model.BaseEntity;

public class DayTraffic extends BaseEntity {

    private String statDate; //账期日
    private String busCirId;  //商圈ID
    private String busCirName;  //商圈名称
    private Integer busCirNum;  //当日客流量
    private Integer busCirHNum; //当日回头客流量
    private Integer busCirNNum;  //当日新增客流量
    private String avgHour;    //人均驻留总时长
    private String avgLayerHour;  //人均单层驻留时长

    public DayTraffic() {
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
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

    public Integer getBusCirHNum() {
        return busCirHNum;
    }

    public void setBusCirHNum(Integer busCirHNum) {
        this.busCirHNum = busCirHNum;
    }

    public Integer getBusCirNNum() {
        return busCirNNum;
    }

    public void setBusCirNNum(Integer busCirNNum) {
        this.busCirNNum = busCirNNum;
    }

    public String getAvgHour() {
        return avgHour;
    }

    public void setAvgHour(String avgHour) {
        this.avgHour = avgHour;
    }

    public String getAvgLayerHour() {
        return avgLayerHour;
    }

    public void setAvgLayerHour(String avgLayerHour) {
        this.avgLayerHour = avgLayerHour;
    }
}
