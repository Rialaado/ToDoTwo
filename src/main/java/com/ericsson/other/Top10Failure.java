package com.ericsson.other;

import java.math.BigInteger;
import java.util.List;


public class Top10Failure {
	
	private String imsi;
	private BigInteger noOfFailure;
	

	public Top10Failure() {

	}

	public Top10Failure(String imsi, BigInteger failureCount) {
		this.imsi = imsi;
		this.noOfFailure = failureCount;
	}


	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public BigInteger getNoOfFailure() {
		return noOfFailure;
	}

	public void setNoOfFailure(BigInteger noOfFailure) {
		this.noOfFailure = noOfFailure;
	}

}
