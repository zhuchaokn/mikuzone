package com.stonegate.mikuzone.view;

import com.stonegate.mikuzone.R;
import com.stonegate.mikuzone.util.data.MikuConfiger;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadFileActivity extends Activity {
	private TextView textView;
	private ProgressBar progressBar;
	private String text = "start init...\n";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadfile);
		textView = (TextView) findViewById(R.id.fileName);
		textView.setText(text);
		progressBar = (ProgressBar) findViewById(R.id.fileProgress);
		new Thread(fileDownloadThread).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			displayText(msg);
			switch (msg.what) {
			case 0:
				progressBar.setMax(msg.arg1);
				break;
			case 1:
				break;
			case 2:
				progressBar.setProgress(msg.arg1);
				break;
			case 3:
				Intent it=new Intent();
				it.setClass(LoadFileActivity.this,LogonActivity.class);
				startActivity(it);
				//LoadFileActivity.this.finish();
			}
		}
		private void displayText(Message msg) {
			String fileName = (String) msg.obj;
			text += fileName + "\n";
			textView.setText(text);
		}
	};
	private Runnable fileDownloadThread = new Runnable() {

		public void run() {
			// TODO Auto-generated method stub
			try {
				MikuConfiger mikuConfiger = MikuConfiger.create();
				if (mikuConfiger.hasCheck()) {
					// 文件已经加载完毕，且与服务器端版本一致
					handler.sendMessage(getMessage(3, "no update", 0));
					return;
				}
				mikuConfiger.init();
				handler.sendMessage(getMessage(0, "check filelist....done", mikuConfiger.getFileCount()));
				int progress = 0;
				while (mikuConfiger.hasNext()) {
					String fileName = mikuConfiger.checkNext();
					handler.sendMessage(getMessage(2, "check " + fileName
							+ "......done", ++progress));
				}
				mikuConfiger.checked();
				handler.sendMessage(getMessage(3,"all done!",mikuConfiger.getFileCount()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				handler.sendMessage(getMessage(1, "init failed", -1));
			}
		}

		Message getMessage(int what, String msg, int len) {
			Message message = new Message();
			message.what = what;
			message.obj = msg;
			message.arg1 = len;
			return message;
		}

	};
}
