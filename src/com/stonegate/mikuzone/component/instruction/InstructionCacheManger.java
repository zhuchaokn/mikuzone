package com.stonegate.mikuzone.component.instruction;

import java.util.HashMap;
import java.util.Map;

import com.stonegate.mikuzone.component.action.DefaultAction;
import com.stonegate.mikuzone.component.action.ExitAction;
import com.stonegate.mikuzone.component.action.InstructionAction;
import com.stonegate.mikuzone.component.action.WeatherAction;
import com.stonegate.mikuzone.model.InstructionDTO;
import com.stonegate.mikuzone.util.data.MessageHolder;
import com.stonegate.mikuzone.util.data.UserInfo;

public class InstructionCacheManger {
	private Map<String, MessageHolder> map;
	private InstructionAction action;
	public InstructionCacheManger()
	{
		map=new HashMap<String, MessageHolder>();
		
		map.put("����Ԥ��", new MessageHolder(new InstructionDTO(2,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "����Ԥ��","�ɶ�","WeatherAction")));
		map.put("����",new MessageHolder(new InstructionDTO(3,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "����",null,"DefaultAction")));
		map.put("�ʺ�", new MessageHolder(new InstructionDTO(1,UserInfo.getMikuType(),UserInfo.getUserName(),
										"�ʺ�",null,"DefaultAction")));
		map.put("��·", new MessageHolder(new InstructionDTO(4,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "��·",null,"DefaultAction")));
		map.put("default", new MessageHolder(new InstructionDTO(4,UserInfo.getMikuType(),UserInfo.getUserName(),
                                        "default","Sorry,no serverNow","DefaultAction")));
		map.put("������Ϣ",new MessageHolder(new InstructionDTO(-1,0,null,"Exit",null,"ExitAction")));
		map.put("����",new MessageHolder(new InstructionDTO(-1,0,null,"Exit",null,"ReturnLogon")));
		map.put("�ҵ���Ϣ",new MessageHolder(new InstructionDTO(0,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "�����ҵ���Ϣ",null,"ReadMessageAction")));
	}
	public  void updateCache(String string, MessageHolder msgHolder) {
		// TODO Auto-generated method stub
	}

	public MessageHolder getInstruction(String ins) {
		// TODO Auto-generated method stub
		MessageHolder msgHolder=null;
		if(ins.equals("�ʺ�")||ins.equals("�ʸ���")||ins.equals("������ʸ���"))
		{
			msgHolder=map.get("�ʺ�");
		}
		else if(ins.equals("����")||ins.equals("����Ԥ��")){
			msgHolder=map.get("����Ԥ��");
			ins="�ɶ�";
		}
		else if(ins.equals("����")||ins.equals("���׸�")){
			msgHolder=map.get("����");
		}else if(ins.equals("������Ϣ"))
		{
			msgHolder=map.get("������Ϣ");
		}else if(ins.equals("����"))
		{
			msgHolder=map.get("����");
		}else if(ins.equals("�����ҵ���Ϣ")||ins.equals("�ҵ���Ϣ")){
			msgHolder=map.get("�ҵ���Ϣ");
		}
		if(msgHolder!=null)
		 { action=msgHolder.getAction();
		   msgHolder.execute(); 
		  }
		return msgHolder;
		//return -1;
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return action.getResults();
	}
}
