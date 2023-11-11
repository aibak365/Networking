import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class Client {
    public static void main(String[] args) {
        try {
            String serverAddress = "localhost";
            int serverPort = 2500;

            String sharedKey = "HiIamAibak123456";
            SecretKey secretKey = new SecretKeySpec(sharedKey.getBytes(), "AES");

            Socket socket = new Socket(serverAddress, serverPort);

            String message = "Aibak 2019904038";
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(encryptedMessage);

            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
