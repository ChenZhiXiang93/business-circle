package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.ResidentTime;
import com.bonc.businesscircle.model.DataUtil;
import com.bonc.businesscircle.pojo.LayerTime;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.ResidentTimeService;
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
public class ResidentTimeServiceImpl implements ResidentTimeService {

    private Logger logger = LoggerFactory.getLogger(ResidentTimeServiceImpl.class);

    @Autowired
    private ResidentTime residentTime;

    @Override
    public Object getResidentTime(String uuid, String date) throws Exception {
        Object res = residentTime.getResidentTime(uuid,date);
        if (res == null || res.equals("")){
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }

    @Override
    public Object getLayerTime(String uuid, String date) throws Exception {
        List<LayerTime> res = residentTime.getLayerTime(uuid,date);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        res.get(0).setAVG_LAYER_HOUR_FF1(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_FF1(),res.get(0).getAVG_LAYER_NUM_FF1()));
        res.get(0).setAVG_LAYER_HOUR_F1(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_F1(),res.get(0).getAVG_LAYER_NUM_F1()));
        res.get(0).setAVG_LAYER_HOUR_F2(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_F2(),res.get(0).getAVG_LAYER_NUM_F2()));
        res.get(0).setAVG_LAYER_HOUR_F3(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_F3(),res.get(0).getAVG_LAYER_NUM_F3()));
        res.get(0).setAVG_LAYER_HOUR_F4(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_F4(),res.get(0).getAVG_LAYER_NUM_F4()));
        res.get(0).setAVG_LAYER_HOUR_F5(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_F5(),res.get(0).getAVG_LAYER_NUM_F5()));
        res.get(0).setAVG_LAYER_HOUR_F6(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_F6(),res.get(0).getAVG_LAYER_NUM_F6()));
        res.get(0).setAVG_LAYER_HOUR_ST(DataUtil.divide(res.get(0).getAVG_LAYER_HOUR_ST(),res.get(0).getAVG_LAYER_NUM_ST()));
        return ResponseData.turnResponse(res);
    }
}
