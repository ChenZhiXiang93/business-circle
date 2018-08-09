package com.bonc.businesscircle.service;

import com.bonc.businesscircle.pojo.LayerTime;

import java.util.List;

public interface ResidentTimeService {

    Object getResidentTime(String uuid,String date) throws Exception;

    Object getLayerTime(String uuid, String date) throws Exception;
}
