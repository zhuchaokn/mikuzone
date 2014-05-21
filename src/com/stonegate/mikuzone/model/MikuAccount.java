package com.stonegate.mikuzone.model;

import java.io.Serializable;


public class MikuAccount implements Serializable{
	/**
	 * @fields serialVersionUID
	 */
	
	private static final long serialVersionUID = -2997281092430316214L;
	private int operation;
	private String userName;
	private String passWord;
	private int mikuType;
	private String email;
	private String sinaToken;
	private String state;
	public MikuAccount(UserDTO userDTO) {
		// TODO Auto-generated constructor stub
		this.setEmail(userDTO.getEmail());
		this.setSinaToken(userDTO.getSinaToken());
		this.setMikuType(userDTO.getMikuType());
		this.setPassWord(userDTO.getPassWord());
		this.setUserName(userDTO.getUserName());
		this.setState(userDTO.getState());
	}
	public MikuAccount() {
		// TODO Auto-generated constructor stub
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public int getOperation() {
		return operation;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMikuType() {
		return mikuType;
	}
	public void setMikuType(int mikuType) {
		this.mikuType = mikuType;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getSinaToken() {
		return sinaToken;
	}
	public void setSinaToken(String sinaToken) {
		this.sinaToken = sinaToken;
	}
	public UserDTO toUserDTO(){
		UserDTO userDTO=new UserDTO(this.userName,this.passWord,this.mikuType,this.email,this.sinaToken);
		return userDTO;
	}

}
