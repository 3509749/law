package com.sc.aqjl.business.yw.service.impl;

import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.business.yw.mapper.MajorRoadMapper;
import com.sc.aqjl.business.yw.mapper.SafeCheckMapper;
import com.sc.aqjl.business.yw.model.ANJCMXB;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.model.AQXCJS;
import com.sc.aqjl.business.yw.model.DLZDJKZB;
import com.sc.aqjl.business.yw.service.MajorRoadService;
import com.sc.aqjl.business.yw.service.SafeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MajorRoadServiceImpl implements MajorRoadService {


    @Autowired
    private MajorRoadMapper majorRoadMapper;

    public PageBean getMajorRoadList(DLZDJKZB model, int page, int limit) {
        List list = majorRoadMapper.getMajorRoadList(model);
        int countNums = majorRoadMapper.getCount(model);
        PageBean<AQXCJS> pageData = new PageBean(page, limit, countNums);
        pageData.setItems(list);
        pageData.setPageSize(countNums);
        return pageData;
    }
}
