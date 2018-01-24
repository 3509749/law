package com.sc.aqjl.business.yw.mapper;

import com.sc.aqjl.business.yw.model.AQXCJS;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SafeDrivingMapper {
    public  List<AQXCJS> getSafeDrivingList(AQXCJS a);
    public  int getCount(AQXCJS a);
    public int insert(AQXCJS a);
    public int delete(String[] ids);
    public int update(AQXCJS a);

}
