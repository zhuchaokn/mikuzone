package com.stonegate.mikuzone.component.action;

import com.stonegate.mikuzone.model.InstructionDTO;

public class ScheduleAction implements InstructionAction{
	private int ins;
	public ScheduleAction(){}
	public void execute(InstructionDTO dto) {
		// TODO Auto-generated method stub
		
	}

	public String getResults() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInstruction(int i) {
		// TODO Auto-generated method stub
		ins=i;
	}

	public int getInstruction() {
		// TODO Auto-generated method stub
		return ins;
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
