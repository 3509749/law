package com.sc.aqjl.admin.model;
import java.io.Serializable;
import java.util.List;



/**
 * @Title: UPermission.java 
 * @Package com.xm.shiro.admin.entity
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author bamboo  <a href=
 *         "mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题："
 *         Bamboo</a>   
 * @date 2017-5-10 0:00:45
 * @version V1.0   
 */
public class UPermission  implements Serializable {
	 /** serialVersionUID. */
 	private static final long serialVersionUID =1493049839167L;
 	
 	  private String id;
 	  private String name;
 	  private Short type;
 	  private String parentId;
 	  private List<UPermission> childs;
 	  private String url;
 	  private String notes;
 	  private Integer orderNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public List<UPermission> getChilds() {
		return childs;
	}
	public void setChilds(List<UPermission> childs) {
		this.childs = childs;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	  
	
}
