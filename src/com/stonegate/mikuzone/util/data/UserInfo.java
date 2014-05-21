package com.stonegate.mikuzone.util.data;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.stonegate.mikuzone.model.MikuAccount;


public class UserInfo {
	private static final String TAG="UserInfo";
	private static String userName;
	private  static String passWord;
	private static int mikuType=1;
	private  static String email;
	private static String city;
	private static String networkState;
	private static String userState;
	private static Context mainContext;
	private static Handler handler;
	public static void setHandler(Handler handler) {
		UserInfo.handler = handler;
	}
	public static Handler getHandler() {
		return handler;
	}
	public static Context getMainContext() {
		return mainContext;
	}
	public static void setMainContext(Context mainContext) {
		UserInfo.mainContext = mainContext;
	}
	public static void setAccount(MikuAccount account)
	{
		Log.i(TAG, "getAccount:"+account.getMikuType());
		userName=account.getUserName();
		passWord=account.getPassWord();
		mikuType=account.getMikuType();
		email=account.getEmail();
	}
	public static void setEmail(String email) {
		UserInfo.email = email;
	}
	public static void setUserName(String userName) {
		UserInfo.userName = userName;
	}
	public static void setPassWord(String passWord) {
		UserInfo.passWord = passWord;
	}
	public static void setMikuType(int mikuType) {
		UserInfo.mikuType = mikuType;
	}
	public static String getUserName() {
		return userName;
	}
	public static String getPassWord() {
		return passWord;
	}
	public static String getEmail() {
		return email;
	}
	public static int getMikuType() {
		return mikuType;
	}
	public static String getCity() {
		return city;
	}
	public static void setCity(String city) {
		UserInfo.city = city;
	}
	public static String getNetworkState() {
		return networkState;
	}
	public static void setNetworkState(String networkState) {
		UserInfo.networkState = networkState;
	}
	public static String getUserState() {
		return userState;
	}
	public static void setUserState(String userState) {
		UserInfo.userState = userState;
	}
}
