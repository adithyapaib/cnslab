import java.util.*;
import java.net.*;
class s {
    public static void main(String args[]) throws Exception {
        System.out.println("Enter a message : ");
        String s = new Scanner(System.in).nextLine();
        new DatagramSocket().send(new DatagramPacket(s.getBytes(), s.length(), InetAddress.getLocalHost(), 4000));
        System.out.println("Message Sent");
    }
}
