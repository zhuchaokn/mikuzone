package com.stonegate.mikuzone.component.account;

import android.util.Log;

import com.stonegate.mikuzone.component.instruction.MessageManager;
import com.stonegate.mikuzone.model.MikuAccount;
import com.stonegate.mikuzone.util.data.MikuGlobal;

public class AccountManager {
	MikuAccount result;
	public MikuAccount regist(final MikuAccount account)
	{
		account.setOperation(2);
		result=new MikuAccount();
		result.setState("overtime");
		sendMessage(account);
		long time = System.currentTimeMillis();
	    while((System.currentTimeMillis()-time)<=2000)
	    {
	    	if(!result.getState().equals("overtime"))
	    		break;
	    }

		return result;
	}
	public MikuAccount logOn(final MikuAccount account)
	{
		account.setOperation(1);
		result=new MikuAccount();
		result.setState("overtime");
		sendMessage(account);
		long time = System.currentTimeMillis();
	    while((System.currentTimeMillis()-time)<=2000)
	    {
	    	if(!result.getState().equals("overtime"))
	    		break;
	    }

		return result;
	}
	private void sendMessage(final MikuAccount account)
	{
		new Thread(new Runnable() {
					
					public void run() {
						MessageManager manager=new MessageManager();
						if(manager.connect(MikuGlobal.USER_VALIDATE_PORT)==0)
						{  manager.send(account);
						   result=(MikuAccount) manager.recieve();
						}
						else{
							Log.i("Network", "Connect error");
						}
					}
				}).start();
	}
}
