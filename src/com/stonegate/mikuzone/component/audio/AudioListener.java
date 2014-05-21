package com.stonegate.mikuzone.component.audio;

public interface AudioListener {
	public void onOverThresHold();
	public boolean onCheckAudio(float volume);
}
