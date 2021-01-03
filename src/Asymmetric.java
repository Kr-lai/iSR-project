import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

public class Asymmetric {
	private static final String RSA = "RSA";
	private static Scanner sc;

	// Generating public & private keys
	// using RSA algorithm.
	public static KeyPair generateRSAKkeyPair() {
		try {
		    SecureRandom secureRandom = new SecureRandom();
		    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		
		    keyPairGenerator.initialize(2048, secureRandom);
		    return keyPairGenerator.generateKeyPair();
		}
		catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	// Encryption function which converts
	// the plainText into a cipherText
	// using private Key.
	public static byte[] do_RSAEncryption(String plainText, PrivateKey privateKey) {
		try {
		    Cipher cipher = Cipher.getInstance(RSA);
		
		    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		
		    return cipher.doFinal(plainText.getBytes());
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Decryption function which converts
	// the ciphertext back to the
	// orginal plaintext.
	public static String do_RSADecryption(byte[] cipherText, PublicKey publicKey) {
		try {
		    Cipher cipher = Cipher.getInstance(RSA);
		
		    cipher.init(Cipher.DECRYPT_MODE, publicKey);
		    byte[] result = cipher.doFinal(cipherText);
		
		    return new String(result);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
