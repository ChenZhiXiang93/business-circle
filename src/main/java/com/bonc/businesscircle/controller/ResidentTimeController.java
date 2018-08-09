package com.bonc.businesscircle.controller;

import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.service.ResidentTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
@Api("商圈驻留时长分析")
public class ResidentTimeController {

    private Logger logger = LoggerFactory.getLogger(ResidentTimeController.class);

    @Autowired
    private ResidentTimeService residentTimeService;

    @ApiOperation(value = "获取访客人均驻留时长信息", notes = "根据商圈的uuid和账期来获取该商圈的人均驻留时长信息,perCapita:人均驻留总时长")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "查询月份，格式:YYYYMMDD", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/avgTime")
    public Object getSourceRank(@RequestParam("uuid")String uuid, @RequestParam(value = "date",required = false)String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getYestoday();
        }
        return residentTimeService.getResidentTime(uuid,date);
    }

    @ApiOperation(value = "获取商圈每层人数及人均驻留时长信息", notes = "根据商圈的uuid和账期来获取商圈每层人数及人均驻留时长信息,AVG_LAYER_HOUR_FF1:日均每层驻留时长(负一楼)," +
            "AVG_LAYER_NUM_FF1:日均每层驻留人数(负一楼),AVG_LAYER_HOUR_F1:日均每层驻留时长(1楼),AVG_LAYER_NUM_F1:日均每层驻留人数(1楼),AVG_LAYER_HOUR_F2:日均每层驻留时长(2楼)," +
            "AVG_LAYER_NUM_F2:日均每层驻留人数(2楼),AVG_LAYER_HOUR_F3:日均每层驻留时长(3楼),AVG_LAYER_NUM_F3:日均每层驻留人数(3楼),AVG_LAYER_HOUR_F4:日均每层驻留时长(4楼)," +
            "AVG_LAYER_NUM_F4:日均每层驻留人数(4楼),AVG_LAYER_HOUR_F5:日均每层驻留时长(5楼),AVG_LAYER_NUM_F5:日均每层驻留人数(5楼),AVG_LAYER_HOUR_F6:日均每层驻留时长(6楼)," +
            "AVG_LAYER_NUM_F6:日均每层驻留人数(6楼),AVG_LAYER_HOUR_ST:日均每层驻留时长(停车场),AVG_LAYER_NUM_ST:日均每层驻留人数(停车场)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "查询月份，格式:YYYYMMDD", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/avgAndNum")
    public Object getAvgAndNum(@RequestParam("uuid")String uuid, @RequestParam(value = "date",required = false)String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getYestoday();
        }
        return residentTimeService.getLayerTime(uuid,date);
    }
}
