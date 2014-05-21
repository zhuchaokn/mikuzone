package com.stonegate.mikuzone.component.action;

import com.stonegate.mikuzone.model.InstructionDTO;

public class ExitAction implements InstructionAction{
	
	public void execute(InstructionDTO dto) {
		// TODO Auto-generated method stub
		android.os.Process.killProcess(android.os.Process.myPid());
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
