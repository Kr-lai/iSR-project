import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.util.Scanner; // Import the Scanner class to read text files

public class EncryptionAlgo {
	private static KeyPair keypair = Asymmetric.generateRSAKkeyPair();
	private static String symmetricKey = "example-symmetric-key!";
	
	public static byte[] EncryptTextFile(String filename) {
        // For encrypting text file
		String data = "";
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			int lineCount = 0;

			while (myReader.hasNextLine()) {
				if (lineCount > 0) {
					data += "\n";
				}
				String temp = myReader.nextLine();
				data += temp;
				lineCount += 1;
			}
	      
			myReader.close();
	    }
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		
		// Encrypt text as String
		String encryptedData = Aes.encryptText(data, symmetricKey);
		
		// Write encrypted text to text file
		try {
			File myObj = new File(filename + "-encrypted.txt");
			if (myObj.createNewFile()) {
				System.out.println("Encrypted file created: " + myObj.getName());
			} 
			else {
				System.out.println("Encrypted file already exists.");
			}
	    } 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		
		try {
			FileWriter myWriter = new FileWriter(filename + "-encrypted.txt");
			myWriter.write(encryptedData);
			myWriter.close();
			System.out.println("Successfully wrote to the encrypted file.");
	    } 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		
		return Asymmetric.do_RSAEncryption(symmetricKey, keypair.getPrivate());
	}
	
	public static byte[] EncryptXmlFile(String filename) {
		// For encrypting xml file as byte stream
		InputStream xmlInput = null;
		try {
			xmlInput = new BufferedInputStream(new FileInputStream(filename));
			Aes.encryptXML(xmlInput, filename);
			if (xmlInput != null) {
				xmlInput.close();
			}
		}
		catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Use asymmetric encryption on symmetric aes key
		return Asymmetric.do_RSAEncryption(symmetricKey, keypair.getPrivate());
	}
	
	public static void DecryptTextFile(String filename, byte[] encryptedSymmetricKey) {
		// For decrypting text file
		String data = "";
		
		try {
			File myObj = new File(filename + "-encrypted.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data += myReader.nextLine();
			}
			myReader.close();
	    } 
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		
		String decryptedData = Aes.decryptText(data, Asymmetric.do_RSADecryption(encryptedSymmetricKey, keypair.getPublic()));
		
		try {
			File myObj = new File(filename + "-decrypted.txt");
		      	if (myObj.createNewFile()) {
		      		System.out.println("Decrypted file created: " + myObj.getName());
		      	} 
		      	else {
		      		System.out.println("Decrypted file already exists.");
		      	}
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
			
		try {
			FileWriter myWriter = new FileWriter(filename + "-decrypted.txt");
			myWriter.write(decryptedData);
			myWriter.close();
			System.out.println("Successfully wrote to the decrypted file.");
	    } 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
	}
	
	public static void DecryptXmlFile(String filename, byte[] encryptedSymmetricKey) {
		// For decrypting as xml file
		InputStream encryptedXML = null;
		try {
			encryptedXML = new BufferedInputStream(new FileInputStream(filename));
			Aes.decryptXML(encryptedXML, Asymmetric.do_RSADecryption(encryptedSymmetricKey, keypair.getPublic()));
			if (encryptedXML != null) {
				encryptedXML.close();
			}
		}
		catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			System.exit(1);
		}
	}
}

