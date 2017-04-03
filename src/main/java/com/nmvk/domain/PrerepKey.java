package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrerepKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PrerepKey() {
		
	}
	@Column(name = "CID")
	int CID;

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public int getReq_cid() {
		return req_cid;
	}

	public void setReq_cid(int req_cid) {
		this.req_cid = req_cid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name = "REQ_CID")
	int req_cid;

	
}