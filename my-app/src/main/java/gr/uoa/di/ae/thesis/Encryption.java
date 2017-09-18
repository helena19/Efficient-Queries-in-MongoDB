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
	
}
