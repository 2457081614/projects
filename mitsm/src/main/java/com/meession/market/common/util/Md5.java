package com.meession.market.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 用MD5加密
 * @author zy
 *
 */
public class Md5 {
	
	/**
	 * 用MD5算法对密码进行加密
	 * @param password
	 * @return
	 */
	public static String makeMD5(String password) {
		MessageDigest md;
		try {
			// 生成一个MD5加密计算摘要
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(password.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String pwd = new BigInteger(1, md.digest()).toString(16);
			System.err.println("加密之前："+password+"\t，加密之后："+pwd);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	/**
	 * 判断密码是否正确
	 * @param newPassword
	 * @param oldPassword
	 * @return 密码正确返回true  错误返回false
	 */
	public static boolean checkPassword(String newPassword,String oldPassword){
		return makeMD5(newPassword).equals(oldPassword);
	}
	
	 public static  String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	        //确定计算方法
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	        return newstr;
	    }
	 
	 public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(checkPassword("12312fdsf","12312fdsf"));
	}
}
