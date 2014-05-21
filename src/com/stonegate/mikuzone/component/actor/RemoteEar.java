package com.stonegate.mikuzone.component.actor;

import java.util.List;

import android.os.MessageQueue;
import android.util.Log;

import com.stonegate.mikuzone.component.audio.VoiceManager;
import com.stonegate.mikuzone.model.InstructionResults;
import com.stonegate.mikuzone.model.Message;
import com.stonegate.mikuzone.model.UserDTO;
import com.stonegate.mikuzone.service.MessageQueueService;
import com.stonegate.mikuzone.service.MessageService;
import com.stonegate.mikuzone.util.data.UserInfo;

public class RemoteEar implements Actor {
	private VoiceManager voiceManager;
	private boolean isLiving = false;
	private UserDTO userDTO = new UserDTO();
	private int timeToSleep = 3000;// 每3秒发送一次请求
	private MessageService messageService;

	public void init() {
		userDTO.setUserName(UserInfo.getUserName());
		messageService = new MessageService();
		isLiving=true;
	}

	public void execute() {
		// TODO Auto-generated method stub
		this.init();
		while (isLiving) {
			Log.i("RemoteEar", "getMessage");
			List<Message> list = messageService.getMessages(userDTO);
			for(Message message:list){
				MessageQueueService.putMessage(message);
			}
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.finish();
	}

	public void finish() {
		// TODO Auto-generated method stub
	}

	public void setVoiceManager(VoiceManager voiceManager) {
		this.voiceManager = voiceManager;
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void exit() {
		// TODO Auto-generated method stub
		isLiving = false;
		this.destroy();
	}
}
