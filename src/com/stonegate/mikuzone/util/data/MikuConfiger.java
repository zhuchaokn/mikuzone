package com.stonegate.mikuzone.util.data;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Properties;

import com.stonegate.mikuzone.service.FileDownloadService;

public class MikuConfiger {
	private FileDownloadService fileDownloadService = new FileDownloadService();
	private FileList fileList =new FileList();
	public static MikuConfiger create() throws Exception {
		MikuConfiger mikuConfiger = new MikuConfiger();
		return mikuConfiger;
	}
	public void checked() {
		fileDownloadService.download(new File(MikuGlobal.VERSION_FILE));
	}

	public boolean hasCheck() throws IOException {
		File file = new File(MikuGlobal.VERSION_FILE);
		if (!file.exists()) {
			return false;
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			String version = scanner.nextLine();
			if (!fileDownloadService.checkVersion(version)) {
				return false;
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return false;
	}

	public boolean hasNext() {
		return fileList.hasNext();
	}

	public String checkNext() {
		String fileName =fileList.next();
		File file = new File(fileName);
		if (!file.exists()) {
			fileDownloadService.download(file);
		}
		return fileName;
	}

	public int getFileCount() {
		return fileList.count();
	}
	public void init() throws IOException {
		File file = new File(MikuGlobal.CONFIG_FILE);
		if (file.exists()) {
			file.delete();
		}
		fileDownloadService.download(file);
		fileList.init(file);
	}
}
