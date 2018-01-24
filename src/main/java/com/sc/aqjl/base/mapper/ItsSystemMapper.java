package com.sc.aqjl.base.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper  
public   interface ItsSystemMapper
{
  public  String getSeqIdBySeqName(Map<String, String> map);
  public  String getSystemTime();
}