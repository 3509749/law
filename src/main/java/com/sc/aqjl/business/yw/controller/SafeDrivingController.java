package com.sc.aqjl.business.yw.controller;


import com.sc.aqjl.admin.model.UUser;
import com.sc.aqjl.base.mapper.DictMapper;
import com.sc.aqjl.base.model.DictTable;
import com.sc.aqjl.business.yw.model.AQXCJS;
import com.sc.aqjl.business.yw.service.SafeDrivingService;
import com.sc.aqjl.util.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.util.Map;


@Controller
@RequestMapping("safeDriving")
public class SafeDrivingController {
    @Autowired
    private SafeDrivingService safeDrivingService;
    @Autowired
    private DictMapper dictMapper;

    @RequestMapping("/getSafeDrivingData")
    @ResponseBody
    public Map<String, Object> getSafeDrivingData(AQXCJS a){
        UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
        return  CommonUtil.jsonPutPageList(safeDrivingService.getSafeDrivingList(a,1,10000));
    }

    @RequestMapping("/add")
    @ResponseBody
    public int add(AQXCJS a){
        if( 0 != a.getRecordid())
            return safeDrivingService.update(a);
        else
            return  safeDrivingService.insert(a);
    }



    @RequestMapping("/safeDrivingList")
    public String safeDrivingList(ModelMap map){
        return "business/safeDriving/safeDrivingList";
    }

    @RequestMapping("/safeDrivingAddPage")
    public String safeDrivingAddPage(ModelMap map,@RequestParam(value = "id",required = false,defaultValue = "0") int id){
        map.put("data",safeDrivingService.getSafeDrivingData(id));
        map.put("companyList",dictMapper.getOtherTable(new DictTable("SYN_CO", "CONO", "CONAME","", "","")));
        return "business/safeDriving/safeDrivingAdd";
    }
    @RequestMapping("/del")
    @ResponseBody
    public int del(String ids){
        return  safeDrivingService.del(ids.split(","));
    }
}