package com.stonegate.mikuzone.component.audio;

import java.util.ArrayList;

import com.stonegate.mikuzone.model.InstructionResults;
import com.stonegate.mikuzone.util.data.MikuGlobal;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

public class VoiceManager {
	private InstructionResults results;
	private boolean hasInstruction=false;
	private RecongnizerManager recongnizerManager; 
	private Object instructionLock;
	private boolean isRemote;
	private boolean isChecked;
	private AudioManger audioManger;
	public boolean check()
	{
		
		isChecked=false;
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				audioManger=new AudioManger();
				audioManger.setAudioListener(new AudioListener() {
					
					public void onOverThresHold() {
						// TODO Auto-generated method stub
						synchronized (instructionLock) {
							if(hasInstruction==false&&isRemote==false)
								   recongnizerManager.start();	
						}	
					}
					
					public boolean onCheckAudio(float volume) {
						// TODO Auto-generated method stub
						synchronized (instructionLock) {
							if(hasInstruction==true)
							{	 isRemote=true;
								 return true;
							} 
						}
						if(volume>MikuGlobal.AUDIO_THRESHOLD){
							return true;
						}							
						  
						return false;
					}
				});
				audioManger.start();
				Log.i("VOLUME","volume check start");
				/*
				synchronized (checkObject) {
					checkObject.notify();
				}
				*/
			}
		}).start();
      return isRemote;
	}
	public void uncheck() {
		// TODO Auto-generated method stub
		audioManger.finish();
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void start() {
		// TODO Auto-generated method stub
		synchronized (instructionLock) {
			if(hasInstruction==false)
				   recongnizerManager.start();	
		}	
	}
	public VoiceManager(Context context)
	{
		instructionLock =new Object();
		results=new InstructionResults();
		results.setRemoteRes(false);
		recongnizerManager=new RecongnizerManager();
		recongnizerManager.init(new RecListener(), context);
	}
	public void release()
	{
		recongnizerManager.stopService();
	}
	public InstructionResults getResults() {
		synchronized (instructionLock) {
			if(hasInstruction==false)
			  {
				try {
					
					instructionLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			hasInstruction=false;
			return results;			
		}
	}
	public void setResults(InstructionResults results) {
		synchronized (instructionLock) {
			this.results = results;
			hasInstruction=true;
			instructionLock.notify();
		}
	}
	
	public boolean hasInstruction()
	{
		return hasInstruction;
	}
	
	
	public void setHasInstruction(boolean hasInstruction) {
		this.hasInstruction = hasInstruction;
	}		
	private class RecListener implements RecognitionListener{

		public void onBeginningOfSpeech() {
			// TODO Auto-generated method stub
			
		}

		public void onBufferReceived(byte[] buffer) {
			// TODO Auto-generated method stub
			
		}

		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			
		}

		public void onError(int error) {
			// TODO Auto-generated method stub
			Log.e("VoiceManager","Error:"+error);
			String[] errRes=new String[2];
			errRes[0]="#ʶ�����"+error;
			switch (error) {
			case RecognizerIntent.RESULT_AUDIO_ERROR:
				errRes[1]="���²����ˣ�����������";
				break;
			case RecognizerIntent.RESULT_NETWORK_ERROR:
				errRes[1]="��ѽ��ѽ�����������";
				break;
			case RecognizerIntent.RESULT_NO_MATCH:
				errRes[1]="������岻�ʣ��ɷ�������Ϣһ�£�";
				break;
			case RecognizerIntent.RESULT_CLIENT_ERROR:
				errRes[1]="�ߣ���˵����ʲô�����˼Ҹ���û������.";
				break;
			case RecognizerIntent.RESULT_SERVER_ERROR:
				errRes[1]="���Թ�����Ұ����Ƿ����������ˡ�";
				break;
			default:
				errRes[1]="�ף�����ʲô���󰡣���ǰû�м�������";
				break;
			}
			
			results.setResults(errRes);
			hasInstruction=true;
			synchronized(instructionLock)
			{
				instructionLock.notify();
			}
		}

		public void onEvent(int eventType, Bundle params) {
			// TODO Auto-generated method stub
			
		}

		public void onPartialResults(Bundle partialResults) {
			// TODO Auto-generated method stub
			
		}

		public void onReadyForSpeech(Bundle params) {
			// TODO Auto-generated method stub
			
		}

		public void onResults(Bundle resultsBundle) {
			// TODO Auto-generated method stub
			Log.d("TAG", " onResults " + results);
			synchronized (instructionLock) {
				
				if(hasInstruction==true)
					return ;
				ArrayList<String> data = resultsBundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				//instruction=results.getStringArray(SpeechRecognizer.RESULTS_RECOGNITION);
				//instruction=(String[])data.toArray();
				String[] instruction=new String[data.size()];
				for(int i=0;i<data.size();i++)
				{
					instruction[i]=data.get(i);
				}
				
				InstructionResults voiceResults=new InstructionResults();
				voiceResults.setRemoteRes(false);
				voiceResults.setResults(instruction);
				setResults(voiceResults);
				Log.i("TAG", "res:"+data.get(0));
			}
		
			
		}

		public void onRmsChanged(float rmsdB) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void setRemote(boolean b) {
		// TODO Auto-generated method stub
		this.isRemote=true;
	}

}
