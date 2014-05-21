package com.stonegate.mikuzone.component.action;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.stonegate.mikuzone.model.InstructionDTO;

import android.content.Context;

public class WeatherAction implements InstructionAction{
	private String results;
	private String area;
	private int count=0;
	private int ins;
	public WeatherAction()
	{
		
	}
	public WeatherAction(int i)
	{
		ins=i;
	}
	public void execute(InstructionDTO dto) {
		count++;
		results="对不起,查询失败.";
		area=dto.getParam();
		new Thread(new Runnable() {
			
			public void run() {
				results=(area+getWeather("101270101"));
			}
		}).start();
	}
	public String getResults() {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void setInstruction(int i) {
		// TODO Auto-generated method stub
		ins=i;
	}

	public int getInstruction() {
		// TODO Auto-generated method stub
		return ins;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	private String getWeather(String areaCode)
	{
		String res="";
		String raw =getRawData(areaCode);
		JSONTokener parser=new JSONTokener(raw);
		try {
			JSONObject object =(JSONObject)parser.nextValue();
			JSONObject weatherObject=(JSONObject)object.get("weatherinfo");
			res+="今天天气"+weatherObject.getString("weather1")+".";
			String temp=weatherObject.getString("temp1");
			res+="最高气温"+temp.substring(0,temp.indexOf("~"))+",";
			res+="最低气温"+temp.substring(temp.indexOf("~")+1)+".";
			res+=weatherObject.getString("wind1")+".";
			res+=weatherObject.getString("index_d");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	private String getRawData(String areaCode)
	{
		String strUrl="http://m.weather.com.cn/data/"+areaCode+".html";
		URL url;
		StringBuffer buffer=new StringBuffer();
		try {
			url = new URL(strUrl);
			HttpURLConnection weatherUrl=(HttpURLConnection) url.openConnection();
			InputStream inputStream=weatherUrl.getInputStream();
			
			byte[] in =new byte[4096];
			int len=0;
			while((len=inputStream.read(in))>0)
			{
				buffer.append(new String(in,0,len));
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}
	public String getLines() {
		// TODO Auto-generated method stub
		return results;
	}
}
