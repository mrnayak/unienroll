package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PreConKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PreConKey() {
		
	}
	@Column(name = "CID")
	int CID;

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name = "TAG")
	int tag;
	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	
}