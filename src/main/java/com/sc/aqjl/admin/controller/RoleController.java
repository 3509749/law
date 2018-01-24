package com.sc.aqjl.admin.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.aqjl.admin.model.URole;
import com.sc.aqjl.admin.service.RoleService;
import com.sc.aqjl.base.model.TreeModel;
import com.sc.aqjl.util.CommonUtil;
import com.sc.aqjl.util.ExcelFileGenerator;

 
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/getRoleData")
	@ResponseBody 
	public Map<String, Object> getUserData(URole u,int page,int limit){
		return  CommonUtil.jsonPutPageList(roleService.getRoleByPage(u,page,limit));
	}

	@RequestMapping("/add")
	@ResponseBody
	public int addRole(URole u){
		return roleService.insertRole(u);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public int editRole(URole u){
		return roleService.updateRole(u);
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public int delUsers(String ids){
		return roleService.deleteRoles(ids.split(","));
	}
	
	
	@RequestMapping("/getResourceTree")
	@ResponseBody
	public List<TreeModel> getResourceTree(String id){
		return roleService.getResourceTree(id);
	}
	
	@RequestMapping("/addResourceRole")
	@ResponseBody
	public int addResourceRole(String id,String ids){
		return roleService.insertResourceRole(id,ids);
	}
	
	
	@RequestMapping("/rolelist")
	public String userlist(ModelMap map){
    return "system/role/rolelist";
    }
	
	@RequestMapping("/roleAdd")
	public String roleAdd(ModelMap map){
    return "system/role/roleAdd";
    }
	
	@RequestMapping("/roleEdit")
	public ModelAndView roleEdit(URole u,ModelMap modelMap){
		modelMap.put("role",roleService.getRole(u));
	    return new ModelAndView("system/role/roleEdit",modelMap);
    }
	
	@RequestMapping("/roleGive")
	public ModelAndView roleGive(URole u,ModelMap modelMap){
		modelMap.put("role",roleService.getRole(u));
	    return new ModelAndView("system/role/roleGive",modelMap);
    }
	
	//跳转编辑页面
	@RequestMapping("/roleDetail")
	 public ModelAndView detail(URole u,ModelMap modelMap)
	    throws Exception
	 {
		modelMap.put("role",roleService.getRole(u));
	    return new ModelAndView("system/role/roleDetail",modelMap);
	 }
	

}