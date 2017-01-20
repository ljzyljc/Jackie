package com.oceansoft.osga.utils;

import com.alibaba.fastjson.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 *
 * @author chenw
 */
public class Md5Util {

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
	    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    
    public static String md5(String str) {
        if (str != null && !str.equals("")) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                byte[] md5Byte = md5.digest(str.getBytes("UTF8"));
                StringBuilder sb = new StringBuilder();
                for (byte aMd5Byte : md5Byte) {
                    sb.append(HEX[(int) (aMd5Byte & 0xff) / 16]);
                    sb.append(HEX[(int) (aMd5Byte & 0xff) % 16]);
                }
                str = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }
    
    public static String md5sum(File file) {
	InputStream fis = null;
	byte[] buffer = new byte[1024];
	int numRead = 0;
	try {
	    fis = new FileInputStream(file);
	    MessageDigest digest = MessageDigest.getInstance("MD5");
	    while ((numRead = fis.read(buffer)) > 0) {
		digest.update(buffer, 0, numRead);
	    }
	    return toHexString(digest.digest());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	finally {
	    if (null != fis) {
	        IOUtils.close(fis);
	    }
	}
	
	return "";
    }
    
    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }
}
