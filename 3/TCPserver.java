
/* TCP Conenction */

/* **********SERVER************* */
import java.net.*;
import java.io.*;

public class TCPserver {
    public static void main(String args[]) throws Exception {
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connection");
        Socket sock = sersock.accept();
        System.out.println("Connection is successful and wating for chatting");

        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();
        BufferedReader contentRead = new BufferedReader(new FileReader(fname)); 
       

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        String str;
        while ((str = contentRead.readLine()) != null) {
            pwrite.println(str);

        }
        System.out.println("Contents of the file is sent...");
        sock.close();
        sersock.close(); 
        pwrite.close();
        fileRead.close();
        contentRead.close();
    }
}

