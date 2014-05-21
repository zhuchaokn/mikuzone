package com.stonegate.mikuzone.service;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.stonegate.mikuzone.model.Message;
import com.stonegate.mikuzone.model.UserDTO;
import com.stonegate.mikuzone.network.NetworkManager;
import com.stonegate.mikuzone.network.impl.NetWorkManagerImpl;
import com.stonegate.mikuzone.util.data.MikuGlobal;

public class MessageService {

	public List<Message> getMessages(UserDTO userDTO) {
		NetworkManager networkManager = new NetWorkManagerImpl();
		@SuppressWarnings("unchecked")
		List<JSONObject> resList = (List<JSONObject>) networkManager.postObject(
				userDTO, MikuGlobal.HTTP_URL + "/message/get.json", List.class);
		List<Message> list = Lists.newArrayList();
		for (JSONObject msg : resList) {
			list.add(JSON.parseObject(msg.toJSONString(),Message.class));
		}
		return list;
	}
}
