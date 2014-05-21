package com.stonegate.mikuzone.component.actor;

import android.util.Log;

import com.stonegate.mikuzone.component.video.MediaManager;

public class Miku implements Actor{
	private Object playSamphore;
	private boolean isWorking;
	private MediaManager mediaManager;
	public void init() {
		// TODO Auto-generated method stub
		isWorking=true;
	}

	public void execute()   {
		// TODO Auto-generated method stub
		this.init();
		while(isWorking)
		{
			try {
				synchronized (playSamphore) {
					Log.i("OBJECT", "miku waiting....");
					playSamphore.wait();
				}
			mediaManager.play();	
				synchronized (playSamphore) {
					playSamphore.notify();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.destroy();
	}

	public void finish() {
		// TODO Auto-generated method stub
		
	}
	public void setMediaManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
	}
	public void setPlaySamphore(Object playSamphore) {
		this.playSamphore = playSamphore;
	}
	public void exit()
	{
		isWorking=false;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
