package com.stonegate.mikuzone.network.impl;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.stonegate.mikuzone.network.NetworkManager;

public class NetWorkManagerImpl implements NetworkManager {
	private String jsonRes;
	private static final String TAG = "network";
	private JSONObject jsonObject;
	public Object getObject(Object object, String url, Class target) {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public Object postObject(Object object, final String url, Class target){
		// TODO Auto-generated method stub
		try {
			jsonObject = new JSONObject(JSON.toJSONString(object));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Log.d(TAG, "sending");
					HttpPost httpPost = new HttpPost(new URI(url));
					httpPost.addHeader("Content-Type", "application/json");
					
					StringEntity entity = new StringEntity(jsonObject
							.toString(),HTTP.UTF_8);
					httpPost.setEntity(entity);
					HttpResponse response = new DefaultHttpClient()
							.execute(httpPost);
					Log.i(TAG, response.getStatusLine().toString());
					jsonRes = EntityUtils.toString(response.getEntity());
				} catch (Exception e) {
					Log.e("network", "postError", e);
				}
			}
		});
		thread.start();
		try {
			thread.join(TimeUnit.SECONDS.toMillis(6));
		} catch (InterruptedException e) {
			Log.e(TAG, "postError", e);
		}
		if (jsonRes != null) {
			try{
			return JSON.parseObject(jsonRes, target);
			}catch(Exception e){
				Log.e(TAG, "jsonparseError", e);
				return null;
			}
		}
		return null;
	}

}
