import java.util.*;
import java.net.*;
public class udpServer {
    public static void main(String args [])throws Exception{
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the Message");
        String msg = s.nextLine();
        new DatagramSocket().send(new DatagramPacket(msg.getBytes(),msg.length(),InetAddress.getLocalHost(),4000));
        System.out.println("Message Sent");
    }
}