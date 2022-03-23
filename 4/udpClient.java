/* UDP Client */
import java.net.*;
public class udpClient {
    public static void main(String args [])throws Exception{
         DatagramPacket dp = new DatagramPacket(new byte[100],100);
            new DatagramSocket(4000).receive(dp);
            System.out.println("Message Received");
            System.out.println(new String(dp.getData())); 
    }
}