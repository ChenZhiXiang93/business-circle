package com.bonc.businesscircle.controller;

import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.service.DemographicService;
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
@RequestMapping("/demographic")
@Api("商圈人流特征")
public class DemographicController {

    private Logger logger = LoggerFactory.getLogger(DemographicController.class);

    @Autowired
    private DemographicService demographicService;

    @ApiOperation(value = "获取访客性别和年龄人数信息", notes = "根据商圈的uuid和账期来获取对应访客性别和年龄人数信息,sexMNum:性别男,sexFNum:性别女,sexNNum:性别未知," +
            "age50Num:50后,age60Num:60后,age70Num:70后,age80Num:80后,age90Num:90后,age00Num:00后")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "查询月份，格式:YYYYMM", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/sexAndAge")
    public Object getSexNum(@RequestParam("uuid")String uuid, @RequestParam(value = "date",required = false)String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getLastMonth();
        }
        return demographicService.getSexNumAndAge(uuid,date);
    }

   /* @ApiOperation(value = "获取访客不同年龄阶段人数信息", notes = "根据商圈的uuid和账期来获取对应访客不同年龄阶段人数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "date", value = "查询月份，格式:2018-07", required = false, dataType = "String", paramType = "query")
    })*/
    /*@GetMapping("/age")
    public Object getAgeNum(@RequestParam("uuid")String uuid, @RequestParam("date")String date) throws Exception {
        return demographicService.getAgeNum(uuid,date);
    }*/
    @ApiOperation(value = "获取访客学生和上班族人数信息", notes = "根据商圈的uuid和账期来获取对应学生和上班族人数信息,workStudentNum:工作（学生）," +
            "workWhiteCollarNum:工作（白领）,workOtherNum:工作（其他）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "查询月份，格式:YYYYMM", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/work")
    public Object getWorkNum(@RequestParam("uuid")String uuid, @RequestParam(value = "date",required = false)String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getLastMonth();
        }
        return demographicService.getWorkNum(uuid,date);
    }
}
