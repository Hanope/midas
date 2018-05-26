package com.midas.cafe.common;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * User: kimkm
 * Date: 2018-05-27
 * Time: 오전 4:23
 */
public class Crypt
{
	public static String key = "fe8025947de7cd71";

	public static byte[] hexToByteArray(String hex)
	{

		if (hex == null || hex.length() == 0)
		{
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];

		for (int i = 0; i < ba.length; i++)
		{
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return ba;
	}

	/**
	 * byte[] to hex : unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.
	 *
	 * @param ba byte[]
	 * @return
	 */
	public static String byteArrayToHex(byte[] ba)
	{

		if (ba == null || ba.length == 0)
		{
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;

		for (int x = 0; x < ba.length; x++)
		{
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}

		return sb.toString();
	}

	public static String encrypt(String message) throws Exception
	{

		// use key coss2
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

		// Instantiate the cipher
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal(message.getBytes());

		return byteArrayToHex(encrypted);
	}

		public static String decrypt(String encrypted) throws Exception
	{

		// use key coss2
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		byte[] original = cipher.doFinal(hexToByteArray(encrypted));

		String originalString = new String(original);

		return originalString;
	}
}
