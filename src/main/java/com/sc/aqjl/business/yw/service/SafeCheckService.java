package com.sc.aqjl.business.yw.service;


import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.model.AQXCJS;


public interface SafeCheckService {
	public PageBean getSafeCheckList(AQJCZB a, int page, int limit);
	public AQJCZB getSafeCheckData(String id);
	public int insertAQJCZB(AQJCZB a);
	public int updateAQJCZB(AQJCZB a);

	public int del(String[] ids);
}
