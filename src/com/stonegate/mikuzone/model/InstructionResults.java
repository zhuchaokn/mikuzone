package com.stonegate.mikuzone.model;

import java.io.Serializable;

public class InstructionResults implements Serializable{
	private static final long serialVersionUID = 84729797034189073L;
	private boolean isRemoteRes;
	private String [] results;
	public String[] getResults() {
		return results;
	}
	public boolean isRemoteRes() {
		return isRemoteRes;
	}
	public void setRemoteRes(boolean isRemoteRes) {
		this.isRemoteRes = isRemoteRes;
	}
	public void setResults(String[] results) {
		this.results = results;
	}
}
