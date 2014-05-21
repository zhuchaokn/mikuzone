package com.stonegate.mikuzone.component.action;

import java.awt.List;

import android.util.Log;

import com.stonegate.mikuzone.model.InstructionDTO;
import com.stonegate.mikuzone.model.Message;
import com.stonegate.mikuzone.service.MessageQueueService;

public class ReadMessageAction implements InstructionAction{
	private int mikuType;
	private String content;
	private int instruction;
	private int count=0;
	private String lines="";
	public void execute(InstructionDTO dto) {
		// TODO Auto-generated method stub
		mikuType=dto.getMikuType();
		Message msg=MessageQueueService.getMessage();
		Log.i("ReadMessage", "readMessage");
		while (msg!=null) {
			lines+=msg.getMsg()+";";
			Log.i("ReadMessage",msg.getMsg());
			msg=MessageQueueService.getMessage();
		}
	}

	public String getResults() {
		// TODO Auto-generated method stub
		return content;
	}

	public void setInstruction(int i) {
		// TODO Auto-generated method stub
		this.instruction=i;
	}

	public int getInstruction() {
		// TODO Auto-generated method stub
		return instruction;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	public String getLines() {
		// TODO Auto-generated method stub
		return lines;
	}

}
