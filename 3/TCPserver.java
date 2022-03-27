import java.net.*;
import java.io.*;

public class ts {
    public static void main(String args[]) throws Exception {
      
        System.out.println("Server ready for connection");
        Socket sock =  new ServerSocket(4000).accept();
        System.out.println("Connection is successful");

        BufferedReader fileRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        BufferedReader contentRead = new BufferedReader(new FileReader(fileRead.readLine())); 
        PrintWriter pwrite = new PrintWriter(sock.getOutputStream(), true);


        String str;
        while ((str = contentRead.readLine()) != null) 
            pwrite.println(str);

        System.out.println("Contents of the file is sent...");
        sock.close();
    }
}
