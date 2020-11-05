package com.example.springbootswagger2.model.requestProcessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.context.annotation.ComponentScan;

import com.example.springbootswagger2.model.ErrorData;

@ComponentScan
public class ErrorDataProcessor {

	public ErrorData getErrorDataByAccountNumber(String accountNumber) throws SQLException, ClassNotFoundException {
		
		Connection c = null;
		Statement stmt = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:C:/Users/AC52263/Desktop/hackathon/code/spring-boot-swagger2/errorData.db");
		c.setAutoCommit(false);
		System.out.println("Opened database successfully");

		stmt = c.createStatement();
		String sql = "SELECT * FROM ERRORDATA WHERE ACCOUNTNUMBER = ?";
		PreparedStatement pstmt  = c.prepareStatement(sql);
		pstmt.setString(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println(rs.getRow());
		ErrorData errorData = new ErrorData();
		while (rs.next()) {
			errorData.setAccountName(rs.getString("ACCOUNTNAME"));
			errorData.setAccountNumber(rs.getString("ACCOUNTNUMBER"));
			errorData.setErrorDetails(rs.getString("ERRORDETAILS"));
			errorData.setErrorType(rs.getString("ERRORTYPE"));
			errorData.setResponsibleSystem(rs.getString("RESPONSIBLESYSTEM"));
			errorData.setStatus(rs.getString("STATUS"));
			errorData.setTimeStamp(rs.getString("TIMESTAMP"));
			errorData.setErrorCriticality(rs.getString("ERRORCRITICALITY"));
			errorData.setErrorCode(rs.getString("ERRORCODE"));
			System.out.println("ID = " + rs.getString("ACCOUNTNAME"));
			rs.close();
			stmt.close();
			c.close();
		}
		if (errorData.getAccountName().isEmpty()) {
			throw new NoSuchFieldError("No account found!");
		}

		return errorData;
	}

}
