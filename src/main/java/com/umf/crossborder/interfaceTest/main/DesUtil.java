package com.umf.crossborder.interfaceTest.main;



import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * ******************  类说明  *********************
 * class       :  DESUtil
 * @author     :  niuchuncheng
 * @version    :  1.0  
 * description :  DES加密解密类
 * @see        :                        
 * ***********************************************
 */
public class DesUtil {
	
	private static final int ENCRYPT_MODE = 1; // 加密模式
	private static final int DECRYPT_MODE = 2; // 解密模式

	private static byte[] SECUREKEY = "KSagZxG3".getBytes();
	
	/*
	 * ********************************************
	 * method name   : enc_des 
	 * description   : DES加密方法
	 * @return       : String
	 * @param        : @param showInfo 需要加密的明文信息
	 * @param        : @return 秘文
	 * modified      : lizhibin ,  2013-7-30
	 * @see          : 
	 * *******************************************
	 */
	public static String enc_des(String showInfo){
		byte[] result = null;
		try {
			byte[] tmp = showInfo.getBytes("GBK");
			result = cipher(tmp, SECUREKEY, ENCRYPT_MODE, "DES");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == result ? null : b2hex(result);
	}
	
	/*
	 * ********************************************
	 * method name   : dec_des 
	 * description   : DES解密方法
	 * @return       : String
	 * @param        : @param encryInfo
	 * @param        : @return
	 * @param        : @throws IllegalBlockSizeException
	 * @param        : @throws IllegalArgumentException
	 * modified      : lizhibin ,  2013-7-30
	 * @see          : 
	 * *******************************************
	 */
	public static String dec_des(String encryInfo) throws IllegalBlockSizeException,IllegalArgumentException {
		byte[] result = null;
		String resultStr = null;
		try {
			try {
				byte[] tmp = hex2b(encryInfo);
				result = cipher(tmp,SECUREKEY, DECRYPT_MODE, "DES");
				resultStr = new String(result,"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return resultStr;
		} catch (ArrayIndexOutOfBoundsException e) {//base64抛出数组越界 
			throw new IllegalBlockSizeException(e.getMessage());
		} catch (NullPointerException e) {
			throw new IllegalBlockSizeException(e.getMessage());
		}
	}
	
	/*
	 * ********************************************
	 * method name   : cipher 
	 * description   : 
	 * @return       : byte[]
	 * @param        : @param binfo 明文
	 * @param        : @param bkey  秘钥
	 * @param        : @param mode  类型
	 * @param        : @param transformation 加密方式 , 如DES
	 * @param        : @return
	 * @param        : @throws IllegalBlockSizeException
	 * modified      : lizhibin ,  2013-7-30
	 * @see          : 
	 * *******************************************
	 */
	private static byte[] cipher(byte[] binfo, byte[] bkey, int mode, String transformation) throws IllegalBlockSizeException {
		String algs[] = transformation.split("/");
		SecretKey key = new SecretKeySpec(bkey, algs[0]);
		Cipher cipher = null;
		byte[] result = null;
		try {
			cipher = Cipher.getInstance(transformation);
			cipher.init(mode, key);
			result = cipher.doFinal(binfo);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * ********************************************
	 * method name   : b2hex 
	 * description   : 二进制转换成十六进制
	 * @return       : String
	 * @param        : @param bs
	 * @param        : @return
	 * modified      : lizhibin ,  2013-7-30
	 * @see          : 
	 * *******************************************
	 */
	private static String b2hex(byte[] bs) {
		int iLen = bs.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = bs[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/*
	 * ********************************************
	 * method name   : hex2b 
	 * description   : 十六进制转换二进制
	 * @return       : byte[]
	 * @param        : @param str
	 * @param        : @return
	 * @param        : @throws UnsupportedEncodingException
	 * modified      : lizhibin ,  2013-7-30
	 * @see          : 
	 * *******************************************
	 */
	private static byte[] hex2b(String str) throws UnsupportedEncodingException {
		byte[] arrB = str.getBytes("GBK");
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2,"GBK");
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	public static void main(String[] args) throws Exception {
		String str = "6227000010400001236";
		String bankAccount = "9b37a12d4be8ff3abee4cb7dd6911773d7c395d0bd17ddf7";
		System.out.println(str + "加密后的字符串为：" + DesUtil.enc_des(str));
		System.out.println(bankAccount + "解密后的字符串为：" + DesUtil.dec_des(bankAccount));
	}
}
