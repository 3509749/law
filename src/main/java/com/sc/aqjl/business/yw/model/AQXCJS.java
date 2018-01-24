package com.sc.aqjl.business.yw.model;
import com.sc.aqjl.base.dict.CodeDict;

import java.util.Date;

public class AQXCJS {
	private int recordid = 0;
	private String wzbt;
	private Date fbrq;
	private String cono;
	private String coname = "";
	private String nr = "";
	private String sfyd ="";
	private String czy;
	private String fknr ="";
	private Date inputdate;

	public AQXCJS() {
	}

	public AQXCJS(int recordid) {
		this.recordid = recordid;
	}

	public String getFknr() {
		return fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getWzbt() {
		return wzbt;
	}

	public void setWzbt(String wzbt) {
		this.wzbt = wzbt;
	}

	public Date getFbrq() {
		return fbrq;
	}

	public void setFbrq(Date fbrq) {
		this.fbrq = fbrq;
	}

	public String getCono() {
		return cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	public String getConame() {
		return  CodeDict.getInstance().getItemName("SYN_CO","CONO","CONAME","",this.cono,true);
	}

	public void setConame(String coname) {
		this.coname = coname;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getSfyd() {
		return sfyd;
	}

	public void setSfyd(String sfyd) {
		this.sfyd = sfyd;
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
}