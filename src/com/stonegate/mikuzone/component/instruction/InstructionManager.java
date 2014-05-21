package com.stonegate.mikuzone.component.instruction;

import java.util.logging.Logger;

import android.util.Log;

import com.stonegate.mikuzone.model.InstructionDTO;
import com.stonegate.mikuzone.model.InstructionResults;
import com.stonegate.mikuzone.network.NetworkManager;
import com.stonegate.mikuzone.network.impl.NetWorkManagerImpl;
import com.stonegate.mikuzone.util.data.MessageHolder;
import com.stonegate.mikuzone.util.data.MikuGlobal;
import com.stonegate.mikuzone.util.data.UserInfo;

public class InstructionManager {

	private InstructionCacheManger cache;
	private MessageHolder msgHolder;

	public InstructionManager() {
		// TODO Auto-generated constructor stub
		cache = new InstructionCacheManger();
		msgHolder = new MessageHolder("");
	}

	public int getInstruction(InstructionResults results) {
		int res;
		if (results.isRemoteRes())
			res = remoteMessage(results);
		else {
			if (msgHolder.getInstructionDTO().getNextInstruction() != 0)
				res = remoteRecgnize(results.getResults());
			else {
				res = localCache(results.getResults());
				if (res < 0)
					res = remoteRecgnize(results.getResults());
			}
		}
		msgHolder.execute();
		return res;

	}

	public int getPreInsturction() {
		return msgHolder.getInstruction();
	}

	public String getLines() {
		return msgHolder.getLines();
	}

	private int remoteRecgnize(String[] ins) {
		NetworkManager networkManager = new NetWorkManagerImpl();

		InstructionDTO insDTO = (InstructionDTO) networkManager.postObject(
				msgHolder.getMessageDTO(ins), MikuGlobal.HTTP_URL
						+ "/instruction/recognize.json", InstructionDTO.class);
		if (insDTO == null) {
			insDTO = InstructionDTO.serverNullInstruction();
		}
		insDTO.setMikuType(UserInfo.getMikuType());
		Log.i("instrucitonDTO", "" + insDTO.getInstruction());
		msgHolder = new MessageHolder(insDTO);
		msgHolder.execute();
		cache.updateCache(ins[0], msgHolder);
		return msgHolder.getInstruction();
	}

	private int remoteMessage(InstructionResults results) {
		msgHolder = new MessageHolder(results);
		return msgHolder.getInstruction();
	}

	private int localCache(String[] ins) {
		if (ins[0].startsWith("#Ê¶±ð´íÎó")) {
			msgHolder = new MessageHolder(ins[1]);
			return 0;
		}
		MessageHolder msg = cache.getInstruction(ins[0]);
		if (msg == null)
			return -1;
		msgHolder = msg;
		return msgHolder.getInstruction();
	}

	public boolean beforeLines() {
		// TODO Auto-generated method stub
		return msgHolder.beforeLines();
	}

	public InstructionDTO getInstructionDTO() {
		return msgHolder.getInstructionDTO();
	}
}
