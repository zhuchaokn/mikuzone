package com.stonegate.mikuzone.component.instruction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.stonegate.mikuzone.util.data.MikuGlobal;

public class MessageManager {
	private Socket socket;
	 public int connect(int port){
		try {
			//ServerSocket serverSocket=new ServerSocket(MikuGlobal.INSTRUCTION_SERVICE_PORT);
			socket= new Socket(MikuGlobal.SERVER_ADDRESS,port);
		} catch(Throwable e)
		{
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	public void send(Object obj)
	{
		
		try {
			ObjectOutputStream objOut=new ObjectOutputStream(socket.getOutputStream());
			objOut.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Object recieve()
	{
		Object  obj = null;
		try {
			
			ObjectInputStream objIn=new ObjectInputStream(socket.getInputStream());
			obj=objIn.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public void close()
	{
		
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
