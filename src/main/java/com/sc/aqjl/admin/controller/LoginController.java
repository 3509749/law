package com.sc.aqjl.admin.controller;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sc.aqjl.admin.model.MainProps;
import com.sc.aqjl.admin.service.RoleService;
import com.sc.aqjl.admin.service.UserService;

 
@Controller
@RequestMapping("/")
public class LoginController {
	 private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired  
	private HttpSession session;  
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MainProps mainProps;

	/**s
	 * 跳转到主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public String login(ModelMap map){
	    return "login";
    }


	/**
	 * 跳转到主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(ModelMap modelMap){
		 modelMap.put("listParent",roleService.listParent());
		 modelMap.put("listChild",roleService.listChild());
		 modelMap.put("company",mainProps.getCompany());
		 return new ModelAndView("/main",modelMap);
    }
	@RequestMapping("/test")
	public void test(ModelMap map){
		System.out.println("test");
    }
//	@RequestMapping("/ajaxLogin")
//	public String ajaxLogin(HttpServletRequest request) {
//		System.out.println("---------------");
//		logger.info("进行登录验证..验证开始");
//		String loginName=request.getParameter("loginName");
//		String password=request.getParameter("password");
//		if (loginName!= null && password != null) {
//	            UsernamePasswordToken token = new UsernamePasswordToken(loginName,password, "login");
//	            Subject currentUser = SecurityUtils.getSubject();
//	            logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
//	            try {
//	                currentUser.login( token );
//	                //验证是否登录成功
//	                if (currentUser.isAuthenticated()) {
//	                    logger.info("用户[" +loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//	                    System.out.println("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//	                    return "redirect:/index";
//	                } else {
//	                    token.clear();
//	                    System.out.println("用户[" + loginName + "]登录认证失败,重新登陆");
//	                    return "redirect:/";
//	                }
//	            } catch ( UnknownAccountException uae ) {
//	                logger.info("对用户[" + loginName + "]进行登录验证..验证失败-username wasn't in the system");
//	            } catch ( IncorrectCredentialsException ice ) {
//	                logger.info("对用户[" + loginName+ "]进行登录验证..验证失败-password didn't match");
//	            } catch ( LockedAccountException lae ) {
//	                logger.info("对用户[" + loginName + "]进行登录验证..验证失败-account is locked in the system");
//	            } catch ( AuthenticationException ae ) {
//	                logger.error(ae.getMessage());
//	            }
//	        }
//	        return "login";
//	}
	 @RequestMapping(value="/logout",method=RequestMethod.GET)  
     public String logout(RedirectAttributes redirectAttributes ){ 
	        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
	        SecurityUtils.getSubject().logout();  
	        redirectAttributes.addFlashAttribute("message", "您已安全退出");  
	        return "redirect:/";
	 }
	  /**
	     * ajax登录请求接口方式登陆
	     * @param username
	     * @param password
	     * @return
	     */
	@RequestMapping("/ajaxLogin")
    @ResponseBody
    public Map<String,Object> submitLogin(HttpServletRequest request) {
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
        	UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {	
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

}