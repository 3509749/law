package com.sc.aqjl.base.sevice;


import com.sc.aqjl.base.model.DictTable;

public interface DictService {
	public DictTable fillDMTable(DictTable d);
	
	public DictTable fillDMTableByWhereCase(DictTable d, String whereCase);
	
	public DictTable fillDMTableFromDictAll(DictTable d);
		
	public String getCodeName(DictTable d);
	
	public String getCodeNames(DictTable d);
}
