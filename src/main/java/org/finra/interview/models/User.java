package org.finra.interview.models;

import lombok.Data;

@Data
public class User {

	private String userName;
	private String password;
	private String status;

	public User(String status){
		this.status = status;
	}

//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
}