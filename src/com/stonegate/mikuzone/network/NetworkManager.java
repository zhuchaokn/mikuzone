package com.stonegate.mikuzone.network;

import org.json.JSONException;

public interface NetworkManager {
	public Object getObject(Object object, String url, Class target);

	public Object postObject(Object object, String url, Class target);

}
