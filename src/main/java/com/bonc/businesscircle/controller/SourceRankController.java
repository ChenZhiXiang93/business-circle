package com.bonc.businesscircle.controller;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.model.DateFormat;
import com.bonc.businesscircle.response.ResponseData;
import com.bonc.businesscircle.service.SourceRankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rank")
@Api("访客来源排行")
public class SourceRankController {

    private Logger logger = LoggerFactory.getLogger(SourceRankController.class);

    @Autowired
    private SourceRankService sourceRankService;

    @ApiOperation(value = "获取访客来源排行信息", notes = "根据商圈的uuid和账期来获取对应访客来源排行信息,restWorkArea:常驻地,busCirHNum:人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "YYYYMM", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/sourceRank")
    public Object getSourceRank(@RequestParam("uuid")String uuid,@RequestParam(value = "date",required = false)String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getLastMonth();
        }
        return sourceRankService.getSourceRank(uuid,date);
    }

    @ApiOperation(value = "获取常驻地TOP5", notes = "根据商圈的uuid和账期来获取对应访客来源排行信息,name:常驻地,currentMoth:当前月份,beforeMoth:上个月,currnetRank:当前月份排行," +
            "beforeRank:上个月的排行,changeNum:当月排行较上月的变化,数据为负，下降，反之上升")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "YYYYMM", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1;工作2，休息,0，其他", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/topRank")
    public Object getLiveRank(@RequestParam("uuid")String uuid,@RequestParam(value = "date",required = false)String date,@RequestParam("type")String type) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getLastMonth();
        }
        return sourceRankService.getLiveRank(uuid,date,type);
    }
/*
    @ApiOperation(value = "获取工作常驻地TOP5", notes = "根据商圈的uuid和账期来获取对应访客来源排行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "date", value = "YYYYMM", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/workRank")
    public Object getWorkRank(@RequestParam("uuid")String uuid,@RequestParam("date")String date) throws Exception {
        return sourceRankService.getWorkRank(uuid,date);
    }

    @ApiOperation(value = "获取居住常驻地TOP5较上月的变化", notes = "根据商圈的uuid和账期来获取对应访客来源排行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "date", value = "YYYYMM", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/liveRankChange")
    public Object getLiveRankChange(@RequestParam("uuid")String uuid,@RequestParam("date")String date) throws Exception {
        //上个月的数据
        String lastMonth = (String) sourceRankService.getLiveRank(uuid,DateFormat.getTime(date));
        //当前月的数据
        String currentMonth = (String) sourceRankService.getLiveRank(uuid,date);
        //截取data以后有效数据
        String current01 = org.apache.commons.lang.StringUtils.substringAfter(currentMonth,"[");
        String current02 = org.apache.commons.lang.StringUtils.substringBeforeLast(current01,"]");
        *//*System.out.println(lastMonth);
        System.out.println(currentMonth);
        System.out.println(current02);*//*
        //拼接成字符串
        String str = "["+current02+"]";
        //json数据的转换并获取
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(str);
        List<Object> nums = new ArrayList<>();
        //循环取到需要的数据
        for (int i = 0;i < 2;i++){
            *//*System.out.println(jsonArray.getJSONObject(i).get("restWorkArea"));*//*
            //根据住地获取上月访问人数
            Integer lastNum = sourceRankService.getSourcesLive(uuid,(String)jsonArray.getJSONObject(i).get("restWorkArea"),DateFormat.getTime(date));
            *//*System.out.println(lastNum);*//*
            //根据住地获取当月访问人数
            Integer currntNum = (Integer) jsonArray.getJSONObject(i).get("busCirHNum");
            *//*System.out.println(currntNum);*//*
            nums.add(currntNum-lastNum);
           *//* System.out.println(nums);*//*
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("data",nums);
        return ResponseData.turnResponse(resMap);
    }

    @ApiOperation(value = "获取工作常驻地TOP5较上月的变化", notes = "根据商圈的uuid和账期来获取对应访客来源排行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "date", value = "YYYYMM", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/workRankChange")
    public Object getWorkRankChange(@RequestParam("uuid")String uuid,@RequestParam("date")String date) throws Exception {
        String lastMonth = (String) sourceRankService.getWorkRank(uuid,DateFormat.getTime(date));
        String currentMonth = (String) sourceRankService.getWorkRank(uuid,date);
        String current01 = org.apache.commons.lang.StringUtils.substringAfter(currentMonth,"[");
        String current02 = org.apache.commons.lang.StringUtils.substringBeforeLast(current01,"]");
        *//*System.out.println(lastMonth);
        System.out.println(currentMonth);
        System.out.println(current02);*//*
        String str = "["+current02+"]";
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(str);
        List<Object> nums = new ArrayList<>();
        for (int i = 0;i < 2;i++){
            *//*System.out.println(jsonArray.getJSONObject(i).get("restWorkArea"));*//*
            Integer lastNum = sourceRankService.getSourcesWork(uuid,(String)jsonArray.getJSONObject(i).get("restWorkArea"),DateFormat.getTime(date));
           *//* System.out.println(lastNum);*//*
            Integer currntNum = (Integer) jsonArray.getJSONObject(i).get("busCirHNum");
           *//* System.out.println(currntNum);*//*
            nums.add(currntNum-lastNum);
            *//*System.out.println(nums);*//*
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("data",nums);
        return ResponseData.turnResponse(resMap);
    }*/
}
