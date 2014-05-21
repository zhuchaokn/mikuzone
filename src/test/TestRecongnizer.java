package test;

import java.util.ArrayList;

import com.stonegate.mikuzone.R;
import com.stonegate.mikuzone.component.audio.RecongnizerManager;
import com.stonegate.mikuzone.component.audio.VoiceManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class TestRecongnizer extends Activity {
	private TextView textView;
	private VoiceManager voiceManager;
	private Button button;
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i("TAG","start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		textView=(TextView)findViewById(R.id.testText);
		button=(Button)findViewById(R.id.button1);
		start();
	}


private void start()
{
	new RecongnizerManager().start();
	}
}
