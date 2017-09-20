package gr.uoa.di.ae.thesis;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.BasicBSONDecoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;


public class RandomPassEncryption {
	
	private static final String ALGORITHM = "AES";

	public String randomPassEncrypt1(String str) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("Password encoder is " + passwordEncoder.toString());
		String encoded = passwordEncoder.encode(str);
		return encoded;
	}
	
	public Encoding randomPassEncrypt2(byte[] str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
			Cipher c = Cipher.getInstance(ALGORITHM);
			Key key = new SecretKeySpec(str, ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			String encoded = ((PasswordEncoder) new BasicBSONDecoder()).encode(str.toString());
			Encoding encoding = new Encoding(encoded, key);
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
