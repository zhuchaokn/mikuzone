package com.stonegate.mikuzone.view;

import com.stonegate.mikuzone.R;
import com.stonegate.mikuzone.component.actor.Actor;
import com.stonegate.mikuzone.component.actor.Heart;
import com.stonegate.mikuzone.component.actor.Master;
import com.stonegate.mikuzone.component.actor.Miku;
import com.stonegate.mikuzone.component.actor.RemoteEar;
import com.stonegate.mikuzone.component.audio.SpeechManager;
import com.stonegate.mikuzone.component.audio.VoiceManager;
import com.stonegate.mikuzone.component.instruction.ExecutionManager;
import com.stonegate.mikuzone.component.video.MediaManager;
import com.stonegate.mikuzone.component.video.PlayManager;
import com.stonegate.mikuzone.util.data.AccountInfoKeeper;
import com.stonegate.mikuzone.util.data.UserInfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
    private VideoView videoView;
    private Master master;
    //private TestMaster testMaster;
    private Miku miku;
    private RemoteEar remoteEar;
    private VoiceManager voiceManager;
    private MediaManager mediaManager;
    private SpeechManager speechManager;
    private Heart heart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler();
        prepareViewAndActor();
         //startActor(heart);
         startActor(miku); 
         startActor(master);
         startActor(remoteEar);
    }
    private void prepareViewAndActor()
    {
    	getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.main);
        //获取界面上VideoView组件
        videoView = (VideoView)findViewById(R.id.video);
        PlayManager playManager=new PlayManager();
        mediaManager=new MediaManager();
        mediaManager.setVideoView(videoView);
        mediaManager.setMediaController(new MediaController(this));
        mediaManager.setPlayManager(playManager);
        mediaManager.init();
        miku=new Miku();
        Object playSamphore=new Object();
        miku.setPlaySamphore(playSamphore);
       
        miku.setMediaManager(mediaManager);
        voiceManager=new VoiceManager(MainActivity.this);
        speechManager=new SpeechManager(MainActivity.this);
        ExecutionManager executionManager=new ExecutionManager();
        executionManager.setPlaySamphore(playSamphore);
        executionManager.setVoiceManager(voiceManager);
        executionManager.setPlayManager(playManager);
        executionManager.setSpeechManager(speechManager);
        
        master=new Master();
        master.setExecutionManager(executionManager);
        master.setVoiceManager(voiceManager);
        
        remoteEar=new RemoteEar();
        remoteEar.setVoiceManager(voiceManager);
        heart=new Heart();

    }
    @Override
    protected void onDestroy(){
    	voiceManager.release();
    	speechManager.destory();
        super.onDestroy();
        
    }
    private void startActor(final Actor actor)
    {
    	new Thread(new Runnable() {
				public void run() {
				 actor.execute();
				}
		}).start();

    }
    private void handler()
    {
    	Handler handler=new Handler(){
    		@Override
			public void handleMessage(Message msg)
			{
				if(msg.arg1==1)
				{
					Intent it=new Intent();
				   AccountInfoKeeper.clear(MainActivity.this);
				   remoteEar.exit();
				   master.exit();
				   miku.exit();
				   heart.exit();
				   try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   it.setClass(MainActivity.this, LogonActivity.class);
				   startActivity(it);
				   MainActivity.this.finish();
				}
			}
    	};
    	UserInfo.setHandler(handler);
    }
}
