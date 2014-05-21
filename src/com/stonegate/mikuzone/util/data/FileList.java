package com.stonegate.mikuzone.util.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class FileList {
	private List<String> list=new ArrayList<String>();
	private Iterator<String> iterator;
	public FileList(){
		iterator=list.iterator();
	}
	public void init(File file) throws FileNotFoundException{
		Scanner scanner=new Scanner(file);
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			if(line!=null&&!line.equals("")){
				list.add(line);	
			}
		}
		iterator=list.iterator();
		scanner.close();
	}
	public boolean hasNext(){
		return iterator.hasNext();
	}
	public String next(){
		return iterator.next();
	}
	public int count() {
		// TODO Auto-generated method stub
		return list.size();
	}
}
