package gr.uoa.di.ae.thesis;

import java.security.Key;

public class Encoding {
	private String encoded;
	private Key key;
	
	public Encoding(String encoded, Key key) {
		this.encoded = encoded;
		this.key = key;
	}
	
	public String getEncoded() {
		return this.encoded;
	}
	
	public Key getKey() {
		return this.key;
	}
}
