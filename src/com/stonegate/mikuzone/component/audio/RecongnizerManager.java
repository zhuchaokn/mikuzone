package com.stonegate.mikuzone.component.audio;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

public class RecongnizerManager {
	private SpeechRecognizer speechRecognizer;
	private Handler handler;
	public void init(RecognitionListener listener,Context context)
	{
		speechRecognizer=SpeechRecognizer.createSpeechRecognizer(context);
		speechRecognizer.setRecognitionListener(listener);
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
				startListen();
			}
		};
	}
	public void start(){
		handler.sendMessage(new Message());
	}
	public void startListen(){
		speechRecognizer.startListening(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH));
	}
	public void stopService()
	{
		speechRecognizer.stopListening();
		speechRecognizer.destroy();
	}
}
