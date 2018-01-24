package com.sc.aqjl.admin.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import com.sc.aqjl.admin.model.User;
import com.sc.aqjl.admin.service.UserService;
import com.sc.aqjl.util.CommonUtil;

 
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	

	
	@RequestMapping("/getUserData")
	@ResponseBody 
	public Map<String, Object> getUserData(User u,int page,int limit){
		return  CommonUtil.jsonPutPageList(userService.getUserByPage(u,page,limit));
	}
	
	/**
	 * 跳转到主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/userlist")
	public String userlist(ModelMap map){
    return "system/userlist";
    }
    
	@RequestMapping("/userAdd")
	public String userAdd(ModelMap map){
    return "system/userAdd";
    }
	@RequestMapping("/add")
	@ResponseBody
	public int addUser(User u){
		return userService.insertUser(u);
	}
	@RequestMapping("/del")
	@ResponseBody
	public int delUsers(String ids){
		return userService.deleteUsers(ids.split(","));
	}
	//跳转编辑页面
	@RequestMapping("/editJsp")
	 public ModelAndView editJsp(String id,ModelMap modelMap)
	    throws Exception
	 {
		modelMap.put("user",userService.getUserById(id));
	    return new ModelAndView("system/userEdit",modelMap);
	 }
	//跳转编辑页面
	@RequestMapping("/detail")
	 public ModelAndView detail(String id,ModelMap modelMap)
	    throws Exception
	 {
		modelMap.put("user",userService.getUserById(id));
	    return new ModelAndView("system/userDetail",modelMap);
	 }
	//跳转修改密码页面
	@RequestMapping("/changePassWord")
	 public ModelAndView changePassWord(String id,ModelMap modelMap)
	    throws Exception
	 {
		modelMap.put("user",userService.getUserById(id));
	    return new ModelAndView("system/userPass",modelMap);
	 }
	@RequestMapping("/edit")
	@ResponseBody
	public int edit(User u){
		return userService.updateUser(u);
	} 
	@RequestMapping("/editPass")
	@ResponseBody
	public int editPass(User u){
		return userService.updatePass(u);
	} 
}