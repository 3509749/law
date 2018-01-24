package com.sc.aqjl.business.yw.service;


import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.business.yw.model.AQXCJS;

import java.util.List;


public interface SafeDrivingService {
	public PageBean getSafeDrivingList(AQXCJS a, int page, int limit);
	public AQXCJS getSafeDrivingData(int id);
	public int insert(AQXCJS a);
	public int update(AQXCJS a);
	public int del(String[] ids);
}
