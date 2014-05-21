package com.stonegate.mikuzone.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.stonegate.mikuzone.util.data.AccessTokenKeeper;

public class AuthListener implements WeiboAuthListener {
	   private Oauth2AccessToken mAccessToken;
	   private Context context;
	   public AuthListener(Context con)
	   {
		   context=con;
	   }
       public void onComplete(Bundle values) {
            // �� Bundle �н��� Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // ��ʾ Token
                // ���� Token �� SharedPreferences
                AccessTokenKeeper.writeAccessToken(context, mAccessToken);
            } else {
                // ����ע���Ӧ�ó���ǩ������ȷʱ���ͻ��յ� Code����ȷ��ǩ����ȷ
                String code = values.getString("code");
                String message = "failed!";
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }

        public void onCancel() {
            Toast.makeText(context, "ȡ��", Toast.LENGTH_LONG).show();
        }

        public void onWeiboException(WeiboException e) {
            Toast.makeText(context, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }