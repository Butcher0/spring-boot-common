package org.demo.vo;

import java.io.Serializable;

public class UserVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
