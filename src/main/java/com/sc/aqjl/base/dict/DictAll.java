package com.sc.aqjl.base.dict;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.aqjl.SpringUtil;
import com.sc.aqjl.base.model.DictItem;
import com.sc.aqjl.base.model.DictTable;
import com.sc.aqjl.base.sevice.DictService;

/**功能描述 
 * @类型名称 DictAll
 * @版本 1.0
 */
public class DictAll{
	private static Object lockObj = new Object();

	public static DictService getDmDataSource() {
		return (DictService) SpringUtil.getBean(DictService.class);
	}

	
	private static String dictTableName = "";
	private static String codeTypeField = "";
	private static String codeField = "";
	private static String nameField = "";
	private static String parentField = "";
	private static String orderByField = "";
	
	private static Map<String,DictTable> tables = new HashMap<String,DictTable>();//tableName,DMTable
	
	public static void clear(){
		tables.clear(); 
	}
	
	/**
	 * 根据码表中的编码获取名称
	 * 功能描述：
	 * @param codeType 编码类型
	 * @param code  编码值
	 * @return 编码对应的名称
	 * @版本 1.0
	 * @创建者 wanghua
	 * @创建时间 2013-12-25 下午2:37:17
	 * @版权所有 ©2013 CTFO
	 * @修改者 wanghua
	 * @修改时间 2013-12-25 下午2:37:17
	 * 修改描述
	 */
	public static String getItemName(String codeType, String code){
		return getCachedItemName(codeType, code);
	}
	
	/**
	 * 根据表名，代码，获取代码对应的名称
	 * 功能描述：
	 * @param code 代码
	 * @return 名称
	 * @版本 1.0
			* @创建者 wanghua
	 * @创建时间 2012-4-19 下午04:55:22
			* @版权所有 ©2012 CTFO
	 * @修改者 wanghua
	 * @修改时间 2012-4-19 下午04:55:22
			* 修改描述
	 */
	public static String getCachedItemName(String codeType, String code){		
		DictItem item = getCachedItem(codeType, code);
		if(item == null)return code;
		else return item.getName(); 
	} 
	
	/** 根据表名，代码，获取代码对应的DMItem
	 * 功能描述：
	 * @param code 代码
	 * 修改描述
	 */
	public static DictItem getCachedItem(String codeType, String code){
		DictTable dictTable = new DictTable();
		dictTable.setTypeField(codeType);
		if(getDmDataSource() == null)return null;
		if(code == null || code.toString().length()==0)return null;
		 
		DictTable table = tables.get(codeType);
		if(table == null){
			synchronized(lockObj){
				if(table == null){
					try{
						table = getDmDataSource().fillDMTableFromDictAll(dictTable);
						tables.put(codeType, table);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
		if(table != null){
			return table.getItem(code);
		}
		return null;
	} 
	 
	/**
	 * 获取指定表的所有字典值的列表
	 * dmClazz必须包含CodeTableInfoAnnotation注解
	 * 功能描述：
	 * @param dmClazz dmClazz必须包含CodeTableInfoAnnotation注解
	 * @return 
	 * @版本 1.0
	 * @创建者 wanghua
	 * @创建时间 2012-4-24 上午11:45:59
	 * @版权所有 ©2012 CTFO
	 * @修改者 wanghua
	 * @修改时间 2012-4-24 上午11:45:59
	 * 修改描述
	 */
	public List<DictItem> getItems(Class<?> dmClazz){
		DictTableInfoAnnotation anno = dmClazz.getAnnotation(DictTableInfoAnnotation.class);
		if(anno != null){
			List<DictItem> list = getItems(anno.TableName());
			return list;
		}else return null;
	}
	
	public static List<DictItem> getItems(String codeType, boolean userCache){
		DictTable dictTable = new DictTable();
		dictTable.setTypeField(codeType);
		if(getDmDataSource() == null)return null; 
		codeType = codeType.toUpperCase();
		DictTable table = null;
		if(userCache){ //如果使用缓存，则首先从缓存中获取数据，否则从数据库中读取数据
			table = tables.get(codeType);
		}
		if(table == null){
			synchronized(DictAll.class){
				if(table == null){	
					try{
						table = getDmDataSource().fillDMTableFromDictAll(dictTable);
						tables.put(codeType, table);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
		if(table != null){
			return table.getItemlist();
		}else return null;
	}
	
	/**获取指定表的所有字典值的列表（不使用缓存数据）*/
	public static List<DictItem> getItems(String codeType){
		return getItems(codeType, false);
	}
	
	public static void clearDMTable(String codeType){
		codeType = codeType.toUpperCase();
		tables.remove(codeType);
	}
	
	public static String createHtmlSelectOptions(String codeType, String selectedValue, boolean useCache){
		StringBuilder sbText = new StringBuilder();
		List<DictItem> items = getItems(codeType,useCache);
		if (items==null)
		{
			return null;
		} else
		{
			for(DictItem item : items){
				String selected = "";
				if(selectedValue != null && selectedValue.equals(item.getCode())){
					selected = "selected=\"selected\"";
				}
				sbText.append("<option ").append(selected).append(" value=\"");
				sbText.append(item.getCode()).append("\">").append(item.getName()).append("</option>");
			}
		}
		
		return sbText.toString();
	}
	public static String createHtmlSelectOptions(String dmTableName, String codeField, String nameField, String parentField, String orderField, String selectedValue){
		StringBuilder sbText = new StringBuilder();
		List<DictItem> items = CodeDict.getInstance().getItems(dmTableName, codeField, nameField, parentField, orderField,false);
		if (items==null)
		{
			return null;
		} else
		{
			for(DictItem item : items){
				String selected = "";
				if(selectedValue != null && selectedValue.equals(item.getCode())){
					selected = "selected=\"selected\"";
				}
				sbText.append("<option ").append(selected).append(" value=\"");
				sbText.append(item.getCode()).append("\">").append(item.getName()).append("</option>");
			}
		}
		
		return sbText.toString();
	}
	public static String createHtmlSelectOptions(String codeType, String selectedValue){
		return createHtmlSelectOptions(codeType, selectedValue, true);
	}
	
	/**
	 * 获取行政区编号的有效位，去掉后面的无效的00
	 * 如 510200->5102   510000->51   510301->510301
	 * 
	 * 功能描述：
	 * @param districtId
	 * @return 
	 * @版本 1.0
	 * @创建者 wanghua
	 * @创建时间 2014-3-22 下午2:16:08
	 * @版权所有 ©2014 CTFO
	 * @修改者 wanghua
	 * @修改时间 2014-3-22 下午2:16:08
	 * 修改描述
	 */
	public static String getPrixDistrictId(String districtId){
		if(districtId == null)return "";
		if(districtId.length() == 9 && districtId.endsWith("000")){			
			districtId = districtId.substring(0,6);
		}
		
		if(districtId.length() == 6 && districtId.endsWith("00")){			
			districtId = districtId.substring(0,4);
		}
		
		if(districtId.length() == 4 && districtId.endsWith("00")){			
			districtId = districtId.substring(0,2);
		}
		
		if(districtId.length() == 2 && districtId.endsWith("00")){			
			districtId = "";
		}
		
		return districtId;
	}

	public String getDictTableName() {
		return dictTableName;
	}

	public void setDictTableName(String dictTableName) {
		DictAll.dictTableName = dictTableName;
	}
	 

	public String getCodeTypeField() {
		return codeTypeField;
	}

	public void setCodeTypeField(String codeTypeField) {
		DictAll.codeTypeField = codeTypeField;
	}

	public static String getCodeField() {
		return codeField;
	}

	public void setCodeField(String codeField) {
		DictAll.codeField = codeField;
	}

	public static String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		DictAll.nameField = nameField;
	}
	public static String getParentField() {
		return parentField;
	}

	public static void setParentField(String parentField) {
		DictAll.parentField = parentField;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		DictAll.orderByField = orderByField;
	}

}
