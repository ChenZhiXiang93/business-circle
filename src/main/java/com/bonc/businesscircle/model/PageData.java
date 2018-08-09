package com.bonc.businesscircle.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @author JokerJodas
 */
public class PageData {
    /**
     * 不分页的总数
     */
    private long totalCount;

    /**
     * 每页显示记录数
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 当前页数据
     */
    private List<Map<String, Object>> recordList;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Map<String, Object>> getRecordList() {
        if (recordList == null || recordList.isEmpty()) {
            return Collections.emptyList();
        }
        return recordList;
    }

    public void setRecordList(List<Map<String, Object>> recordList) {
        this.recordList = recordList;
    }

}
