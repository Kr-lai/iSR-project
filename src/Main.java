import java.security.KeyPair;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		// Symmetric
//		System.out.println("lmao");
//		
//		String key = "key!off";
//		
//		String original = "iSR project";
//		String encrypted = Aes.encrypt(original, key);
//		String decrypted = Aes.decrypt(encrypted, key);
//		
//		System.out.println(original);
//		System.out.println(encrypted);
//		System.out.println(decrypted);
//		
//		// Asymmetric
//		KeyPair keypair = Asymmetric.generateRSAKkeyPair();
//
//	    String plainText = key;
//	
//	    byte[] cipherText = Asymmetric.do_RSAEncryption(plainText, keypair.getPrivate());
//	
//	    System.out.println(
//	        "The Public Key is: " + DatatypeConverter.printHexBinary(keypair.getPublic().getEncoded()));
//	
//	    System.out.println("The Private Key is: " + DatatypeConverter.printHexBinary(keypair.getPrivate().getEncoded()));
//	
//	    System.out.print("The Encrypted Text is: ");
//	
//	    System.out.println(
//	        DatatypeConverter.printHexBinary(
//	            cipherText));
//	
//	    String decryptedText = Asymmetric.do_RSADecryption(cipherText, keypair.getPublic());
//	
//	    System.out.println("The decrypted text is: "+ decryptedText);
		byte[] encryptedSymmetricKey = EncryptionAlgo.EncryptXmlFile("english-xml.xml");
		EncryptionAlgo.DecryptXmlFile("encrypted.txt", encryptedSymmetricKey);
	}
}

