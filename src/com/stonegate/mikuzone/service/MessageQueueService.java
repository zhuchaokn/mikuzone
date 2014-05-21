package com.stonegate.mikuzone.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.stonegate.mikuzone.model.Message;

public class MessageQueueService {
	private static  Queue<Message> queue=new ConcurrentLinkedQueue<Message>();
	public static void putMessage(Message message){
		queue.add(message);
	}
	public static Message getMessage(){
		return queue.poll();
	}
}
