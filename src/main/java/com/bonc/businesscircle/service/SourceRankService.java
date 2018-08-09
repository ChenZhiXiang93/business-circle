package com.bonc.businesscircle.service;


import com.bonc.businesscircle.pojo.RankTop;

import java.util.List;

public interface SourceRankService {

    Object getSourceRank(String uuid,String date) throws Exception;

    Object getLiveRank(String uuid, String date, String type) throws Exception;

    /*Object getLiveRank(String uuid, String date) throws Exception;

    Object getWorkRank(String uuid, String date) throws Exception;

    Integer getSourcesWork(String uuid, String name, String date) throws Exception;

    Integer getSourcesLive(String uuid, String name, String date);*/
}
