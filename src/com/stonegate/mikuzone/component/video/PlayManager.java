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
	 *videonameΪ��Ƶ·���洢���� 
	 */
	public String string;
	private String videoName;
	private VideoManager videoManager;
	public PlayManager(){
		
		videoManager=new VideoManager();
	}
		
	
	/**
	 * ������Ƶ·��
	 */
	public String getVideoName() {
		return videoName;
	}
	
	
	/**
	 * playIdleΪ�������лε���Ƶ·�� 
	 */
	public void playIdle(int type){
		Log.i("PlayManager", "play idle");
		int idleAmount=videoManager.getIdleCount();
		Random random=new Random();
		int Idlenumber = random.nextInt(idleAmount);
		videoName=videoManager.getIdle(Idlenumber,type);
	} 
	
	
	/**
	 * playPreΪ��������ʾ���ʵ���Ƶ·�� 
	 */
	public void playPre(int type){
		Log.i("PlayManager", "play pre");
		int preAmount=videoManager.getPreCount();
		Random random=new Random();
		int prenumber = random.nextInt(preAmount);
		videoName=videoManager.getPre(prenumber,type);
		
	}
	 
	/**
	 * playInstructionΪ����Ƶ·�� 
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
