package com.bonc.businesscircle.model;


/**
 * 分库分表，早着呢吧？
 * @author JokerJodas
 */
public class MultiDbTableModel {
    /**
     * 分库数字坐标
     */
    private Long dbNum;
    /**
     * 分表数字坐标
     */
    private Long tbNum;

    public MultiDbTableModel(Long dbNum, Long tbNum) {
        this.dbNum = dbNum;
        this.tbNum = tbNum;
    }

    public Long getDbNum() {
        return dbNum;
    }

    public void setDbNum(Long dbNum) {
        this.dbNum = dbNum;
    }

    public Long getTbNum() {
        return tbNum;
    }

    public void setTbNum(Long tbNum) {
        this.tbNum = tbNum;
    }
}
