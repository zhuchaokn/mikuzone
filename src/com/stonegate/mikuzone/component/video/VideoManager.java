package com.stonegate.mikuzone.component.video;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.stonegate.mikuzone.util.data.MikuGlobal;


public class VideoManager {

	private  int      idleCount;
	private  int      preCount;
    private  int      instructionCount;
    private  int 	  instructionIdleCount;
	private Document idleDocument;
	private Document preDocument;
	private Document instructionDocument;
	private Document instruttionIdleDocument;
	public VideoManager() { 
		this.update();
	}
	
	public String getIdle (int idleNumber,int type){
		String name=null;
		name=idleDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME)
				         .item(idleNumber)
				         .getFirstChild()
				         .getNodeValue();
		return filePath(name, type);
		
	}
	public String getPre (int preNumber,int type){
		String name=null;
		name=preDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME)
				         .item(preNumber)
				         .getFirstChild()
				         .getNodeValue();
		return filePath(name, type);
		
	}
	public String getInstruction(int instructionNumber,int type){
		String name=null;
		name=instructionDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME)
				         .item(instructionNumber)
				         .getFirstChild()
				         .getNodeValue();
		return filePath(name, type);
		
	}
	public String getInstructionIdle(int ins,int type) {
		String name=null;
		name=instruttionIdleDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME)
				         .item(ins)
				         .getFirstChild()
				         .getNodeValue();
		return filePath(name, type);
	}
	public int getIdleCount() {
		return idleCount;
	}
	
	public int getInstructionCount() {
		return instructionCount;
	}
	
	public int getPreCount() {
		return preCount;
	}
	public int getInstructionIdleCount() {
		return instructionIdleCount;
	}
	public void update(){
		idleDocument=loadXml(MikuGlobal.IDLE_VIDEO_CONFIG);
		preDocument=loadXml(MikuGlobal.PRE_VIDEO_CONFIG);
		instructionDocument=loadXml(MikuGlobal.INSTRUCTION_VIDEO_CONFIG);
		instruttionIdleDocument=loadXml(MikuGlobal.INSTRUCTION_IDLE_VIDEO_CONFIG);
		idleCount=idleDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME).getLength();
		preCount=preDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME).getLength();
		instructionCount=instructionDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME).getLength();
		instructionIdleCount=instruttionIdleDocument.getElementsByTagName(MikuGlobal.FILE_TAG_NAME).getLength();
		}
	
	
	
	
	private Document loadXml(String type)
	
	{
		Document document = null;
		DocumentBuilder builder = null;
		File file= new File(type);
		if(!file.exists()) return null;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return document;
		
	}
	private String filePath(String name,int type)
	{
		return MikuGlobal.VIDEO_PATH+type+"/"+name;
	}
}
