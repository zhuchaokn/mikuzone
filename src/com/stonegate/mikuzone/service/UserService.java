package com.stonegate.mikuzone.service;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.stonegate.mikuzone.model.UserDTO;
import com.stonegate.mikuzone.network.NetworkManager;
import com.stonegate.mikuzone.network.impl.NetWorkManagerImpl;
import com.stonegate.mikuzone.util.data.MikuGlobal;

public class UserService {
	private static final String TAG = "UserService";

	public UserDTO logon(UserDTO userDTO) {
		NetworkManager networkManager = new NetWorkManagerImpl();

		UserDTO res = (UserDTO) networkManager.postObject(userDTO,
				MikuGlobal.HTTP_URL + "/user/logon.json", UserDTO.class);
		if (res != null) {
			Log.i(TAG, res.toString());
		}

		return res;

	}

	public UserDTO register(UserDTO userDTO) {
		// TODO Auto-generated method stub
		NetworkManager networkManager = new NetWorkManagerImpl();

		UserDTO res = (UserDTO) networkManager.postObject(userDTO,
				MikuGlobal.HTTP_URL + "/user/register.json", UserDTO.class);
		if (res != null) {
			Log.i(TAG, res.toString());
		}
		return res;
	}
}
