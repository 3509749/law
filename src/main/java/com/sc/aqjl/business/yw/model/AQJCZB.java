package com.sc.aqjl.business.yw.model;

import com.sc.aqjl.base.dict.CodeDict;

import java.util.Date;
import java.util.List;

public class AQJCZB {

	private String jcdh = "0";
	private Date jsrq;
	private String jclx;
	private String zbgs;
	private String zbgsName;
	private String zbzd;
	private String zbzdName;
	private String jcrys;
	private String jcrylb;
	private int recordid;
	private String czy;
	private Date inputdate;

	private List<ANJCMXB> list;

	public AQJCZB() {
	}

	public AQJCZB(String jcdh) {
		this.jcdh = jcdh;
	}

	public List<ANJCMXB> getList() {
		return list;
	}

	public void setList(List<ANJCMXB> list) {
		this.list = list;
	}

	public String getJcdh() {
		return jcdh;
	}

	public void setJcdh(String jcdh) {
		this.jcdh = jcdh;
	}

	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}

	public String getJclx() {
		return jclx;
	}

	public void setJclx(String jclx) {
		this.jclx = jclx;
	}

	public String getZbgs() {
		return zbgs;
	}

	public void setZbgs(String zbgs) {
		this.zbgs = zbgs;
	}

	public String getZbzd() {
		return zbzd;
	}

	public void setZbzd(String zbzd) {
		this.zbzd = zbzd;
	}

	public String getJcrys() {
		return jcrys;
	}

	public void setJcrys(String jcrys) {
		this.jcrys = jcrys;
	}

	public String getJcrylb() {
		return jcrylb;
	}

	public void setJcrylb(String jcrylb) {
		this.jcrylb = jcrylb;
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public Date getInputdate() {
		return inputdate;
	}

	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}

	public String getZbgsName() {
		return  CodeDict.getInstance().getItemName("SYN_CO","CONO","CONAME","",this.zbgs,true);
	}

	public void setZbgsName(String zbgsName) {
		this.zbgsName = zbgsName;
	}

	public String getZbzdName() {
		return  CodeDict.getInstance().getItemName("TB_BUSCREW","buscrewno","buscrewname","",this.zbzd,true);
	}

	public void setZbzdName(String zbzdName) {
		this.zbzdName = zbzdName;
	}
}