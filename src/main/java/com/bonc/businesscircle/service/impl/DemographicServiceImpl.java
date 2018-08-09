package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.Demographic;
import com.bonc.businesscircle.pojo.BasisPortrait;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.DemographicService;
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
public class DemographicServiceImpl implements DemographicService {

    private Logger logger = LoggerFactory.getLogger(DemographicServiceImpl.class);

    @Autowired
    private Demographic demographic;

    @Override
    public Object getSexNumAndAge(String uuid, String date) throws Exception {
        List<BasisPortrait> res = demographic.getSexNumAnAge(uuid,date);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }

    @Override
    public Object getWorkNum(String uuid, String date) throws Exception {
        List<BasisPortrait> res = demographic.getWorkNum(uuid,date);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(demographic.getWorkNum(uuid,date));
    }
}
