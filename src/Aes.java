import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes {
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static byte[] IV = new byte[16];
    private static GCMParameterSpec gcmSpec = new GCMParameterSpec(128, IV);
	
	public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
	
	public static String encryptText(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-16")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decryptText(String strToDecrypt, String secret) 
    {
    	try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        System.out.println("Decryption failed");
        return null;
    }
    
    public static void encryptXML(InputStream xmlInput, String secret) {
    	File outputFile = new File("encrypted.txt");
    	
    	try {
    		OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile));
        	
        	setKey(secret);
        	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            while (xmlInput.available() > 0) {
                byte[] bytes = xmlInput.readNBytes(128);
                output.write(cipher.update(bytes));
            }
            output.write(cipher.doFinal());
            
            if (output != null) {
            	output.close();
            }
    	}
    	catch (Exception e) {
    		System.out.println("Error while encrypting: " + e.toString());
    		e.printStackTrace();
    		System.exit(1);
    	}
    }
    
    public static void decryptXML(InputStream encryptedXML, String secret) {
    	File outputFile = new File("english-decrypted.xml");
    	
    	try {
            OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile));
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            while (encryptedXML.available() > 0) {
                byte[] bytes = encryptedXML.readNBytes(128);
                output.write(cipher.update(bytes));
            }

            output.write(cipher.doFinal());
            
            if (output != null) {
            	output.close();
            }
        }
    	catch (Exception e) {
    		System.out.println("Error while decrypting: " + e.toString());
    		System.exit(1);
    	}
    }
}
