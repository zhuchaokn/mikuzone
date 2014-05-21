package test;

import java.util.ArrayList;

import com.stonegate.mikuzone.R;
import com.stonegate.mikuzone.component.audio.RecongnizerManager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;

public class TestHandler extends Activity {
	private TextView textView;
	private RecongnizerManager recongnizerManager;
	private Handler handler;
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i("TAG","start");
		super.onCreate(savedInstanceState);
		textView=(TextView)findViewById(R.id.testText);
		recongnizerManager=new RecongnizerManager();
		recongnizerManager.init(new RecognitionListener() {
			
			public void onRmsChanged(float rmsdB) {
				// TODO Auto-generated method stub
				
			}
			
			public void onResults(Bundle results) {
				// TODO Auto-generated method stub
				ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				//if(data!=null)
				  //textView.setText(""+data.get(0));
				Log.i("TAG", "res:"+data.get(0));
			}
			
			public void onReadyForSpeech(Bundle params) {
				// TODO Auto-generated method stub
				
			}
			
			public void onPartialResults(Bundle partialResults) {
				// TODO Auto-generated method stub
				
			}
			
			public void onEvent(int eventType, Bundle params) {
				// TODO Auto-generated method stub
				
			}
			
			public void onError(int error) {
				// TODO Auto-generated method stub
				
			}
			
			public void onEndOfSpeech() {
				// TODO Auto-generated method stub
				
			}
			
			public void onBufferReceived(byte[] buffer) {
				// TODO Auto-generated method stub
				
			}
			
			public void onBeginningOfSpeech() {
				// TODO Auto-generated method stub
				
			}
		}, this);
		
		setContentView(R.layout.test);
        handler=new Handler(){
			 @Override  
			 public void handleMessage(Message msg) {  
			         if(msg.what==1)
			         {
			        	 //textView.setText("haha"+msg.what);
			        	 recongnizerManager.start();
			         }
			      }  
		};
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while(i<100)
				{
					i++;
					Message msg=new Message();
					msg.what=1;
					handler.sendMessage(msg);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
}
