package com.sc.aqjl.admin.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sc.aqjl.base.dict.CodeDict;



/**
 * @Title: UUser.java 
 * @Package com.xm.shiro.admin.entity
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author bamboo  <a href=
 *         "mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题："
 *         Bamboo</a>   
 * @date 2017-5-10 0:13:08
 * @version V1.0   
 */
public class UUser  implements Serializable {
	 /** serialVersionUID. */
 	private static final long serialVersionUID =1493049839167L;
 	
	  private String id;
	  private String loginName;
	  private String name;
	  private String password;
	  private String coNo;
	  private String dwNo;
	  private String addressNo;
	  private String coName;
	  private String dwName;
	  private String addressName;
	  private String role;
	  private String roName;
	  private List<String> roleStrlist;
	  private List<String> perminsStrlist;

	  public String getRoName() {
		return CodeDict.getInstance().getItemName("PUB_ROLE","ID","NAME","",this.role,true);
	  }

		public void setRoName(String roName) {
			this.roName = roName;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		} 
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getCoName() {
		return  CodeDict.getInstance().getItemName("SYN_CO","CONO","CONAME","",this.coNo,true);
	}

	public String getDwNo() {
		return dwNo;
	}
	public void setDwNo(String dwNo) {
		this.dwNo = dwNo;
	}
	public String getDwName() {
		return CodeDict.getInstance().getItemName("BX_XLDW","DWBH","DWMC","",this.dwNo,true);
	}
	public void setDwName(String dwName) {
		this.dwName = dwName;
	}
	public String getAddressName() {
		return CodeDict.getInstance().getItemName("BX_WXDD","DDBH","WXDD","",this.addressNo,true);
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCoNo() {
		return coNo;
	}
	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}
	 
	public String getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}
	 
	public List<String> getRoleStrlist() {
		return roleStrlist;
	}

	public void setRoleStrlist(List<String> roleStrlist) {
		this.roleStrlist = roleStrlist;
	}

	public List<String> getPerminsStrlist() {
		return perminsStrlist;
	}

	public void setPerminsStrlist(List<String> perminsStrlist) {
		this.perminsStrlist = perminsStrlist;
	}

	

	
	
	
}
