//************************************************
// Make sure value of n = (p*q) is greater than 127
//************************************************
import java.util.*;

public class RSA {
    static int mult(int x, int y, int n, int k) {
        for (int  i = 0; i < y; i++) 
            k = (k * x) % n;
        return k;
    }

    static int gcd(int n1, int n2) {
        return (n2 == 0) ? n1 : gcd(n2, n1 % n2);
    }

    public static void main(String[] args) {
        int pt[] = new int[100], ct[] = new int[100];
        int p, q, z, n, e, d, i;
        String msg, ad = "";
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        msg = sc.nextLine();
        do {
            System.out.print("\nEnter p, q :(p*q >127) \t");
            p = sc.nextInt();
            q = sc.nextInt();
            n = p * q;
        } while (!(n > 127));

        z = (p - 1) * (q - 1);
        for (e = 2; e < z; e++)
            if (gcd(e, z) == 1) break;
        for (d = 0; d < z; d++)
            if ((d * e) % z == 1) break;

        System.out.println("\n\nPublic key: " + e + " " + n + " \nPrivate key: " + d + " " + n);
        char msg1[] = msg.toCharArray();
        System.out.print("\n\nCipher Text: \t");
        for (i = 0; i < msg1.length; i++) {
            pt[i] = msg1[i];
            System.out.print( (ct[i] = mult(pt[i], e, n,1)) + "\t" );
            ad = ad + (char)(mult(ct[i], d, n,1));
        }
        System.out.println("\n\nPlain text:\t" + ad);
    }
}