package com.stonegate.mikuzone.view;

import java.nio.charset.Charset;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.stonegate.mikuzone.R;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.stonegate.mikuzone.model.MikuAccount;
import com.stonegate.mikuzone.model.UserDTO;
import com.stonegate.mikuzone.service.UserService;
import com.stonegate.mikuzone.util.data.AccessTokenKeeper;
import com.stonegate.mikuzone.util.data.AccountInfoKeeper;
import com.stonegate.mikuzone.util.data.Constants;
import com.stonegate.mikuzone.util.data.UserInfo;
import com.stonegate.mikuzone.util.view.WindowPoper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LogonActivity extends Activity {
	private EditText userName;
	private EditText passWord;
	/** 微博 Web 授权类，提供登陆等功能 */
	private WeiboAuth mWeiboAuth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logon);
		findView();
		MikuAccount account = AccountInfoKeeper.readAccount(LogonActivity.this);
		if (account != null) {
			if (logOn(account)) {
				skipTo(MainActivity.class);
			}
		}
	}

	private void findView() {
		userName = (EditText) findViewById(R.id.logon_username);
		passWord = (EditText) findViewById(R.id.logon_password);
		ImageButton logonButton = (ImageButton) findViewById(R.id.logon_logon);
		logonButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				MikuAccount account = new MikuAccount();
//				String strPass = new String(Hashing
//						.md5()
//						.hashString(passWord.getText().toString(),
//								Charsets.UTF_8).asBytes());
				String strPass=passWord.getText().toString();
				account.setUserName(userName.getText().toString());
				account.setPassWord(strPass);
				account.setSinaToken(AccessTokenKeeper.readAccessToken(
						LogonActivity.this).getToken());
				boolean res = logOn(account);
				if (res) {
					AccountInfoKeeper.writeAccount(LogonActivity.this, account);
					skipTo(MainActivity.class);
				} else {
					new WindowPoper().popWindow(LogonActivity.this, "提示",
							"登陆错误");
				}
			}
		});

		/* registerButton为logon页面上的注册 */
		ImageButton registerButton = (ImageButton) findViewById(R.id.logon_signup);
		registerButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				skipTo(SignupActivity.class);
			}
		});
		findViewById(R.id.logon_weibo).setOnClickListener(
				new OnClickListener() {

					public void onClick(View v) {
						mWeiboAuth = new WeiboAuth(LogonActivity.this,
								Constants.APP_KEY, Constants.REDIRECT_URL,
								Constants.SCOPE);
						mWeiboAuth.anthorize(new AuthListener(
								LogonActivity.this));
					}
				});
	}

	private boolean logOn(MikuAccount account) {
		UserDTO userDTO = new UserService().logon(account.toUserDTO());
		if (userDTO == null) {
			return false;
		}
		MikuAccount resAccount=new MikuAccount(userDTO);
		UserInfo.setAccount(resAccount);
		return true;
	}

	private void skipTo(Class activity) {
		Intent intent = new Intent();
		intent.setClass(LogonActivity.this, activity);
		startActivity(intent);
		LogonActivity.this.finish();
	}

}
