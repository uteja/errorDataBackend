package com.example.springbootswagger2.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootswagger2.model.ErrorData;
import com.example.springbootswagger2.model.PostData;
import com.example.springbootswagger2.model.Student;
import com.example.springbootswagger2.model.requestProcessor.ErrorDataProcessor;
import com.example.springbootswagger2.model.requestProcessor.MailProcessor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Backend API's")
@RestController
@RequestMapping("/api")
public class Swagger2DemoRestController {
	
	private ErrorDataProcessor errorDataProcessor;
	
	private MailProcessor mailProcessor;

	@ApiOperation(value = "Get specific error which is related to given accountNumber ", response = ErrorData.class, tags = "getErrorDataByAccountNumber")
	@RequestMapping(value = "/getErrorDataByAccountNumber/{accountNumber}", method = RequestMethod.GET)
	public ErrorData getErrorDataByAccountNumber(@PathVariable(value = "accountNumber") String accountNumber) throws ClassNotFoundException, SQLException {
		System.out.println("given accountNumber : " + accountNumber);
		errorDataProcessor = new ErrorDataProcessor();
		ErrorData errorData = errorDataProcessor.getErrorDataByAccountNumber(accountNumber);
		return errorData;
	}
	
	@ApiOperation(value = "Get all errors ", response = ErrorData.class, tags = "getAllErrorData")
	@RequestMapping(value = "/getAllErrorData", method = RequestMethod.GET)
	public ErrorData[] getAllErrorData() throws ClassNotFoundException, SQLException {
		System.out.println("entered the controller");
		errorDataProcessor = new ErrorDataProcessor();
		ErrorData[] errorData = errorDataProcessor.getAllErrorData();
		return errorData;
	}
	
	@ApiOperation(value = "Send mail to a given mailId",response = String.class, tags = "sendMailToTeam")
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestBody PostData postData) throws ClassNotFoundException, SQLException {
		System.out.println("given mailId : " + postData.getMailId());
		errorDataProcessor = new ErrorDataProcessor();
		ErrorData errorData = errorDataProcessor.getErrorDataByAccountNumber(postData.getAccountNumber());
		mailProcessor = new MailProcessor();
		String responseCode = mailProcessor.sendMail(postData.getMailId(),errorData);
		return responseCode;
	}

}
