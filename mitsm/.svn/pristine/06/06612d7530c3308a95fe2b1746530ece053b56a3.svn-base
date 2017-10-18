package com.meession.market.common.view;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.meession.market.common.util.DataHandler;

@ManagedBean
@ViewScoped
public class FileExportView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String databasePassword;
	private String key;
	private StreamedContent encryptedFile;

	public static void main(String[] args) {
		FileExportView fev = new FileExportView();
		fev.setDatabasePassword("root");
		fev.setKey("adfjfsdflf");
		fev.exportEncryptedFile();
	}
	
	@PostConstruct
	public void init(){
		InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/mitsm/google.png");
		encryptedFile = new DefaultStreamedContent(is, "image/png", "encrypted_data.sql");
	}
	
	
	public void exportEncryptedFile() {
		if (!StringUtils.isBlank(databasePassword) && !StringUtils.isBlank(key)) {
			File file = null;
			try{
				file = DataHandler.exportData(databasePassword, key);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/google.png");
			String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(file.getAbsolutePath());
			encryptedFile = new DefaultStreamedContent(is, contentType, "encrypted_data.sql");
			showResultTip("导出成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("数据库密码和加密密码不能为空", FacesMessage.SEVERITY_ERROR);
		}

	}

	
	/**
	 * 显示提示信息
	 * 
	 * @param tip
	 *            提示信息
	 * @param s
	 */
	public void showResultTip(String tip, FacesMessage.Severity s) {
		String realText = null;
		final int eachWordSize = 30;
		if (s != null && s == FacesMessage.SEVERITY_ERROR) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_INFO) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:#003a6c;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_WARN) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:orange;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_FATAL) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-weight:bold;font-size:18px;'>" + tip + "</span>";
		}
		FacesMessage message = new FacesMessage(s, "提示", realText);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	
	public StreamedContent getEncryptedFile() {
		return encryptedFile;
	}

	public void setEncryptedFile(StreamedContent encryptedFile) {
		this.encryptedFile = encryptedFile;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
