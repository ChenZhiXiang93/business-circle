package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.TrafficAnalysis;
import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.pojo.DayTraffic;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.TrafficAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class TrafficAnalysisServiceImpl implements TrafficAnalysisService {

    private Logger logger = LoggerFactory.getLogger(TrafficAnalysisServiceImpl.class);

    @Autowired
    private TrafficAnalysis trafficAnalysis;

    @Override
    public Object getRealTraffic(String uuid, String date) throws Exception {
        Object res = trafficAnalysis.getRealTraffic(uuid,date);
        if (res == null || res.equals("")){
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }

    @Override
    public Object getTodayTraffic(String uuid, String date) throws Exception {
        String startDate = DateBefore.ago(date);
        List<DayTraffic> res = trafficAnalysis.getTodayTraffic(uuid,startDate,date);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }

        return ResponseData.turnResponse(res);
    }
}
