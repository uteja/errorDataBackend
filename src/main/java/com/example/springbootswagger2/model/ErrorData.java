package com.example.springbootswagger2.model;

import io.swagger.annotations.ApiModelProperty;

public class ErrorData {
	
	@ApiModelProperty(notes = "Number of the account",name="accountNumber",required=true,value="test name")
	private String accountNumber;
	
	@ApiModelProperty(notes = "Name of the account",name="accountName",required=true,value="test name")
	private String accountName;
	
	private String responsibleSystem;
	
	private String timeStamp;
	
	private String errorType;
	
	private String errorDetails;
	
	private String status;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getResponsibleSystem() {
		return responsibleSystem;
	}

	public void setResponsibleSystem(String responsibleSystem) {
		this.responsibleSystem = responsibleSystem;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
