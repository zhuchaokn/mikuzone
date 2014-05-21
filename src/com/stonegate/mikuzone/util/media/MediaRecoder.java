package com.stonegate.mikuzone.util.media;

import java.io.File;
import java.io.FileDescriptor;

import com.stonegate.mikuzone.component.audio.AudioVolumeInterface;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

public class MediaRecoder implements AudioVolumeInterface{
	private MediaRecorder mARecorder;
	private final float minAngle = (float) Math.PI*4 / 11; 
	public MediaRecoder()
	{
		mARecorder = new MediaRecorder();
		mARecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mARecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
		mARecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	}
	public void start()
	{
		try {
			mARecorder.setOutputFile(File.createTempFile("Recodertmp", ".amr").getAbsolutePath());
			mARecorder.prepare();
			mARecorder.start();	
		} catch (Exception e) {
			e.printStackTrace();
			this.release();
		}
	}
	public float getVolume()
	{
		float angle = 100*  minAngle* mARecorder.getMaxAmplitude() / 32768; 
    	if (angle>100) {
    		Log.i("Recorder", "volume:"+angle);
			angle = 100;
		}
    	return angle;
	}
	public void stop()
	{
	   mARecorder.stop();
	}
	public void release()
	{
		mARecorder.release();
		mARecorder=null;
	}
	
}
