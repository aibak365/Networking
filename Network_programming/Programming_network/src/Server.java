import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2500);
            System.out.println("I am waiting on port 2500...");

            String sharedKey = "HiIamAibak123456";
            SecretKey secretKey = new SecretKeySpec(sharedKey.getBytes(), "AES");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String encryptedMessage = in.readLine();

                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] encryptedData = Base64.getDecoder().decode(encryptedMessage);
                byte[] decryptedBytes = cipher.doFinal(encryptedData);
                String decryptedMessage = new String(decryptedBytes);

                System.out.println("Ciphertext: " + encryptedMessage);
                System.out.println("Plaintext: " + decryptedMessage);

                in.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
