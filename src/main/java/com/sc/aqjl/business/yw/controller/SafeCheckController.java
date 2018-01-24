package com.sc.aqjl.business.yw.controller;


import com.sc.aqjl.admin.model.UUser;
import com.sc.aqjl.base.mapper.DictMapper;
import com.sc.aqjl.base.model.DictTable;
import com.sc.aqjl.business.yw.model.ANJCMXB;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.service.SafeCheckService;
import com.sc.aqjl.util.CommonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("safeCheck")
public class SafeCheckController {
    @Autowired
    private SafeCheckService safeCheckService;
    @Autowired
    private DictMapper dictMapper;

    @RequestMapping("/getSafeCheckData")
    @ResponseBody
    public Map<String, Object> getSafeCheckData(AQJCZB a){
        UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
        return  CommonUtil.jsonPutPageList(safeCheckService.getSafeCheckList(a,1,10000));
    }

    @RequestMapping("/add")
    @ResponseBody
    public int add(String obj,String listStr){
        AQJCZB a = (AQJCZB)JSONObject.toBean(JSONObject.fromObject(obj),AQJCZB.class);
        UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
        a.setCzy(user.getName());
        JSONArray array = JSONArray.fromObject(listStr.replaceAll("\"","\'"));
        List<ANJCMXB> list = JSONArray.toList(array, new ANJCMXB(), new JsonConfig());
        a.setList(list);
        if(!"0".equals(a.getJcdh()))
            return  safeCheckService.updateAQJCZB(a);
        else
            return  safeCheckService.insertAQJCZB(a);
    }

    @RequestMapping("/safeCheckList")
    public String safeCheckList(ModelMap map){
        return "business/safeCheck/safeCheckList";
    }

    @RequestMapping("/safeCheckAddPage")
    public String safeDrivingAddPage(ModelMap map,@RequestParam(value = "id",required = false,defaultValue = "0") String id){
        AQJCZB data =safeCheckService.getSafeCheckData(id);
        map.put("data",data);
        map.put("jclx",dictMapper.getDict(new DictTable(1008)));
        map.put("buscrew",dictMapper.getOtherTable(new DictTable("TB_BUSCREW", "buscrewno", "buscrewname","", "","")));
        DictTable d =new DictTable("SYN_CO", "CONO", "CONAME","", "","");
        if(!id.equals("0"))
            d.setWhereCase(" CONO = '"+ data.getZbgs() + "'");
        map.put("companyList",dictMapper.getOtherTable(d));
        return "business/safeCheck/safeCheckAdd";
    }

    @RequestMapping("/del")
    @ResponseBody
    public int del(String ids){
        return  safeCheckService.del(ids.split(","));
    }


}