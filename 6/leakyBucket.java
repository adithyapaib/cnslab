/* 12 Leaky Bucket */
import java.util.*;

class Main {
    static int bktSize , pkt , nofpkt , i , time =0 , oprate;
    public  static void main(String args[]) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the BucketSize");
        bktSize = s.nextInt();
        System.out.println("Enter the no Packets");
        nofpkt = s.nextInt();
        System.out.println("Enter the output rate");
        oprate = s.nextInt();
        Random r = new Random();
        for (i=0;i<nofpkt;i++){
            pkt = r.nextInt(100);
            System.out.println("******************************");
            System.out.println("Packet "+i+ " of size " + pkt);
            System.out.println("******************************");
            time = leakyBucket(bktSize,oprate,pkt,time);

        }
        
    }
    public static int leakyBucket(int bktSize,int oprate,int pkt,int time){
       if (bktSize < pkt){
           System.out.println("Time "+ time + "s \nPacket dropped");
           return time;
       }
       else{
           while (pkt> oprate){
                time++;
                pkt = pkt - oprate;
                System.out.println("\nTime" + time +"s \nSent Packet Size of" + oprate + "\n Remaining Packet Size " + pkt);
           }
           if (pkt >0){
               time++;
               System.out.println("\nTime" + time+"s\nlast Packet Size " + pkt);
                pkt = 0;
           }
              return time;
       }
    }

}