package com.stonegate.mikuzone.view;

import com.stonegate.mikuzone.R;
import com.stonegate.mikuzone.util.data.MikuGlobal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ConfigActivity extends Activity{
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.config);
	        findViewById(R.id.congfig_btn_set).setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					EditText edit=(EditText)findViewById(R.id.textServerIp);
					MikuGlobal.SERVER_ADDRESS=edit.getText().toString();
					Intent it=new Intent();
					it.setClass(ConfigActivity.this, LogonActivity.class);
					startActivity(it);
				    ConfigActivity.this.finish();
				}
			});
	    }

}
