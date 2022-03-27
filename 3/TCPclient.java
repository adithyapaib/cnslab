
import java.net.*;
import java.util.*;
import java.io.*;

public class tc {
    public static void main(String args[]) throws Exception {
        
        System.out.print("Enter the file name :\t");
        String fname = new Scanner(System.in).nextLine() , str;


        Socket sock = new Socket("127.0.0.1", 4000);
        new PrintWriter(sock.getOutputStream(), true).println(fname);


        System.out.println("Contents of the File:");
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        while ((str = fileRead.readLine()) != null)
            System.out.println(str);
        sock.close();
    }

}
