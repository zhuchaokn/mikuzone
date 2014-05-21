package com.stonegate.mikuzone.view;

import com.stonegate.mikuzone.R;
import com.stonegate.mikuzone.component.account.AccountManager;
import com.stonegate.mikuzone.model.MikuAccount;
import com.stonegate.mikuzone.model.UserDTO;
import com.stonegate.mikuzone.service.UserService;
import com.stonegate.mikuzone.util.data.AccountInfoKeeper;
import com.stonegate.mikuzone.util.data.UserInfo;
import com.stonegate.mikuzone.util.view.WindowPoper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SubSignupActivity extends Activity {
	private String userName;
	private String passWord;
	private String email;
	private String token;
	private int mikuType;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subregist);
		Intent it = getIntent();
		userName = it.getStringExtra("userName");
		passWord = it.getStringExtra("passWord");
		email = it.getStringExtra("email");
		token = it.getStringExtra("token");
		findViewById(R.id.subregit_miku1).setOnClickListener(
				new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						regist(1);
					}
				});
		findViewById(R.id.subregit_miku2).setOnClickListener(
				new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						regist(2);
					}
				});
		findViewById(R.id.subregit_miku3).setOnClickListener(
				new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						regist(3);
					}
				});
		findViewById(R.id.subregit_miku4).setOnClickListener(
				new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						regist(1);
					}
				});
	}

	private void regist(int i) {
		MikuAccount account = new MikuAccount();
		account.setEmail(email);
		account.setMikuType(i);
		account.setUserName(userName);
		account.setPassWord(passWord);
		account.setSinaToken(token);
		UserDTO userDTO = new UserService().register(account.toUserDTO());
		account=new MikuAccount(userDTO);

		if (account.getState().equals("Success")) {
			AccountInfoKeeper.writeAccount(SubSignupActivity.this, account);
			UserInfo.setAccount(account);
			Intent intent = new Intent();
			intent.setClass(SubSignupActivity.this, MainActivity.class);
			startActivity(intent);
			SubSignupActivity.this.finish();
		}

		else if (account.getState().equals("Existed")) {
			WindowPoper windowPoper = new WindowPoper();
			windowPoper.popWindow(SubSignupActivity.this, "提示", "用户名已存在");
		}

		else if (account.getState().equals("Failed")) {
			WindowPoper windowPoper = new WindowPoper();
			windowPoper.popWindow(SubSignupActivity.this, "提示", "注册失败");
		}

	}
}
