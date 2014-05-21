package test;

import com.stonegate.mikuzone.component.actor.Actor;
import com.stonegate.mikuzone.component.audio.VoiceManager;

public class TestMaster implements Actor{
	private VoiceManager voiceManager;

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void execute() {
		// TODO Auto-generated method stub
		while(true)
		{
			voiceManager.start();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void finish() {
		// TODO Auto-generated method stub
		
	}
	public void setVoiceManager(VoiceManager voiceManager) {
		this.voiceManager = voiceManager;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void exit() {
		// TODO Auto-generated method stub
		
	}
}
