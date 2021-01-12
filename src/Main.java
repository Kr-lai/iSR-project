
public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		byte[] encryptedSymmetricKey = EncryptionAlgo.EncryptXmlFile("malay-xml.xml");
		EncryptionAlgo.DecryptXmlFile("encrypted-xml.txt", encryptedSymmetricKey);
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(totalTime);
		
//		String textFileName = "file-1kb.txt";
//		
//		long startTime = System.currentTimeMillis();
//		byte[] encryptedSymmetricKey = EncryptionAlgo.EncryptTextFile(textFileName);
//		EncryptionAlgo.DecryptTextFile(textFileName, encryptedSymmetricKey);
//		long totalTime = System.currentTimeMillis() - startTime;
//		System.out.println(totalTime);
	}
}

