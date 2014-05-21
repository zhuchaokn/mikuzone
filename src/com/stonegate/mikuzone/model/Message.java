package com.stonegate.mikuzone.model;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 8436252329435624729L;
    private int instruction;
    private String originUserName;
    private String targetUserName;
    private String  msg;

    public Message(){
    	
    }
    public Message(InstructionDTO dto) {
        this.setInstruction(dto.getInstruction());
        originUserName = dto.getSender();
        targetUserName = dto.getParam();
        this.msg=dto.getRecogResults()[0];
    }

    public int getInstruction() {
        return instruction;
    }

    public void setInstruction(int instruction) {
        this.instruction = instruction;
    }

    public String getOriginUserName() {
        return originUserName;
    }

    public void setOriginUserName(String originUserName) {
        this.originUserName = originUserName;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
