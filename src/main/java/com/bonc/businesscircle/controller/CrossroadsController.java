package com.bonc.businesscircle.controller;


import com.bonc.businesscircle.service.CrossroadsService;
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
@RequestMapping("/crossroads")
@Api("路口人流分析")
public class CrossroadsController {

    private Logger logger = LoggerFactory.getLogger(CrossroadsController.class);

    @Autowired
    private CrossroadsService crossroadsService;

    @ApiOperation(value = "获取路口人流量信息", notes = "根据商圈的uuid和账期来获取获取路口人流量信息,C_Num:路口人流量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "查询小时,格式:YYYYMMDDHH", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping
    public Object getCrossroadsNum(@RequestParam("uuid")String uuid, @RequestParam(value = "date",required = false)String date) throws Exception {

        return crossroadsService.getCrossroadsNum(uuid,date);
    }

}
