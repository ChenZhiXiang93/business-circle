package com.bonc.businesscircle.controller;

import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.service.TrafficAnalysisService;
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
@RequestMapping(value = "/traffic")
@Api("商圈人流分析")
public class TrafficAnalysisController {

    private Logger logger = LoggerFactory.getLogger(TrafficAnalysisController.class);

    @Autowired
    private TrafficAnalysisService trafficAnalysisService;

   @ApiOperation(value = "获取实时流量信息", notes = "根据商圈的uuid和账期来获取对应人流信息,BUS_CIR_NUM:实时人流")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "YYYYMMDDHH", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/realTraffic")
    public Object getRealTraffic(@RequestParam("uuid") String uuid,@RequestParam(value = "date",required = false) String date) throws Exception {
       return trafficAnalysisService.getRealTraffic(uuid,date);
    }

    @ApiOperation(value = "获取当日流量信息", notes = "根据商圈的uuid和账期来获取对应人流信息,statDate:日期,busCirNum:当日客流量,busCirHNum:当日回头客流量," +
            "busCirNNum:当日新增客流量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "YYYYMMDD", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/todayTraffic")
    public Object getTodayTraffic(@RequestParam("uuid") String uuid,@RequestParam(value = "date",required = false) String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.GetNowDate();
        }
       return trafficAnalysisService.getTodayTraffic(uuid,date);
    }
}
