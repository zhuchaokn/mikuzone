package com.stonegate.mikuzone.model;
import java.io.Serializable;

/**
 * Created by chao.zhu 14-4-22 ÏÂÎç5:46
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -9015554614592087769L;
    private int userId;
    private String userName;
    private String passWord;
    private int mikuType;
    private String email;
    private String sinaToken;
    private String state;
    public UserDTO() {

    }
    public UserDTO(String userName2, String passWord2, int mikuType2,
			String email2, String sinaToken2) {
		// TODO Auto-generated constructor stub
    	this.setEmail(email2);
    	this.setMikuType(mikuType2);
    	this.setPassWord(passWord2);
    	this.setUserName(userName2);
    	this.setSinaToken(sinaToken2);
	}
	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getMikuType() {
        return mikuType;
    }

    public void setMikuType(int mikuType) {
        this.mikuType = mikuType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSinaToken() {
        return sinaToken;
    }

    public void setSinaToken(String sinaToken) {
        this.sinaToken = sinaToken;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", mikuType=" + mikuType +
                ", email='" + email + '\'' +
                ", sinaToken='" + sinaToken + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
