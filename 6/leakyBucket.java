/* 12 Leaky Bucket */
import java.util.*;

class Main {
    static int bktSize , pkt , nofpkt , i , time =0 , oprate;
    public  static void main(String args[]) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the BucketSize \t");
        bktSize = s.nextInt();
        System.out.print("\nEnter the no Packets \t");
        nofpkt = s.nextInt();
        System.out.print("\nEnter the output rate \t");
        oprate = s.nextInt();
        Random r = new Random();
        for (i=0;i<nofpkt;i++){
            pkt = r.nextInt(100);
            System.out.println("\n\n+****************************+");
            System.out.println("| Packet "+i+ " of size        " + pkt + " |");
            System.out.println("+****************************+");
            time = leakyBucket(bktSize,oprate,pkt,time);

        }
        
    }
    public static int leakyBucket(int bktSize,int oprate,int pkt,int time){
       if (bktSize < pkt)
           System.out.println("Time "+ (time+=1) + "s \nPacket wont fit in bucket ! \nPacket dropped");  
       else{
           while (pkt> oprate)
             System.out.println("Time :" + (time+=1) +" s \nSent Packet Size of " + oprate + "\nRemaining Packet Size " + (pkt -=oprate) + "\n");
           if (pkt >0)
               System.out.println("Time" +(time+=1)+"s\nLast Packet Size " + pkt);
       }
       return time;
    }

}