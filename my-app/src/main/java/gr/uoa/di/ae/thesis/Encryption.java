package gr.uoa.di.ae.thesis;

import org.apache.commons.codec.digest.DigestUtils;

public class Encryption {
		
	public String sha256Encrypt(String str)
	{
		String encoded = DigestUtils.sha256Hex(str);
	    return encoded;
	}
	
}
