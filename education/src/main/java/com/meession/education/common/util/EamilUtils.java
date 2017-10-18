package com.meession.education.common.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * 
 * @author sam 18073245870@163.com
 *
 */
public class EamilUtils {
	/**
	 * 发送简单邮件
	 * @param to
	 * @param subject
	 * @param msg
	 * @throws Exception
	 */
	public static void sendmail(String to, String subject, String msg) throws Exception {
		Email email = new SimpleEmail();
		email.setHostName(PropertiesUtils.getProperty("mail.host"));
		email.setCharset("utf-8");
		email.addTo(to);
		email.setFrom(PropertiesUtils.getProperty("mail.username"));
		email.setAuthentication(PropertiesUtils.getProperty("mail.username"),
				PropertiesUtils.getProperty("mail.password"));
		email.setSubject(subject);
		email.send();
	}

	/**
	 * 发送html内容的邮件
	 * @param to
	 * @param subject
	 * @param msg
	 * @throws EmailException
	 */
	public static void sendHtmlEmail(String to, String subject, String msg)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(PropertiesUtils.getProperty("mail.host"));
		email.setCharset("utf-8");
		email.addTo(to);
		email.setFrom(PropertiesUtils.getProperty("mail.username"));
		email.setAuthentication(PropertiesUtils.getProperty("mail.username"),
				PropertiesUtils.getProperty("mail.password"));
		email.setSubject(subject);
		email.setHtmlMsg(msg);
		email.send();
	}
	
	/**
	 * 发送html邮件还带简单附件
	 * @param to
	 * @param subject
	 * @param msg
	 * @param attachs
	 * @throws EmailException
	 */
	public static void sendmailWithAttachment(String to, String subject, String msg, EmailAttachment[] attachs)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(PropertiesUtils.getProperty("mail.host"));
		email.setCharset("utf-8");
		email.addTo(to);
		email.setFrom(PropertiesUtils.getProperty("mail.username"));
		email.setAuthentication(PropertiesUtils.getProperty("mail.username"),
				PropertiesUtils.getProperty("mail.password"));
		email.setSubject(subject);
		email.setHtmlMsg(msg);
		for (int i = 0; i < attachs.length; i++) {
			email.attach(attachs[i]);
		}
		email.send();
	}

	/**
	 * 发送带附件的附件email
	 * @param to
	 * @param subject
	 * @param msg
	 * @param fileName
	 */
	public static void sendMultiPartMail(String to, String subject, String msg, String fileName) {

		// 创建附件邮件对象
		MultiPartEmail multipartMail = new MultiPartEmail();
		multipartMail.setCharset("utf-8");
		multipartMail.setHostName(PropertiesUtils.getProperty("mail.host"));
		// 创建一个附件(附件可以是一个本地路径也可以是一个网络url)
		EmailAttachment emailAttachment = new EmailAttachment();
		emailAttachment.setPath(fileName);
		emailAttachment.setDescription(EmailAttachment.ATTACHMENT);
		try {
			multipartMail.setFrom(PropertiesUtils.getProperty("mail.username"));
			multipartMail.setAuthentication(PropertiesUtils.getProperty("mail.username"),
					PropertiesUtils.getProperty("mail.password"));
			multipartMail.addTo(to);
			// multipartMail.addCc("");
			multipartMail.setSubject(subject);
			multipartMail.setMsg(msg);
			// 添加附件
			 multipartMail.attach(emailAttachment);
			// 发送
			multipartMail.send();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
