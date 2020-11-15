package com.example.assignment.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Md5Util {

	private static Logger logger;

	static {
		logger = Logger.getLogger(Md5Util.class);
	}

	public static String createMd5Hash(String key) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(key.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		logger.debug("[Md5Util][createMd5Hash] Digest(in hex format) : " + sb.toString());
		return sb.toString();
	}
}