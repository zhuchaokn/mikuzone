package com.stonegate.mikuzone.component.actor;

import com.stonegate.mikuzone.component.instruction.MessageManager;
import com.stonegate.mikuzone.util.data.MikuGlobal;
import com.stonegate.mikuzone.util.data.UserInfo;
public class Heart implements Actor{
	private MessageManager messageManager;
	private boolean isLiving=true;
	public void init() {
		// TODO Auto-generated method stub
		isLiving=true;
		UserInfo.setUserState(MikuGlobal.ONLINE);
		messageManager=new MessageManager();
		messageManager.connect(MikuGlobal.USER_MANAGEMENT_PORT);
		messageManager.send(UserInfo.getUserName()+MikuGlobal.COMMON_TOKEN+UserInfo.getUserState());
	}

	public void execute() {
		// TODO Auto-generated method stub
		this.init();
		while(isLiving)
		{
			messageManager.connect(MikuGlobal.USER_MANAGEMENT_PORT);
			messageManager.send(UserInfo.getUserName()+MikuGlobal.COMMON_TOKEN+UserInfo.getUserState());
			try {
				Thread.sleep(MikuGlobal.HEART_BEATING);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.destroy();
	}

	public void finish() {
		// TODO Auto-generated method stub
		messageManager.connect(MikuGlobal.USER_MANAGEMENT_PORT);
		messageManager.send(UserInfo.getUserName()+MikuGlobal.COMMON_TOKEN+MikuGlobal.OFFLINE);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				messageManager.connect(MikuGlobal.USER_MANAGEMENT_PORT);
				messageManager.send(UserInfo.getUserName()+MikuGlobal.COMMON_TOKEN+MikuGlobal.OFFLINE);
			}
		}).start();
		
	}

	public void exit() {
		// TODO Auto-generated method stub
		isLiving=false;
	}
	
}
