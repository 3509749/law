package com.sc.aqjl.base.sevice.impl;

import java.util.List;

import com.sc.aqjl.base.mapper.DictMapper;
import com.sc.aqjl.base.model.DictItem;
import com.sc.aqjl.base.model.DictTable;
import com.sc.aqjl.base.sevice.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
// 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class DictServiceImpl  implements DictService {
	
	@Autowired
    private DictMapper dictMapper;

	@Override
	public DictTable fillDMTable(DictTable d) {
		List<DictItem> list = dictMapper.getOtherTable(d);
		d.setItemlist(list);
		return d;
	}

	@Override
	public DictTable fillDMTableByWhereCase(DictTable d,String whereCase) {
		d.setWhereCase(whereCase);
		List<DictItem> list = dictMapper.getOtherTable(d);
		d.setItemlist(list);
		return d;
	}

	@Override
	public DictTable fillDMTableFromDictAll(DictTable d) {
		List<DictItem> list = dictMapper.getDict(d);
		d.setItemlist(list);
		return d;
	}

	@Override
	public String getCodeName(DictTable d) {
		return dictMapper.getOtherTableByField(d);
	}

	@Override
	public String getCodeNames(DictTable d) {
		// TODO Auto-generated method stub
		return null;
	}
}
