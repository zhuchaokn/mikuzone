package com.stonegate.mikuzone.component.action;


import android.os.Message;

import com.stonegate.mikuzone.model.InstructionDTO;
import com.stonegate.mikuzone.util.data.UserInfo;

public class ReturnLogon implements InstructionAction{

	public void execute(InstructionDTO dto) {
		// TODO Auto-generated method stub
		Message msg=new Message();
		msg.arg1=1;
		UserInfo.getHandler().sendMessage(msg);
	}

	public String getResults() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInstruction(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getInstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLines() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
