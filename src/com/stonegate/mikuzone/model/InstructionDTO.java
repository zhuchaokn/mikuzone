package com.stonegate.mikuzone.model;

import java.io.Serializable;

import com.stonegate.mikuzone.util.data.MikuGlobal;
import com.stonegate.mikuzone.util.data.UserInfo;

public class InstructionDTO implements Serializable{

	/**
	 * @fields serialVersionUID
	 */
	
	private static final long serialVersionUID = -117572998566319643L;
	private String sender;
	private int mikuType;
	private int instruction;
	private int nextInstruction;
	private String command;
	private String param;
	private String[] recogResults;
	private String action;
	private String lines;
	private boolean before;
	public InstructionDTO()
	{
		mikuType=0;
		command="Default";
		instruction=0;
		sender=UserInfo.getUserName();
	}
	public int getNextInstruction() {
		return nextInstruction;
	}
	public void setNextInstruction(int nextInstruction) {
		this.nextInstruction = nextInstruction;
	}
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	public InstructionDTO(int ins,int mType,String sender,String command,String param,String action)
	{
		this.instruction=ins;
		this.sender=sender;
		this.mikuType=mType;
		this.action=action;
		this.command=command;
		this.param=param;
		this.before=true;		
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		if(action==null){
			return "DefaultAction";
		}
		return action;
	}
   public void setSender(String sender) {
	this.sender = sender;
   }
   public String getSender() {
	return sender;
}
   public void setMikuType(int mikuType) {
	this.mikuType = mikuType;
}
   public int getMikuType() {
	return mikuType;
}
  public void setCommand(String command) {
	this.command = command;
}
  public void setInstruction(int instruction) {
	this.instruction = instruction;
}
  public String getCommand() {
	return command;
}
  public int getInstruction() {
	return instruction;
}
   public void setParam(String param) {
	this.param = param;
}
   public String getParam() {
	return param;
}
   public void setRecogResults(String[] recogResults) {
	this.recogResults = recogResults;
}
   public String[] getRecogResults() {
	return recogResults;
}
public void setBefore(boolean before) {
	this.before = before;
}
public boolean beforeLines() {
	// TODO Auto-generated method stub
	return before;
}
public static InstructionDTO serverNullInstruction() {
	// TODO Auto-generated method stub
	InstructionDTO instructionDTO=new InstructionDTO();
	instructionDTO.setInstruction(0);
	instructionDTO.setAction("DefaultAction");
	instructionDTO.setLines(MikuGlobal.SERVER_NULL_MSG);
	return instructionDTO;
}
  
}
