package com.stonegate.mikuzone.component.audio;

import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;

public class SpeechManager {
	private boolean isOver=true;
	TextToSpeech speecher;
	private String speecherLines;
	private Handler handler;
	@SuppressWarnings("deprecation")
	public SpeechManager(Context context)
	{
		speecher=new TextToSpeech(context, new OnInitListener() {
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status == TextToSpeech.SUCCESS){
					speecher.setLanguage(Locale.US);
				}
			}
		});
		speecher.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {
			
			public void onUtteranceCompleted(String utteranceId) {
				// TODO Auto-generated method stub
				isOver=true;
			}
		});
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "finished");
				speecher.speak(speecherLines, TextToSpeech.QUEUE_ADD, map);
			}
		};
	}
	public void startSpeak(String lines) {
		// TODO Auto-generated method stub
		isOver=true;
		if(lines==null)return;
		isOver=false;
		speecherLines=lines;
		handler.sendMessage(new Message());
	}

	public boolean isOver() {
		// TODO Auto-generated method stub
		return isOver;
	}
	public void destory()
	{
		if(speecher!=null)
		{
			speecher.stop();
			speecher.shutdown();
		}
	}
}
