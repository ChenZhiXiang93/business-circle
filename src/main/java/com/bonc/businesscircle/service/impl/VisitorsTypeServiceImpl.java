package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.VisitorsType;
import com.bonc.businesscircle.pojo.Consumption;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.VisitorsTypeService;
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
public class VisitorsTypeServiceImpl implements VisitorsTypeService {

    private Logger logger = LoggerFactory.getLogger(VisitorsTypeServiceImpl.class);

    @Autowired
    private VisitorsType visitorsType;

    @Override
    public Object getVisitorsTypeNum(String uuid, String date) throws Exception {
        List<Consumption> res = visitorsType.getVisitorsTypeNum(uuid,date);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }
}
