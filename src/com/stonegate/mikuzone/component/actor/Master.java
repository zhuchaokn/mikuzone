package com.stonegate.mikuzone.component.actor;

import com.stonegate.mikuzone.component.audio.VoiceManager;
import com.stonegate.mikuzone.component.instruction.ExecutionManager;
import com.stonegate.mikuzone.model.InstructionResults;

public class Master implements Actor{
	private VoiceManager voiceManager;
	private boolean isWorking;
	private ExecutionManager executionManager;
	public void init() {
		// TODO Auto-generated method stub
		isWorking=true;
	}

	public void execute() {
		// TODO Auto-generated method stub
		this.init();
		while(isWorking)
		{
			//if(!voiceManager.check())
			{executionManager.beforeExecute();
			 executionManager.waitInstruction();
			}
			InstructionResults results=voiceManager.getResults();
			executionManager.executeInstruction(results);
		}
		this.destroy();

		
	}

	public void finish() {
		// TODO Auto-generated method stub
		
	}
  public void exit()
  {
	  isWorking=false;
  }

  public void setVoiceManager(VoiceManager voiceManager) {
	this.voiceManager = voiceManager;
  }
  public void setExecutionManager(ExecutionManager executionManager) {
	this.executionManager = executionManager;
}

public void destroy() {
	// TODO Auto-generated method stub
	
}
}
