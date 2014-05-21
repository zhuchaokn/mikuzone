package com.stonegate.mikuzone.util.data;


import android.util.Log;

import com.stonegate.mikuzone.component.action.DefaultAction;
import com.stonegate.mikuzone.component.action.InstructionAction;
import com.stonegate.mikuzone.model.InstructionDTO;
import com.stonegate.mikuzone.model.InstructionResults;

public class MessageHolder {
	private InstructionAction action;
	private InstructionDTO insDto;
	public MessageHolder(String lines)
	{
		insDto=new InstructionDTO();
		insDto.setSender(UserInfo.getUserName());
		insDto.setMikuType(UserInfo.getMikuType());
		action=new DefaultAction(null, 0);
		insDto.setLines(lines);
	}
	public MessageHolder(InstructionDTO ins) {
		// TODO Auto-generated constructor stub
		//ªÒ»°√¸¡Ó±‡∫≈
		if(ins==null){
			insDto=InstructionDTO.serverNullInstruction();
		}else {
			insDto=ins;	
		}
		createAction();
		
	}
	public MessageHolder(InstructionResults results) {
		// TODO Auto-generated constructor stub
		insDto=new InstructionDTO();
		String res[] =results.getResults();
		insDto.setAction("DefaultAction");
		insDto.setInstruction(Integer.parseInt(res[0]));
		insDto.setMikuType(Integer.parseInt(res[1]));
		insDto.setLines(res[2]);
		insDto.setSender(UserInfo.getUserName());
		createAction();
		
	}
	public int getInstruction() {
		return insDto.getInstruction();
	}
	public String getCommand() {
		return insDto.getCommand();
	}
	public String getParam() {
		return insDto.getParam();
	}
	public InstructionDTO getMessageDTO(String[] ins)
	{
		insDto.setRecogResults(ins);
		insDto.setSender(UserInfo.getUserName());
		return insDto;
	}
	public InstructionAction getAction() {
		return action;
	}
	public String getActionResults()
	{
		return action.getResults();
	}
	public void execute()
	{
		action.execute(insDto);
	}
	private void createAction()
	{
		try {
			Log.i("Miku", insDto.getAction());
			String actionNameString=InstructionAction.class.getPackage().getName()+"."+insDto.getAction();
			action=(InstructionAction)Class.forName(actionNameString).newInstance();
			action.setInstruction(insDto.getInstruction());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getLines() {
		// TODO Auto-generated method stub
		 return action.getLines();
	}
	public boolean beforeLines() {
		// TODO Auto-generated method stub
		return insDto.beforeLines();
	}
	public InstructionDTO getInstructionDTO() {
		// TODO Auto-generated method stub
		return insDto;
	}
}
