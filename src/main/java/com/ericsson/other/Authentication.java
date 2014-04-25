package com.ericsson.other;

public class Authentication {
	
	private  String username;
	private  String password;
	private  String url;
	

	public Authentication(String username, String password,
			String url) {
		this.username = username;
		this.password = password;
		this.url = url;
	}


	public Authentication() {
		this.username = null;
		this.password = null;
		this.url = null;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

}
