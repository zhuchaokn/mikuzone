package com.stonegate.mikuzone.component.video;


import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MediaManager {
	private VideoView videoView;
	private String videoName;
	private Handler handler;
	private MediaController mediaController;
	private Object mediaLock;
	private PlayManager playManager;
	public void play()
	{
		this.videoName=playManager.getVideoName();
		handler.sendMessage(new Message());
		synchronized (mediaLock) {
			try {
				mediaLock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setMediaController(MediaController mediaController) {
		this.mediaController = mediaController;
	}
	public void init()
	{
		mediaLock=new Object();
		handler=new Handler(){
		@Override
		public void handleMessage(Message msg)
		{
			Log.i("MediaManager",videoName);
			videoView.setVideoPath(videoName);
			videoView.requestFocus();
		}
	};
		
	}
	public void setVideoView(VideoView view) {
		this.videoView = view;
		videoView.setMediaController(mediaController);
		videoView.setOnPreparedListener(new OnPreparedListener() {
			
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.start();
			}
		});
		videoView.setOnCompletionListener(new OnCompletionListener() {
			
			public void onCompletion(MediaPlayer mp) {
				synchronized (mediaLock) {
					mediaLock.notify();
				}
			}
		});	
		videoView.setOnErrorListener(new OnErrorListener() {
			
			public boolean onError(MediaPlayer media, int arg1, int arg2) {
				// TODO Auto-generated method stub
				Log.i("MediaManager",videoName);
				return false;
			}
		});
	}
	public void setPlayManager(PlayManager playManager) {
		this.playManager = playManager;
	}
}
