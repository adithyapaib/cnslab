import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPclient {
    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("127.0.0.1", 4000);
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the file name :\t");
        String fname =  s.nextLine();

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        System.out.println("Contents of the File:");
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String str;
        while ((str = fileRead.readLine()) != null) {
            System.out.println(str);
        }
        
        sock.close();
        pwrite.close();
        fileRead.close();
    }

}