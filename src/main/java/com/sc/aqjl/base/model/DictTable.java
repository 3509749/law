package com.sc.aqjl.base.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictTable {
	private String tableName; 
	private String idField;
	private String valueField;
	private String codeField = "dictcode";
	private String typeField = "dicttypecode";
	private String nameField = "dictname";
	private String orderByField = "";
	private List<DictItem> itemList;
	private Map<String,DictItem> items;
	private String parentField = "";
	private String parentValue = "";
	private String whereCase;
	private String selectValue;
	private Boolean cache = false;
	
	public DictTable(){
		itemList = new ArrayList<DictItem>();
		items = new HashMap<String,DictItem>();
	}



	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public DictTable(int value){
		itemList = new ArrayList<DictItem>();
		items = new HashMap<String,DictItem>();
		this.valueField = value+"";
	}


	public DictTable(String tableName){
		itemList = new ArrayList<DictItem>();
		this.tableName = tableName;
		items = new HashMap<String,DictItem>();
	}
	
	public DictTable(String tableName, String codeField, String nameField,String parentField, String orderByField,String parentValue) {
		this.tableName = tableName;
		this.codeField = codeField;
		this.nameField = nameField;
		this.orderByField = orderByField;
		this.parentField = parentField;
		this.parentValue = parentValue;
	}

	/**
	 * 添加一个字典项
	 * 功能描述：
	 */
	public void addDMItem(DictItem item){
		itemList.add(item);
	}
	
	/**
	 * 根据编码获取名称
	 * 功能描述：
	 * @param code 编码值，可以是数字或者字符串。这里code定义为Object类型
	 */
	public String getName(Object code){		 
		DictItem item = getItem(code);
		if(item == null)return code.toString();
		else return item.getName();
	}
	
	/** 根据编码获取DMItem
	 * 功能描述：
	 * @param code 编码值，可以是数字或者字符串。这里code定义为Object类型
	 */
	public DictItem getItem(Object code){
		if(code == null)return null;
		if(items == null)items = new HashMap<String,DictItem>();
		if(items.size()==0 && itemList.size()>0){
			synchronized(this){
				if(items.size()==0 && itemList.size()>0){
					for(DictItem item : itemList){
						items.put(item.getCode().toString(), item);
					}
				}
			}
		}
		DictItem item = items.get(code.toString());
		return item;
	}
	
	public String getTableName(){
		return tableName;
	}

	public List<DictItem> getItemlist() {
		return itemList;
	}

	public void setItemlist(List<DictItem> itemlist) {
		this.itemList = itemlist;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getCodeField() {
		return codeField;
	}

	public void setCodeField(String codeField) {
		this.codeField = codeField;
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}


	public String getTypeField() {
		return typeField;
	}

	public void setTypeField(String typeField) {
		this.typeField = typeField;
	}

	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}

	public String getParentValue() {
		return parentValue;
	}

	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}

	public String getWhereCase() {
		return whereCase;
	}

	public void setWhereCase(String whereCase) {
		this.whereCase = whereCase;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public Boolean getCache() {
		return cache;
	}

	public void setCache(Boolean cache) {
		this.cache = cache;
	}
	 
	
}
