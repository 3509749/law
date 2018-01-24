package com.sc.aqjl.business.yw.controller;


import com.sc.aqjl.admin.model.UUser;
import com.sc.aqjl.base.mapper.DictMapper;
import com.sc.aqjl.base.model.FtpProps;
import com.sc.aqjl.business.yw.model.DLZDJKZB;
import com.sc.aqjl.business.yw.service.MajorRoadService;
import com.sc.aqjl.util.CommonUtil;
import com.sc.aqjl.util.FtpUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("majorRoad")
public class MajorRoadController {
    @Autowired
    private MajorRoadService majorRoadService;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private FtpProps ftpProps;
    @RequestMapping("/getMajorRoadData")
    @ResponseBody
    public Map<String, Object> getMajorRoadData(DLZDJKZB a){
        UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
        return  CommonUtil.jsonPutPageList(majorRoadService.getMajorRoadList(a,1,10000));
    }


    public void upload(){

    }

    @RequestMapping("/majorRoadList")
    public String majorRoadList(ModelMap map){
        return "business/majorRoad/majorRoadList";
    }

    @RequestMapping("/majorRoadAddPage")
    public String majorRoadAddPage(ModelMap map){
        return "business/majorRoad/majorRoadAdd";
    }

}