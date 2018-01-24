package com.sc.aqjl.base.mapper;
 
import java.util.List;

import com.sc.aqjl.base.model.DictItem;
import com.sc.aqjl.base.model.DictTable;
import org.apache.ibatis.annotations.Mapper;


@Mapper  
public interface DictMapper {
	public List<DictItem> getOtherTable(DictTable d);
	public List<DictItem> getDict(DictTable d);
	public String getOtherTableByField(DictTable d);
	
}

