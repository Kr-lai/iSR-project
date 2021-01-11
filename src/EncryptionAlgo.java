import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Scanner; // Import the Scanner class to read text files

public class EncryptionAlgo {
	private static KeyPair keypair = Asymmetric.generateRSAKkeyPair();
	private static String symmetricKey = "example-symmetric-key!";
	
	public static byte[] EncryptFile(String filename) {
		String data = "";
		
		// For encrypting xml file as byte stream
		
		
        // For encrypting text file
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
				System.out.println("Length of data string: " + Integer.toString(temp.length()));
				lineCount += 1;
			}
	      
			myReader.close();
	    }
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		
		String encryptedData = Aes.encrypt(data, symmetricKey);
		
		try {
			File myObj = new File("encrypted.txt");
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
			FileWriter myWriter = new FileWriter("encrypted.txt");
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
	
	public static void DecryptFile(String filename, byte[] encryptedSymmetricKey) {
		String data = "";
		
		try {
			File myObj = new File(filename);
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
		
		String decryptedData = Aes.decrypt(data, Asymmetric.do_RSADecryption(encryptedSymmetricKey, keypair.getPublic()));
		
		try {
			File myObj = new File("english-decrypted.xml");
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
			FileWriter myWriter = new FileWriter("english-decrypted.xml");
			myWriter.write(decryptedData);
			myWriter.close();
			System.out.println("Successfully wrote to the decrypted file.");
	    } 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
	}
}

