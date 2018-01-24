package com.sc.aqjl.admin.model;

public class RoleResource {
	private String id;
	private String roleId;
	private String resourceId;
	
	public RoleResource(){
		super();
	}
	public RoleResource(String rId,String resId){
		this.roleId = rId;
		this.resourceId = resId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	
	
}
