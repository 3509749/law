package com.sc.aqjl.admin.model;
import com.sc.aqjl.base.dict.CodeDict;

public class User {
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
 
}