package com.stonegate.mikuzone.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.R.integer;
import android.util.Log;

import com.stonegate.mikuzone.util.data.MikuGlobal;

public class FileDownloadService {
	FileOutputStream fileOutputStream = null;
	URLConnection connection = null;
	InputStream inputStream = null;

	public void download(File file) {
		try {
			Log.i("FileDownload",file.getAbsolutePath());
			URL url = new URL(MikuGlobal.SERVER_ADDRESS + "/download/file/"
					+ file.getName());
			connection = url.openConnection();
			int fileLength=connection.getContentLength();
			int length=0;
			byte[] buffer = new byte[1024];
			inputStream = connection.getInputStream();
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			fileOutputStream = new FileOutputStream(file);
			while (length<fileLength) {
				length+=inputStream.read(buffer);
				fileOutputStream.write(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			file.delete();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				file.delete();
			}
			try {
			  if(inputStream!=null){
				  inputStream.close();
			  }
			} catch (IOException e2) {
				// TODO: handle exception
				file.delete();
				e2.printStackTrace();
			}
		}
	}

	public boolean checkVersion(String version) throws IOException {
		// TODO Auto-generated method stub
		URL url=new URL(MikuGlobal.SERVER_ADDRESS+"/download/version");
		connection = url.openConnection();
		String remoteVersion=connection.getHeaderField("version");
		if(version.equals(remoteVersion)){
			return true;
		}
		return false;
	}
}
