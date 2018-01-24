package com.sc.aqjl.business.yw.mapper;

import com.sc.aqjl.business.yw.model.ANJCMXB;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.model.DLZDJKZB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorRoadMapper {
    public  List<DLZDJKZB> getMajorRoadList(DLZDJKZB a);
    public int getCount(DLZDJKZB a);
}
