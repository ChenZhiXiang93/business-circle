package com.bonc.businesscircle.service.impl;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.mapper.SourceRank;
import com.bonc.businesscircle.model.DateFormat;
import com.bonc.businesscircle.pojo.RankTop;
import com.bonc.businesscircle.pojo.SourceStatistical;
import com.bonc.businesscircle.response.CommResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.SourceRankService;
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
public class SourceRankServiceImpl implements SourceRankService {

    private Logger logger = LoggerFactory.getLogger(SourceRankServiceImpl.class);

    @Autowired
    private SourceRank sourceRank;

    @Override
    public Object getSourceRank(String uuid, String date) throws Exception {
        List<SourceStatistical> res = sourceRank.getSourceRank(uuid,date);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }

    @Override
    public Object getLiveRank(String uuid, String date, String type) throws Exception {
        String beforeMonth = DateFormat.getTime(date);
        System.out.println(beforeMonth);
        List<RankTop> res = sourceRank.getLiveRank(uuid,date,type,beforeMonth);
        if (res.size() <= 0 || res == null) {
            throw new BusinessException(CommResponseEnum.FAILURE1);
        }
        return ResponseData.turnResponse(res);
    }

   /* @Override
    public Object getLiveRank(String uuid, String date) throws Exception {
        for (int i = 0;i < sourceRank.getLiveRank(uuid,date).size();i++){
            System.out.println(sourceRank.getLiveRank(uuid,date).get(i).getRestWorkArea());
        }

        return ResponseData.turnResponse(sourceRank.getLiveRank(uuid,date));
    }*/

    /*@Override
    public Object getWorkRank(String uuid, String date) throws Exception {
        return ResponseData.turnResponse(sourceRank.getWorkRank(uuid,date));
    }

    @Override
    public Integer getSourcesWork(String uuid, String name, String date) throws Exception {
        return sourceRank.getSourcesWork(uuid,name,date);
    }

    @Override
    public Integer getSourcesLive(String uuid, String name, String date) {
        return sourceRank.getSourcesLive(uuid,name,date);
    }*/
}
