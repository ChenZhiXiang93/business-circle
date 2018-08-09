package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.Crossroads;
import com.bonc.businesscircle.pojo.Consumption;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.CrossroadsService;
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
public class CrossroadsServiceImpl implements CrossroadsService {

    private Logger logger = LoggerFactory.getLogger(CrossroadsServiceImpl.class);

    @Autowired
    private Crossroads crossroads;

    @Override
    public Object getCrossroadsNum(String uuid, String date) throws Exception {
        Object res = crossroads.getCrossroadsNum(uuid,date);
        if (res == null || res.equals("")) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }
}
