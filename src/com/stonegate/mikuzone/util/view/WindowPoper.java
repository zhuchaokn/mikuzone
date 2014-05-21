package com.stonegate.mikuzone.util.view;

import android.app.AlertDialog;
import android.content.Context;

public class WindowPoper {

	public void popWindow(Context context, String title, String content) {
		new AlertDialog.Builder(context)

		.setTitle(title)

		.setMessage(content)

		.setPositiveButton("È·¶¨", null)

		.show();
	}

}
