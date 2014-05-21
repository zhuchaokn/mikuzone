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
		
		map.put("天气预报", new MessageHolder(new InstructionDTO(2,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "天气预报","成都","WeatherAction")));
		map.put("唱歌",new MessageHolder(new InstructionDTO(3,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "唱歌",null,"DefaultAction")));
		map.put("问好", new MessageHolder(new InstructionDTO(1,UserInfo.getMikuType(),UserInfo.getUserName(),
										"问好",null,"DefaultAction")));
		map.put("走路", new MessageHolder(new InstructionDTO(4,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "走路",null,"DefaultAction")));
		map.put("default", new MessageHolder(new InstructionDTO(4,UserInfo.getMikuType(),UserInfo.getUserName(),
                                        "default","Sorry,no serverNow","DefaultAction")));
		map.put("生生不息",new MessageHolder(new InstructionDTO(-1,0,null,"Exit",null,"ExitAction")));
		map.put("重生",new MessageHolder(new InstructionDTO(-1,0,null,"Exit",null,"ReturnLogon")));
		map.put("我的消息",new MessageHolder(new InstructionDTO(0,UserInfo.getMikuType(),UserInfo.getUserName(),
				                        "发给我的消息",null,"ReadMessageAction")));
	}
	public  void updateCache(String string, MessageHolder msgHolder) {
		// TODO Auto-generated method stub
	}

	public MessageHolder getInstruction(String ins) {
		// TODO Auto-generated method stub
		MessageHolder msgHolder=null;
		if(ins.equals("问好")||ins.equals("问个好")||ins.equals("给大家问个好"))
		{
			msgHolder=map.get("问好");
		}
		else if(ins.equals("天气")||ins.equals("天气预报")){
			msgHolder=map.get("天气预报");
			ins="成都";
		}
		else if(ins.equals("唱歌")||ins.equals("唱首歌")){
			msgHolder=map.get("唱歌");
		}else if(ins.equals("生生不息"))
		{
			msgHolder=map.get("生生不息");
		}else if(ins.equals("重生"))
		{
			msgHolder=map.get("重生");
		}else if(ins.equals("发给我的消息")||ins.equals("我的消息")){
			msgHolder=map.get("我的消息");
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
