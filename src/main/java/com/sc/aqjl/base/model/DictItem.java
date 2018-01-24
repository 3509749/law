package com.sc.aqjl.base.model;


public class DictItem { 
	private String code;
	private String name;
	private String parentCode; 
	private long ordnum = 99999999;
	public DictItem(){
		
	}
	public DictItem(String code,String name){
		this.code = code;
		this.name = name;
	} 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null || code == null)return false;
		DictItem item2 = (DictItem)other;		
		return code.equals(item2.code);
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public int hashCode(){
		if(code == null)return 0;
		return code.hashCode();
	}
	public long getOrdnum() {
		return ordnum;
	}
	public void setOrdnum(long ordnum) {
		this.ordnum = ordnum;
	}
}
