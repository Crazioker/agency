package gdufs.agency.util;

import java.security.MessageDigest;

public class MD5Util {
	public static String encoderByMd5 (String str){
		 MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
	        byte[] byteArray = str.getBytes();  
	  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	}
	
	public static String doubleEncoderByMd5 (String password){
		return encoderByMd5(encoderByMd5(password));
	}
}
