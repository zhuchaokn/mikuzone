package com.stonegate.mikuzone.view;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.stonegate.mikuzone.R;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.stonegate.mikuzone.util.data.AccessTokenKeeper;
import com.stonegate.mikuzone.util.data.Constants;
import com.stonegate.mikuzone.util.view.WindowPoper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SignupActivity extends Activity {
	private EditText username;
	private EditText userpwd;
	private EditText email;
	private EditText userpwdCon;
	private WeiboAuth mWeiboAuth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		username = (EditText) findViewById(R.id.regist_username);
		userpwd = (EditText) findViewById(R.id.regist_password);
		userpwdCon = (EditText) findViewById(R.id.regist_password_confirm);
		email = (EditText) findViewById(R.id.regist_email);
		findViewById(R.id.regist_next).setOnClickListener(
				new OnClickListener() {

					public void onClick(View arg0) {
						Intent it = new Intent();
						String uName = username.getText().toString();
						String pass = userpwd.getText().toString();
						String passCon = userpwdCon.getText().toString();
						String emailStr = email.getText().toString();

						if (uName == null || uName.equals("")) {
							WindowPoper windowPoper = new WindowPoper();
							windowPoper.popWindow(SignupActivity.this, "提示",
									"用户名不能为空");
							return;
						}
						if (pass == null || pass.equals("")) {
							WindowPoper windowPoper = new WindowPoper();
							windowPoper.popWindow(SignupActivity.this, "提示",
									"密码不能为空");
							return;
						}
						if (!pass.equals(passCon)) {
							WindowPoper windowPoper = new WindowPoper();
							windowPoper.popWindow(SignupActivity.this, "提示",
									"两次密码不一致");
							return;
						}
						String token = AccessTokenKeeper.readAccessToken(
								SignupActivity.this).getToken();
						if (token == null) {
							WindowPoper windowPoper = new WindowPoper();
							windowPoper.popWindow(SignupActivity.this, "提示",
									"请设置微博");
							return;
						}
//						pass=new String(Hashing
//								.md5()
//								.hashString(pass,
//										Charsets.UTF_8).asBytes());
						it.putExtra("userName", uName);
						it.putExtra("passWord", pass);
						it.putExtra("email", emailStr);
						it.putExtra("token", token);

						it.setClass(SignupActivity.this,
								SubSignupActivity.class);
						startActivity(it);
						SignupActivity.this.finish();
					}
				});
		findViewById(R.id.regist_weibo).setOnClickListener(
				new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						mWeiboAuth = new WeiboAuth(SignupActivity.this,
								Constants.APP_KEY, Constants.REDIRECT_URL,
								Constants.SCOPE);
						mWeiboAuth.anthorize(new AuthListener(
								SignupActivity.this));
					}
				});
	}
}
