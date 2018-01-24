package com.sc.aqjl.business.yw.service.impl;

import com.github.pagehelper.PageHelper;
import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.business.yw.mapper.SafeDrivingMapper;
import com.sc.aqjl.business.yw.model.AQXCJS;
import com.sc.aqjl.business.yw.service.SafeDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SafeDrivingServiceImpl implements SafeDrivingService {


    @Autowired
    private SafeDrivingMapper safeDrivingMapper;

    public PageBean getSafeDrivingList(AQXCJS model, int page, int limit) {
        List list = safeDrivingMapper.getSafeDrivingList(model);
        int countNums = safeDrivingMapper.getCount(model);
        PageBean<AQXCJS> pageData = new PageBean(page, limit, countNums);
        pageData.setItems(list);
        pageData.setPageSize(countNums);
        return pageData;
    }

    public AQXCJS getSafeDrivingData(int id){
        if(id == 0)
            return new AQXCJS();
        return safeDrivingMapper.getSafeDrivingList(new AQXCJS(id)).get(0);
    }

    @Override
    public int insert(AQXCJS a) {
        return safeDrivingMapper.insert(a);
    }

    @Override
    public int update(AQXCJS a) {
        return safeDrivingMapper.update(a);
    }

    public int del(String[] ids) {
        return safeDrivingMapper.delete(ids);
    }
}
