package com.meession.market.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.lang.SystemUtils;

/**
 * A utility to fetch all data of tables of designated database.
 * 
 * @author fzh
 *
 */
public class DataHandler {

	/*
	 * public static final String CURRENT_WEB_DIR =
	 * SystemUtils.getUserDir().getAbsolutePath() + File.separator +
	 * "WebContent";
	 */
	public static final String CLASSPATH1 = DataHandler.class.getClassLoader().getResource("").getPath();
	
	public static final String CLASSPATH2 = CLASSPATH1.substring(0, CLASSPATH1.lastIndexOf("/"));
	
	public static final String CLASSPATH3 = CLASSPATH1.substring(0, CLASSPATH1.lastIndexOf("/"));
	
	
//	public static final String CURRENT_WEB_DIR = SystemUtils.getUserHome() + "/workspace/mitsm/WebContent"; 
	public static final String CURRENT_WEB_DIR = CLASSPATH1; 

	private static final String UNENCRYPTED_FILE_NAME = "data.sql";

	public static final String ENCRYPTED_FILE_NAME = "encryptedData.sql";

	public static final String DECRYPTED_FILE_NAME = "decryptedData.sql";

	/**
	 * Export all data to the workspace of server.
	 * 
	 * @param databasePassword
	 *            database password
	 */
	public static File exportData(String databasePassword, String key) throws Throwable {
		File encryptedFile = null;
		Runtime r = Runtime.getRuntime();
		String path = CURRENT_WEB_DIR + File.separator + UNENCRYPTED_FILE_NAME;
		File f = new File(path);
		if(!f.exists())f.createNewFile();
		String command = "mysqldump marketing > " + path + " "
				+ "-uroot -p" + databasePassword;
		String[] cmds = { "/bin/sh", "-c", command };
		try {
			r.exec(cmds);
			Thread.sleep(1000);
			String pathName = CURRENT_WEB_DIR + File.separator + UNENCRYPTED_FILE_NAME;
			
			encryptedFile = encryptFile(key, pathName);
		} catch (IOException e) {
			throw e;
		} catch (Throwable e) {
			throw e;
		}
		return encryptedFile;

	}

	/**
	 * Encrypt the exported sql file and delete it. Keep the cryptographic file.
	 * 
	 * @param key
	 *            key to encrypt.
	 * @param pathName
	 *            source file path.
	 * @throws Throwable
	 * @return encrypted file.
	 */
	private static File encryptFile(String key, String pathName) throws Throwable {
		File file = new File(pathName);
		File encryptedFile = new File(file.getParent() + File.separator + ENCRYPTED_FILE_NAME);
		if(encryptedFile != null && !encryptedFile.exists()){
			encryptedFile.createNewFile();
		}
		
		if (file == null || !file.exists())
			throw new FileNotFoundException("没找到要加密的文件");
		else {
			InputStream is = new FileInputStream(file);
			OutputStream os = new FileOutputStream(encryptedFile);
			EncryptionHandler.encrypt(key, is, os);
			file.delete();
		}
		return encryptedFile;

	}

	/**
	 * Decrypt file by key,
	 * 
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public File decryptFile(String key) throws Throwable {
		File encryptedfile = new File(CURRENT_WEB_DIR + File.separator + ENCRYPTED_FILE_NAME);
		File decryptedfile = new File(CURRENT_WEB_DIR + File.separator + DECRYPTED_FILE_NAME);
		if (encryptedfile == null || !encryptedfile.exists()) {
			throw new FileNotFoundException("加密文件没找到");
		} else {
			InputStream is = new FileInputStream(encryptedfile);
			OutputStream os = new FileOutputStream(decryptedfile);
			EncryptionHandler.decrypt(key, is, os);
		}
		return decryptedfile;
	}

}
