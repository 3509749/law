package com.sc.aqjl.base.sevice.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.sc.aqjl.base.mapper.ItsSystemMapper;
import com.sc.aqjl.base.sevice.ItsSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ItsSystemServiceImpl implements ItsSystemService {
	@Resource
	private ItsSystemMapper itsSystemMapper;

	public String getSeqIdBySeqName(String seqName) {
		Map<String, String> map =new HashMap<String, String>();
		map.put("seqName", seqName);
	    return itsSystemMapper.getSeqIdBySeqName(map);
	}

	public String getSystemTime() {
		// TODO Auto-generated method stub
		return  itsSystemMapper.getSystemTime();
	}

}
