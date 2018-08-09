package com.bonc.businesscircle.controller;


import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.service.ConsumptionService;
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
@RequestMapping("/consumption")
@Api("商圈消费能力")
public class ConsumptionController {

    private Logger logger = LoggerFactory.getLogger(ConsumptionController.class);

    @Autowired
    private ConsumptionService consumptionService;

    @ApiOperation(value = "获取消费能力信息", notes = "consume:消费水平,consumeCount:消费水平人数;constend:消费倾向,constendCount:消费倾向人数;finance:金融,financeNum:金融人数;ent:娱乐,entNum:娱乐人数;information:资讯,informationNum:资讯人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "查询月份，格式:YYYYMM", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping
    public Object getConsumptionNum(@RequestParam("uuid")String uuid, @RequestParam(value = "date",required = false)String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getLastMonth();
        }
        return consumptionService.getConsumptionNum(uuid,date);
    }
}
