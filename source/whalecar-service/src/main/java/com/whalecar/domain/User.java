package com.whalecar.domain;

import java.io.Serializable;
import java.util.Date;

// Generated 2013-7-5 22:40:38 by Hibernate Tools 3.4.0.CR1

/**
 * User generated by hbm2java
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114094581321998769L;
	private Integer id;
	private String userName;
	private String userTel;
	private String userEmail;
	private int userCity;
    private int userArea;
	private String loginName;
	private String loginPassword;
    private String wxOpenId;
	private String flagUseable;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private Date createDate;

	public User() {
	}

	public User(String userName, String userTel, String userEmail,
			int userCity, String loginName, String loginPassword,
			String flagUseable) {
		this.userName = userName;
		this.userTel = userTel;
		this.userEmail = userEmail;
		this.userCity = userCity;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.flagUseable = flagUseable;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserCity() {
		return this.userCity;
	}

	public void setUserCity(int userCity) {
		this.userCity = userCity;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getFlagUseable() {
		return this.flagUseable;
	}

	public void setFlagUseable(String flagUseable) {
		this.flagUseable = flagUseable;
	}

    public int getUserArea() { return userArea; }

    public void setUserArea(int userArea) { this.userArea = userArea; }


    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
