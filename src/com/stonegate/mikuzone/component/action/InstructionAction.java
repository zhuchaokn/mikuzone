package com.stonegate.mikuzone.component.action;

import com.stonegate.mikuzone.model.InstructionDTO;


public interface InstructionAction {
	public void execute(InstructionDTO dto);
	public String getResults();
	public void setInstruction(int i);
	public int getInstruction();
	public int getCount();
	public String getLines();
}
