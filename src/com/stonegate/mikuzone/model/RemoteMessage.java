package com.stonegate.mikuzone.model;

import com.stonegate.mikuzone.util.data.MikuGlobal;

public class RemoteMessage {
	private int instruction;
	private String content;
	public RemoteMessage(String ins)
	{
		if(ins!=null)
		{  String[] res=ins.split(MikuGlobal.COMMON_TOKEN);
			instruction=Integer.parseInt(res[0]);
			if(ins.length()>=2)
			 {content=res[1];
			 }
			else
			{
				content=null;
			}
		}
		
	}
	public String getContent() {
		return content;
	}
	public int getInstruction() {
		return instruction;
	}
}
