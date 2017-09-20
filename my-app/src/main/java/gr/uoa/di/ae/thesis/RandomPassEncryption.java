package gr.uoa.di.ae.thesis;

//import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class RandomPassEncryption {

	public String randomPassEncrypt(String str) {
		
		//String encoded = RandomStringUtils.random(str.length());
		//System.out.println("Random password is " + encoded);
		//return encoded;
		
		//BytesKeyGenerator generator = KeyGenerators.secureRandom(str.length());
		//byte[] key = generator.generateKey();
		//return key.toString();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//System.out.println("Password encoder is " + passwordEncoder.toString());
		String encoded = passwordEncoder.encode(str);
		//System.out.println("Random password is " + encoded);
		return encoded;
	}

}
