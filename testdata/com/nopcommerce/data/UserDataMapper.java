package com.nopcommerce.data;

import java.io.File;

import org.aspectj.org.eclipse.jdt.internal.core.util.PublicScanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserDataMapper {

	@JsonProperty("firstName")
	private String  firstName;
	
	@JsonProperty("lastName")
	private String  lastName;
	
	@JsonProperty("emailAddress")
	private String  emailAddress;
	
	@JsonProperty("password")
	private String  password;
	
	@JsonProperty("date")
	private String  date;
	
	@JsonProperty("month")
	private String  month;
	
	@JsonProperty("year")
	private String  year;
	
	@JsonProperty
	private Login login;
	public static class Login {
		@JsonProperty("username")
		private String  username;
		
		@JsonProperty("password")
		private String  password;
	}
	public static UserDataMapper getUserData() {
		try { ObjectMapper mapper=new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "/resources/UserData.json"), UserDataMapper.class);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Login getLogin() {
		return login;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEnailAddress() {
		return emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getYear() {
		return year;
	}
	


}
