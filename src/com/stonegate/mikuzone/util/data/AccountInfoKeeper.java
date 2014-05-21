/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stonegate.mikuzone.util.data;

import com.stonegate.mikuzone.model.MikuAccount;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class AccountInfoKeeper {
    private static final String PREFERENCES_NAME = "com_miku_android";

    private static final String KEY_USERNAME      = "user_name";
    private static final String KEY_PASSWORD      = "pass_word";
    private static final String KEY_EMAIL         = "user_email";
    private static final String KEY_MIKUTYPE      = "user_type";
    private static final String KEY_SINA_TOKEN    = "sina_token";
    
    /**
     * 保存 Token 对象到 SharedPreferences。
     * 
     * @param context 应用程序上下文环境
     * @param token   Token 对象
     */
    public static void writeAccount(Context context, MikuAccount account) {
        if (null == context || null == account) {
            return;
        }
        Log.i("Account",""+account.getMikuType());
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(KEY_USERNAME, account.getUserName());
        editor.putString(KEY_PASSWORD, account.getPassWord());
        editor.putInt(KEY_MIKUTYPE, account.getMikuType());
        editor.putString(KEY_EMAIL, account.getEmail());
        editor.putString(KEY_SINA_TOKEN, account.getSinaToken());
        editor.commit();
    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     * 
     * @param context 应用程序上下文环境
     * 
     * @return 返回 Token 对象
     */
    public static MikuAccount readAccount(Context context) {
        if (null == context) {
            return null;
        }
        MikuAccount account=new MikuAccount();
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        account.setUserName(pref.getString(KEY_USERNAME, null));
        account.setPassWord(pref.getString(KEY_PASSWORD, null));
        account.setMikuType(pref.getInt(KEY_MIKUTYPE, 0));
        account.setEmail(pref.getString(KEY_EMAIL, null));
        account.setSinaToken(pref.getString(KEY_SINA_TOKEN, null));
        if(account.getUserName()==null) {
        	return null;
        }
        Log.i("Account",""+account.getMikuType());
        return account;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     * 
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }
        
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
