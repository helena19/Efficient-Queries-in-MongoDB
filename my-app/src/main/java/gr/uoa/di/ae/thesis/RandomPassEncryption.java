package gr.uoa.di.ae.thesis;

import java.security.Key;
import javax.crypto.Cipher;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import java.util.Base64;


public class RandomPassEncryption {
	
	public String randomPassEncryptBCRYPT(String str, BCryptPasswordEncoder passwordEncoder) {
		String encoded = passwordEncoder.encode(str);
		return encoded;
	}
	
	public Encoding randomPassEncryptAES(String str, Cipher c, Key key) throws Exception {
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(str.getBytes());
        Encoding encoding = new Encoding(Base64.getEncoder().encodeToString(encVal), key);
        return encoding;
	}
	
	public String randomPassEncrypt3(String str) {
		String encoded = RandomStringUtils.random(str.length());
		return encoded;
	}
	
	public String randomPassEncrypt4(String str) {
		BytesKeyGenerator generator = KeyGenerators.secureRandom(str.length());
		byte[] key = generator.generateKey();
		String randomPass = key.toString();
		return randomPass;
	}

}
