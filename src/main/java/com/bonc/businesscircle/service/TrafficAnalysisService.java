package com.bonc.businesscircle.service;

import java.util.List;
import java.util.Map;

public interface TrafficAnalysisService {

    Object getRealTraffic(String uuid,String date) throws Exception;

    Object getTodayTraffic(String uuid, String date) throws Exception;
}
