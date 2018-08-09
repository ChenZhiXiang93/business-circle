package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.Consumption;
import com.bonc.businesscircle.model.MapMaxUtils;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.ConsumptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
@Component
public class ConsumptionServiceImpl implements ConsumptionService {

    private Logger logger = LoggerFactory.getLogger(ConsumptionServiceImpl.class);

    @Autowired
    private Consumption consumption;

    @Override
    public Object getConsumptionNum(String uuid, String date) throws Exception {
        List<com.bonc.businesscircle.pojo.Consumption> resList = consumption.getConsumptionNum(uuid,date);
        //没有数据则抛出异常
        if (resList.size() <= 0 || resList == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        //存放结果的map
        Map<String,Object> result = new HashMap<>();
        //存放消费水平
        Map<String,Integer> resMap01 = new HashMap<>();
        int consume_L = resList.get(0).getConsumeLowNum();
        int consume_ML = resList.get(0).getConsumeMedLowNum();
        int consume_MH = resList.get(0).getConsumeMedHighNum();
        int consume_H = resList.get(0).getConsumeHighNum();
        resMap01.put("consumeLowNum",consume_L);
        resMap01.put("consumeMedLowNum",consume_ML);
        resMap01.put("consumeMedHighNum",consume_MH);
        resMap01.put("consumeHighNum",consume_H);
        //获得value最大的key
        String key = MapMaxUtils.getMaxKey(resMap01);
        //判断key属于低，中低，中高，高哪一个，然后存放到结果集
        if (key == "consumeLowNum"){
            result.put("consume",1);
            result.put("consumeCount",consume_L);
        }else if (key == "consumeMedLowNum"){
            result.put("consume",2);
            result.put("consumeCount",consume_ML);
        }else if (key == "consumeMedHighNum"){
            result.put("consume",3);
            result.put("consumeCount",consume_MH);
        }else if (key == "consumeHighNum"){
            result.put("consume",4);
            result.put("consumeCount",consume_H);
        }else {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }

        int constend_L = resList.get(0).getConstendLowNum();
        int constend_ML = resList.get(0).getConstendMedLowNum();
        int constend_MH = resList.get(0).getConstendMedHighNum();
        int constend_M = resList.get(0).getConstendHighNum();
        Map<String,Integer> resMap02 = new HashMap<>();
        resMap02.put("constendLowNum",constend_L);
        resMap02.put("constendMedLowNum",constend_ML);
        resMap02.put("constendMedHighNum",constend_MH);
        resMap02.put("constendHighNum",constend_M);
        String key01 = MapMaxUtils.getMaxKey(resMap02);
        if (key01 == "constendLowNum"){
            result.put("constend",1);
            result.put("constendCount",constend_L);
        }else if (key01 == "constendMedLowNum"){
            result.put("constend",2);
            result.put("constendCount",constend_ML);
        }else if (key01 == "constendMedHighNum"){
            result.put("constend",3);
            result.put("constendCount",constend_MH);
        }else if (key01 == "constendHighNum"){
            result.put("constend",4);
            result.put("constendCount",constend_M);
        }else {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }

        int finance = resList.get(0).getFinanceNum();
        int ent = resList.get(0).getEntNum();
        int information = resList.get(0).getInformation();
        int busCusNum = resList.get(0).getBusCusNum();
        if ((double)finance/busCusNum < 0.25){
            result.put("finance",1);
            result.put("financeNum",finance);
        }else if ((double)finance/busCusNum <= 0.5){
            result.put("finance",2);
            result.put("financeNum",finance);
        }else if ((double)finance/busCusNum <= 0.75){
            result.put("finance",3);
            result.put("financeNum",finance);
        }else if ((double)finance/busCusNum <= 1){
            result.put("finance",4);
            result.put("financeNum",finance);
        }else {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }

        if ((double)ent/busCusNum < 0.25){
            result.put("ent",1);
            result.put("entNum",ent);
        }else if ((double)ent/busCusNum <= 0.5){
            result.put("ent",2);
            result.put("entNum",ent);
        }else if ((double)ent/busCusNum <= 0.75){
            result.put("ent",3);
            result.put("entNum",ent);
        }else if ((double)ent/busCusNum <= 1){
            result.put("ent",4);
            result.put("entNum",ent);
        }else {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }

        if ((double)information/busCusNum < 0.25){
            result.put("information",1);
            result.put("informationNum",information);
        }else if ((double)information/busCusNum <= 0.5){
            result.put("information",2);
            result.put("informationNum",information);
        }else if ((double)information/busCusNum <= 0.75){
            result.put("information",3);
            result.put("informationNum",information);
        }else if ((double)information/busCusNum <= 1){
            result.put("information",4);
            result.put("informationNum",information);
        }else {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(result);
    }
}
