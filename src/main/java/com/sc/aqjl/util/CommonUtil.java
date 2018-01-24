package com.sc.aqjl.util;

import java.util.HashMap;
import java.util.Map;

import com.sc.aqjl.base.model.PageBean;



public class CommonUtil {
	
	
	public static Map<String, Object> jsonPutPageList(PageBean page){
    	Map<String, Object> jsonMap2 = new HashMap<String, Object>();
    	jsonMap2.put("count", page.getTotalNum());
    	jsonMap2.put("data", page.getItems());
    	jsonMap2.put("msg", "");
    	jsonMap2.put("code", 0);
    	return jsonMap2;
    }
	
	
}
