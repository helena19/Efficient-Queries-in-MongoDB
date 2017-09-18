package gr.uoa.di.ae.thesis;

import org.apache.commons.codec.digest.DigestUtils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {
		
	public String sha256_encrypt(String str)
	{
		String encoded = DigestUtils.sha256Hex(str);
	    return encoded;
	}
	
	public String sha256_decrypt(String str)
	{
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
		byte [] decoded_bytes = Base64.getDecoder().decode(hash);
//		String decoded=new String(decoded_bytes); 
//		System.out.println("The decoded String "+decoded);
//		byte[] decodedBytes = Base64.getDecoder().decode(str.getBytes());
		String decoded=new String(decoded_bytes,Charset.forName("UTF-8")); 
		System.out.println("The decoded String "+decoded);
	    return decoded;
	}
}
