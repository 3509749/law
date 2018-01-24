package com.sc.aqjl.business.yw.service;


import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.model.DLZDJKZB;

import java.util.List;


public interface MajorRoadService {
	public PageBean getMajorRoadList(DLZDJKZB a, int page, int limit);
}
