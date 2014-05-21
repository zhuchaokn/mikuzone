package com.stonegate.mikuzone.component.instruction;

import com.stonegate.mikuzone.component.audio.SpeechManager;
import com.stonegate.mikuzone.component.audio.VoiceManager;
import com.stonegate.mikuzone.component.video.PlayManager;
import com.stonegate.mikuzone.model.InstructionResults;
import com.stonegate.mikuzone.util.data.UserInfo;

import android.util.Log;

public class ExecutionManager {
	private Object playSamphore;
	private PlayManager playManager;
	private VoiceManager voiceManager;
	private SpeechManager speechManager;
	private InstructionManager instructionManager;
	private boolean isFinished;
	public void beforeExecute(){
		if(instructionManager.getInstructionDTO().getNextInstruction()!=0){
			return;
		}
        synchronized (playSamphore) {
        	playManager.playPre(UserInfo.getMikuType());
    		playSamphore.notify();
    		Log.i("OBJECT", "master waiting....");
    		try {
				playSamphore.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	public void waitInstruction(){
		if(instructionManager.getInstructionDTO().getNextInstruction()==0)
		 {
			voiceManager.check();
		 }
		else
		{
			voiceManager.start();
		}
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				isFinished=false;
				while(!isFinished)
				{	
					
						if(voiceManager.hasInstruction()){
							voiceManager.uncheck();
							break;
							}
				     	playManager.playIdle(UserInfo.getMikuType());
				     	synchronized (playSamphore) {	
				     	playSamphore.notify();
				     	Log.i("OBJECT", "master waiting....");
				     	try {
							playSamphore.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				synchronized (playSamphore) {
					playSamphore.notify();
				}
			}
		}).start();
		
	}
	public void executeInstruction(InstructionResults results)
	{
		try{
			int i=instructionManager.getInstruction(results);
				playManager.playInstruction(i,instructionManager.getInstructionDTO().getMikuType());
				isFinished=true;
				synchronized (playSamphore) {
					Log.i("OBJECT", "master waiting....");
					playSamphore.wait();
					playSamphore.notify();
					Log.i("OBJECT", "master waiting....");
					playSamphore.wait();
				  }
				speechManager.startSpeak(instructionManager.getLines());
				while(true)
				{
					if(speechManager.isOver()) break;
					playManager.playWaitingInstruction(i,instructionManager.getInstructionDTO().getMikuType());
					synchronized (playSamphore) {
						playSamphore.notify();
						playSamphore.wait();
					}
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public void setPlaySamphore(Object playSamphore) {
		this.playSamphore = playSamphore;
	}
	public void setPlayManager(PlayManager playManager) {
		this.playManager = playManager;
	}
	public void setVoiceManager(VoiceManager voiceManager) {
		this.voiceManager = voiceManager;
	}
	public void setSpeechManager(SpeechManager speechManager) {
		this.speechManager = speechManager;
	}
	public ExecutionManager()
	{
		instructionManager=new InstructionManager();
	}
}
