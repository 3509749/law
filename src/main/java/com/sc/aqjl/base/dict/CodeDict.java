package com.sc.aqjl.base.dict;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.aqjl.SpringUtil;
import com.sc.aqjl.base.model.DictItem;
import com.sc.aqjl.base.model.DictTable;
import com.sc.aqjl.base.sevice.DictService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@ComponentScan(basePackages = {"com.sc.aqjl.admin"})
public class CodeDict {
	
	public static DictService s_dmDataSource;
	
	private static CodeDict dmdict;
	//需要定期检查代码表的更新状态。在系统配置表里添加一个配置项（long类型），用来记录字典表的更新，如果获取的值比当前加载时的值大，则清空缓存池，所有代码表从新加载
	public void clear(){
		tables.clear();
	}
	
	public void clear(String tableName){
		tables.remove(tableName);
	}
	
	public synchronized static CodeDict getInstance(){
		if(dmdict == null){
			dmdict = new CodeDict();
			s_dmDataSource = (DictService) SpringUtil.getBean(DictService.class);
		}
		return dmdict;
	}
	
	private CodeDict(){
		tables = new HashMap<String,DictTable>();
	}
	
	private Map<String,DictTable> tables;//tableName,DMTable
	
	/**
	 * 根据类名获取字典代码值对应的名称值
	 * dmClazz必须包含DictTableInfoAnnotation注解
	 * 功能描述：
	 * @param dmClazz dmClazz必须包含DictTableInfoAnnotation注解
	 */
	public String getItemName(Class<?> dmClazz, Object code){
		DictTableInfoAnnotation anno = dmClazz.getAnnotation(DictTableInfoAnnotation.class);
		if(anno != null){
			String codeName = getItemName(anno.TableName(), anno.CodeField(),anno.NameField(),anno.OrderField(),code, true);
			return codeName;
		}else return code.toString();
	}
	
	/**
	 * 
	 * 功能描述：
	 * @param tableName     字典表名
	 * @param codeField     代码字段名
	 * @param nameField     名称字段名
	 * @param orderByField  排序字段名，可以与codeField相同
	 * @param code      代码值
	 * @param useCache  代码表是否进行缓存，缓存可以获取较高的性能，但是不能获取缓存之后更新的数据
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	public String getItemName(String tableName, String codeField,String nameField, String orderByField, Object code, boolean useCache){
		if(null == code) return null;
		return getItemName(tableName, codeField,nameField, "", orderByField, code, useCache);
	}
	
	public String getItemName(String tableName, String codeField,String nameField, String parentField, String orderByField, Object code, boolean useCache){
		if(null == code) return null;
		if(useCache){
			return getCachedItemName(tableName,codeField,nameField,parentField,orderByField,code);
		}else{
			return getRealtimeItemName(tableName,codeField,nameField,code);
		}
	}
	
	public String getItemNames(String tableName, String codeField,
			String nameField, String orderByField, Object codes, boolean useCache){
		if(null == codes) return null;
		return getItemNames(tableName, codeField,
				nameField, "", orderByField, codes, useCache);
	}
	
	public String getItemNames(String tableName, String codeField,
			String nameField, String parentField, String orderByField, Object codes, boolean useCache){
		if(null == codes) return null;
		return getRealtimeItemNames(tableName,codeField,nameField,codes);
	}
	/**
	 * 根据表名，代码，获取代码对应的名称
	 * 功能描述：
	 * @param tableName 代码表名
	 * @param code 代码
	 * @return 名称
	 * @版本 1.0
	 * 修改描述
	 */
	public String getCachedItemName(String tableName,String codeField,
			String nameField, String orderByField, Object code){
		if(null == code) return null;
		return getCachedItemName(tableName, codeField, nameField, "", orderByField, code);
	}
	public String getCachedItemName(String tableName, String codeField,String nameField, String parentField, String orderByField, Object code){
		DictTable dictTable = new DictTable(tableName,codeField,nameField,parentField,orderByField,code.toString());
		if(s_dmDataSource == null)return "";
		if(code == null || code.toString().length()==0)return "";
		tableName = tableName.toUpperCase();
		DictTable table = tables.get(tableName);
		if(table == null){
			synchronized(dmdict){
				if(table == null){
					try{
					table = s_dmDataSource.fillDMTable(dictTable);
					tables.put(tableName, table);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
		if(table != null){
			return table.getName(code);
		}
		return code.toString();
	} 
	
	/***
	 * 每次都从数据库中获取名称
	 * 功能描述：
	 * @param tableName
	 * @param codeField
	 * @param nameField
	 * @param code
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	public String getRealtimeItemName(String tableName,String codeField,String nameField, Object code){
		DictTable dictTable = new DictTable(tableName,codeField,nameField,"","",code.toString());
		if(s_dmDataSource == null)return "";
		if(code == null)return "";
		String name = s_dmDataSource.getCodeName(dictTable);
		return name;
	}
	
	public String getRealtimeItemNames(String tableName,String codeField,String nameField, Object codes){
		DictTable dictTable = new DictTable(tableName,codeField,nameField,"","",codes.toString());
		if(s_dmDataSource == null)return "";
		if(codes == null)return "";
		String name = s_dmDataSource.getCodeNames(dictTable);
		return name;
	}
	/**
	 * 获取指定表的所有字典值的列表
	 * dmClazz必须包含DictTableInfoAnnotation注解
	 * 功能描述：
	 * @param dmClazz dmClazz必须包含DictTableInfoAnnotation注解
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	public List<DictItem> getItems(Class<?> dmClazz, boolean userCache){
		DictTableInfoAnnotation anno = dmClazz.getAnnotation(DictTableInfoAnnotation.class);
		if(anno != null){
			List<DictItem> list = getItems(anno.TableName(), anno.CodeField(),
					anno.NameField(),anno.ParentField(), anno.OrderField(), userCache);
			return list;
		}else return null;
	}
	
	/**
	 * 获取指定表的所有字典值的列表
	 * 功能描述：
	 * @param tableName
	 * @param codeField
	 * @param nameField
	 * @param orderByField
	 * @param userCache 是否使用缓存数据
	 * @return
	 * @版本 1.0
	 * 修改描述
	 */
	public List<DictItem> getItems(String tableName , String codeField, String nameField, String parentField, String orderByField, boolean userCache){
		DictTable dictTable = new DictTable(tableName,codeField,nameField,parentField,orderByField,"");
		if(s_dmDataSource == null)return null; 
		tableName = tableName.toUpperCase();
		DictTable table = null;
		if(userCache){
			table = tables.get(tableName);
		}
		if(table == null){
			synchronized(dmdict){
				if(table == null){
					try{
					table = s_dmDataSource.fillDMTable(dictTable);
					tables.put(tableName, table);
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
	
	public List<DictItem> getItemsByWhereCase(String tableName , String codeField,String nameField,String whereCase,String parentField, String orderByField, boolean userCache){
		DictTable dictTable = new DictTable(tableName,codeField,nameField,parentField,orderByField,"");
		if(s_dmDataSource == null)return null; 
		tableName = tableName.toUpperCase();
		DictTable table = null;
		if(userCache){
			table = tables.get(tableName);
		}
		if(table == null){
			synchronized(dmdict){
				if(table == null){
					try{
					table = s_dmDataSource.fillDMTableByWhereCase(dictTable,whereCase);
					tables.put(tableName, table);
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
	/**获取指定表的所有字典值的列表(使用缓存)*/
	public List<DictItem> getItems(String tableName , String codeField, String nameField,
								   String parentField, String orderByField){
		return getItems( tableName ,  codeField, nameField, parentField, orderByField, true);
	}
	
	public void clearDMTable(String tableName){
		tableName = tableName.toUpperCase();
		tables.remove(tableName);
	}
	
	public String createHtmlSelectOptionsByWhereCase(String dmTableName, String codeField, String nameField,String WhereCase, String orderField, String selectedValue, boolean userCache){
		return createHtmlSelectOptionsByWhereCase(dmTableName, codeField, nameField,WhereCase, "", orderField, selectedValue, userCache);
	}
	
	public  String createHtmlSelectOptionsByWhereCase(String dmTableName, String codeField, String nameField,String WhereCase, String parentField, String orderField, String selectedValue, boolean userCache){
		StringBuilder sbText = new StringBuilder();
		List<DictItem> items = getItemsByWhereCase(dmTableName, codeField, nameField,WhereCase, parentField, orderField, userCache);
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
	
	public  String createHtmlSelectOptions_CODE_CODE_TEXT_ByWhereCase(String dmTableName, String codeField, String nameField,String WhereCase, String parentField, String orderField, String selectedValue, boolean userCache){
		StringBuilder sbText = new StringBuilder();
		List<DictItem> items = getItemsByWhereCase(dmTableName, codeField, nameField,WhereCase, parentField, orderField, userCache);
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
				sbText.append(item.getCode()).append("\">").append("["+item.getCode()+"]"+item.getName()).append("</option>");
			}
		}
		
		return sbText.toString();
	}
	public  String createHtmlSelectOptions_TEXT_CODE_TEXT_ByWhereCase(String dmTableName, String codeField, String nameField,String WhereCase, String parentField, String orderField, String selectedValue, boolean userCache){
		StringBuilder sbText = new StringBuilder();
		List<DictItem> items = getItemsByWhereCase(dmTableName, codeField, nameField,WhereCase, parentField, orderField, userCache);
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
				sbText.append(item.getName()).append("\">").append("["+item.getCode()+"]"+item.getName()).append("</option>");
			}
		}
		
		return sbText.toString();
	}
	public String createHtmlSelectOptions(String dmTableName, String codeField, String nameField, String orderField, String selectedValue, boolean userCache){
		return createHtmlSelectOptions(dmTableName, codeField, nameField, "","" , orderField, selectedValue, userCache);
	}
	
	@SuppressWarnings("unused")
	public  String createHtmlSelectOptions(String dmTableName, String codeField, String nameField, String parentField, String parentValue, String orderField, String selectedValue, boolean userCache){
		StringBuilder sbText = new StringBuilder();
		List<DictItem> items = getItems(dmTableName, codeField, nameField, parentField, orderField, userCache);
		if (items==null)
		{
			return null;
		} else
		{
			for(DictItem item : items){
				if(null == parentValue || "" == parentValue){
					String selected = "";
					if(selectedValue != null && selectedValue.equals(item.getCode())){
						selected = "selected=\"selected\"";
					}
					sbText.append("<option data-parent-code=\"").append(item.getParentCode()).append("\"").append(selected).append(" value=\"");
					sbText.append(item.getCode()).append("\">").append(item.getName()).append("</option>");
				}else{
					if(null != item.getParentCode() && item.getParentCode().equals(parentValue)){
						String selected = "";
						if(selectedValue != null && selectedValue.equals(item.getCode())){
							selected = "selected=\"selected\"";
						}
						sbText.append("<option data-parent-code=\"").append(item.getParentCode()).append("\"").append(selected).append(" value=\"");
						sbText.append(item.getCode()).append("\">").append(item.getName()).append("</option>");
					}
				}
			}
		}
		
		return sbText.toString();
	}
	public String createHtmlSelectOptions(String dmTableName, String codeField, String nameField, String orderField, String selectedValue){
		return createHtmlSelectOptions(dmTableName, codeField, nameField, "","" , orderField, selectedValue, false);
	}
	
	public String createHtmlSelectOptions(String dmTableName, String codeField, String nameField, String parentField,String parentValue, String orderField, String selectedValue){
		return createHtmlSelectOptions(dmTableName, codeField, nameField, parentField,parentValue , orderField, selectedValue, false);
	}
}
