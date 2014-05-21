package com.stonegate.mikuzone.component.video;

import java.util.Random;

import com.stonegate.mikuzone.component.instruction.InstructionManager;

import android.util.Log;

/**
 * @author DOVE
 *
 */

public class PlayManager {
	/**
	 *videoname为视频路径存储容器 
	 */
	public String string;
	private String videoName;
	private VideoManager videoManager;
	public PlayManager(){
		
		videoManager=new VideoManager();
	}
		
	
	/**
	 * 返回视频路径
	 */
	public String getVideoName() {
		return videoName;
	}
	
	
	/**
	 * playIdle为随机获得闲晃的视频路径 
	 */
	public void playIdle(int type){
		Log.i("PlayManager", "play idle");
		int idleAmount=videoManager.getIdleCount();
		Random random=new Random();
		int Idlenumber = random.nextInt(idleAmount);
		videoName=videoManager.getIdle(Idlenumber,type);
	} 
	
	
	/**
	 * playPre为随机获得提示发问的视频路径 
	 */
	public void playPre(int type){
		Log.i("PlayManager", "play pre");
		int preAmount=videoManager.getPreCount();
		Random random=new Random();
		int prenumber = random.nextInt(preAmount);
		videoName=videoManager.getPre(prenumber,type);
		
	}
	 
	/**
	 * playInstruction为的视频路径 
	 */
	public void playInstruction(int i,int type){
		Log.i("PlayManager", "play instruction"+i);
			videoName=videoManager.getInstruction(i,type);
			return;
	}
	public void playWaitingInstruction(int i,int type) {
		// TODO Auto-generated method stub
		Log.i("PlayManager", "play waiting instruction"+i);
		videoName=videoManager.getInstructionIdle(i,type);
	}
		
}
