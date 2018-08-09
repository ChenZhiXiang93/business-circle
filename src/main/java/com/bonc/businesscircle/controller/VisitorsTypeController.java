package com.bonc.businesscircle.controller;


import com.bonc.businesscircle.model.DateBefore;
import com.bonc.businesscircle.service.VisitorsTypeService;
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
@RequestMapping("/visitors")
@Api("商圈访客的分类")
public class VisitorsTypeController {

    private Logger logger = LoggerFactory.getLogger(VisitorsTypeController.class);

    @Autowired
    private VisitorsTypeService visitorsTypeService;

    @ApiOperation(value = "获取购物狂、电影发烧友、美食达人三类的信息", notes = "根据商圈的uuid和账期来消费能力信息,shoppingNum:购物狂,foodNum:美食达人,filmNum:电影发烧友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "商圈uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "YYYYMM", required = false, dataType = "String", paramType = "query")
    })

    @GetMapping
    public Object getVisitorsType(String uuid,String date) throws Exception {
        if (date == null || date.equals("")){
            date = DateBefore.getLastMonth();
        }
        return visitorsTypeService.getVisitorsTypeNum(uuid,date);
    }

}
