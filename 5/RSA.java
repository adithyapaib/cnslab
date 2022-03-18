import java.util.*;

public class RSA {
    static int mult(int x, int y, int n) {
        int k = 1;
        for (int i = 0; i < y; i++)
            k = (k * x) % n;
        return (int) k;
    }

    static int gcd(int n1, int n2) {
        if (n2 == 0) 
            return n1;
        return gcd(n2, n1 % n2);
    }

    public static void main(String[] args) {
        int pt[] = new int[100];
        int ct[] = new int[100];
        int p, q, z, n, e, d , i;
        String msg;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message: ");
        msg = sc.nextLine();
        System.out.println("Enter 2 prime numbers:");
        p = sc.nextInt();
        q = sc.nextInt();
        n = p * q;
        z = (p - 1) * (q - 1);

        for (e = 2; e < z; e++) 
            if (gcd(e, z) == 1) 
                break;

        for (d = 0; d < z; d++) 
            if ((d * e) % z == 1)
                break;
    
        System.out.println("Public key: " + e + " " + n);
        System.out.println("Private key: " + d + " " + n);

        char msg1[] = msg.toCharArray();
        for ( i = 0; i < msg1.length; i++) 
            pt[i] = msg1[i];
        
        System.out.println("Cipher Text:");
        for ( i = 0; i < msg1.length; i++) 
            System.out.print("\t" +  (ct[i] = mult(pt[i], e, n)));

        System.out.println("\nPlain text:");
        for ( i = 0; i < msg1.length; i++) 
            System.out.print((char)(pt[i] = mult(ct[i], d, n)));
        System.out.println("\n");
    }

}
