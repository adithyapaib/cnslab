import java.util.*;
import java.net.*;
public class udpServer {
    public static void main(String args [])throws Exception{
        Scanner s = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        System.out.println("Enter the Message");
        String msg = s.nextLine();
        DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.length(),InetAddress.getByName("localhost"),4000);
        ds.send(dp);
        System.out.println("Message Sent");
        ds.close();
    }
    
}