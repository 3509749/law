package com.sc.aqjl.business.yw.service.impl;

import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.business.yw.mapper.SafeCheckMapper;
import com.sc.aqjl.business.yw.model.ANJCMXB;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.model.AQXCJS;
import com.sc.aqjl.business.yw.service.SafeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SafeCheckServiceImpl implements SafeCheckService {


    @Autowired
    private SafeCheckMapper safeCheckMapper;

    public PageBean getSafeCheckList(AQJCZB model, int page, int limit) {
        List list = safeCheckMapper.getSafeCheckList(model);
        int countNums = safeCheckMapper.getCount(model);
        PageBean<AQXCJS> pageData = new PageBean(page, limit, countNums);
        pageData.setItems(list);
        pageData.setPageSize(countNums);
        return pageData;
    }

    @Override
    public AQJCZB getSafeCheckData(String id) {
        if(id.equals("0"))
            return new AQJCZB();
        return safeCheckMapper.getSafeCheckList(new AQJCZB(id)).get(0);
    }

    @Override
    public int insertAQJCZB(AQJCZB a) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        a.setJcdh(sdf.format(new Date()).toString());
        for(ANJCMXB temp :a.getList() ){
            temp.setJcdh(a.getJcdh());
            temp.setCzy(a.getCzy());
            temp.setJcdh(a.getJcdh());
        }
        int i = safeCheckMapper.insertAQJCZB(a);
        safeCheckMapper.insertANJCMXB(a.getList());
        return i;
    }

    @Override
    public int updateAQJCZB(AQJCZB a) {
        for(ANJCMXB temp :a.getList() ){
            temp.setJcdh(a.getJcdh());
            temp.setCzy(a.getCzy());
            temp.setJcdh(a.getJcdh());
        }
        int i = safeCheckMapper.updateAQJCZB(a);
        String[] strArray={a.getJcdh()+""};
        safeCheckMapper.deleteANJCMXB(strArray);
        safeCheckMapper.insertANJCMXB(a.getList());
        return i;
    }

    public int del(String[] ids) {
        safeCheckMapper.deleteANJCMXB(ids);
        return safeCheckMapper.deleteAQJCZB(ids);
    }
}
