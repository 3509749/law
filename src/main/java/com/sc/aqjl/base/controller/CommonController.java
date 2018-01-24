package com.sc.aqjl.base.controller;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.sc.aqjl.base.dict.CodeDict;
import com.sc.aqjl.base.model.DictTable;
import com.sc.aqjl.base.model.FtpProps;
import com.sc.aqjl.util.FtpUtil;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private FtpProps ftpProps;

	@RequestMapping("/getHtmlSelectOptionsByWhereCase")
	@ResponseBody 
	public String getHtmlSelectOptionsByWhereCase(DictTable d){
    	return  CodeDict.getInstance().createHtmlSelectOptionsByWhereCase(d.getTableName(), d.getCodeField(),d.getNameField(),d.getWhereCase(),d.getParentField(), d.getOrderByField(),d.getSelectValue(),d.getCache());
	}
	@RequestMapping("/createHtmlSelectOptions_CODE_CODE_TEXT_ByWhereCase")
	@ResponseBody 
	public String createHtmlSelectOptions_CODE_CODE_TEXT_ByWhereCase(DictTable d){
    	return  CodeDict.getInstance().createHtmlSelectOptions_CODE_CODE_TEXT_ByWhereCase(d.getTableName(), d.getCodeField(),d.getNameField(),d.getWhereCase(),d.getParentField(), d.getOrderByField(),d.getSelectValue(),d.getCache());
	}
	@RequestMapping("/createHtmlSelectOptions_TEXT_CODE_TEXT_ByWhereCase")
	@ResponseBody 
	public String createHtmlSelectOptions_TEXT_CODE_TEXT_ByWhereCase(DictTable d){
    	return  CodeDict.getInstance().createHtmlSelectOptions_TEXT_CODE_TEXT_ByWhereCase(d.getTableName(), d.getCodeField(),d.getNameField(),d.getWhereCase(),d.getParentField(), d.getOrderByField(),d.getSelectValue(),d.getCache());
	}
	@RequestMapping("/getHtmlSelectOptions")
	@ResponseBody 
	public String getHtmlSelectOptionsByCache(DictTable d){
    	return  CodeDict.getInstance().createHtmlSelectOptions(d.getTableName(), d.getCodeField(),d.getNameField(),d.getParentField(),d.getParentValue(),d.getOrderByField(),d.getSelectValue() ,d.getCache());
	}
	@RequestMapping(value = "/upload/{type}",method = RequestMethod.POST)
	@ResponseBody
	public void upload(MultipartFile file,HttpServletRequest request,@PathVariable String type){
		String name = file.getOriginalFilename();
		SimpleDateFormat simdf = new SimpleDateFormat("/YYYY/MM/dd");
		Calendar cal = Calendar.getInstance();
		String path = simdf.format(cal.getTime());
		try {
			FtpUtil.uploadFile(ftpProps.getHost(),ftpProps.getPort(),ftpProps.getUsername(),ftpProps.getPassword(),"/"+type,path,name,file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}