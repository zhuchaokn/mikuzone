package com.stonegate.mikuzone.component.action;


import com.stonegate.mikuzone.model.InstructionDTO;

public class RemoteMessageAction implements InstructionAction{
	private int mikuType;
	private String content;
	private int instruction;
	private int count=0;
	public RemoteMessageAction()
	{
		
	}
	public void execute(InstructionDTO dto) {
		// TODO Auto-generated method stub
		count++;
		mikuType=dto.getMikuType();
		content=dto.getParam();
	}

	public String getResults() {
		// TODO Auto-generated method stub
		return content;
	}

	public void setInstruction(int i) {
		// TODO Auto-generated method stub
		instruction=i;
	}

	public int getInstruction() {
		// TODO Auto-generated method stub
		return instruction;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		count++;
		return 0;
	}
	public String getLines() {
		// TODO Auto-generated method stub
		return null;
	}

}
