package com.bonc.businesscircle.pojo;

import io.swagger.models.auth.In;

public class RankTop {

    private String name;
    private Integer beforeMoth;
    private Integer currentMoth;
    private Integer changeNum;
    private Double currnetRank;
    private Double beforeRank;

    public Double getCurrnetRank() {
        return currnetRank;
    }

    public void setCurrnetRank(Double currnetRank) {
        this.currnetRank = currnetRank;
    }

    public Double getBeforeRank() {
        return beforeRank;
    }

    public void setBeforeRank(Double beforeRank) {
        this.beforeRank = beforeRank;
    }

    public RankTop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBeforeMoth() {
        return beforeMoth;
    }

    public void setBeforeMoth(Integer beforeMoth) {
        this.beforeMoth = beforeMoth;
    }

    public Integer getCurrentMoth() {
        return currentMoth;
    }

    public void setCurrentMoth(Integer currentMoth) {
        this.currentMoth = currentMoth;
    }

    public Integer getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
    }
}
