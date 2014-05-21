package com.stonegate.mikuzone.component.action;

import com.stonegate.mikuzone.model.InstructionDTO;


public class DefaultAction implements InstructionAction{
	private String results;
	private int ins;
	private int count=0;
	private String lines;
	public DefaultAction(){
		results=null;
		ins=0;
	}
	public DefaultAction(String arg,int i)
	{
		results=arg;
		ins=i;
	}
	public void execute(InstructionDTO dto) {
		// TODO Auto-generated method stub
		results=dto.getParam();
		lines=dto.getLines();
		count++;
	}

	public String getResults() {
		// TODO Auto-generated method stub
		return results;
	}

	public void setInstruction(int i) {
		// TODO Auto-generated method stub
		ins=i;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	public int getInstruction() {
		// TODO Auto-generated method stub
		return ins;
	}
	public String getLines() {
		// TODO Auto-generated method stub
		return lines;
	}

}
