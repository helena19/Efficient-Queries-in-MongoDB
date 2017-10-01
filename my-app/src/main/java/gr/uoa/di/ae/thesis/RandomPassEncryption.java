package gr.uoa.di.ae.thesis;

//import java.security.InvalidKeyException;
import java.security.Key;
//import java.security.NoSuchAlgorithmException;

//import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
//import org.bson.BasicBSONDecoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import java.security.Key;
//import javax.crypto.Cipher;
import java.util.Base64;
//import javax.crypto.spec.SecretKeySpec;


public class RandomPassEncryption {
	
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

	public String randomPassEncrypt1(String str, BCryptPasswordEncoder passwordEncoder) {
		String encoded = passwordEncoder.encode(str);
		return encoded;
	}
	
	private static Key generateKey() throws Exception {
		return new SecretKeySpec(keyValue, ALGORITHM);
	}
	
	public Encoding randomPassEncrypt2(String str, Cipher c) throws Exception {
		Key key = generateKey();
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
