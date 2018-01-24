package com.sc.aqjl.business.yw.mapper;

import com.sc.aqjl.business.yw.model.ANJCMXB;
import com.sc.aqjl.business.yw.model.AQJCZB;
import com.sc.aqjl.business.yw.model.AQXCJS;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SafeCheckMapper {
    public  List<AQJCZB> getSafeCheckList(AQJCZB a);
    public int getCount(AQJCZB a);
    public int insertAQJCZB(AQJCZB a);
    public int updateAQJCZB(AQJCZB a);
    public int insertANJCMXB(List<ANJCMXB> list);

    public int deleteAQJCZB(String[] ids);
    public int deleteANJCMXB(String[] ids);
}
