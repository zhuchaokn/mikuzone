package com.stonegate.mikuzone.component.audio;

import android.R.bool;

import com.stonegate.mikuzone.util.media.MediaRecoder;

public class AudioManger {
	private AudioListener audioListener;
	private boolean isRunning;
	public void setAudioListener(AudioListener audioListener) {
		this.audioListener = audioListener;
	}
	public void start(){
		isRunning=true;
		//AudioVolumeInterface audioRecoder=new AudioRecoder();
		AudioVolumeInterface audioRecoder=new MediaRecoder();
		audioRecoder.start();
		while(isRunning){
			
			if(audioListener.onCheckAudio(audioRecoder.getVolume())){
				audioRecoder.stop();
				audioRecoder.release();
				audioListener.onOverThresHold();
				break;
			}
		}
		if(!isRunning){
			audioRecoder.stop();
			audioRecoder.release();
		}
		
	}
	public void finish() {
		// TODO Auto-generated method stub
		isRunning=false;
	}
}
