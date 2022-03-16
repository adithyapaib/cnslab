/* 11 RSA */

/* PRO Tip Use p =11 q=17 */
import java.util.*;

public class RSA {
    static int mult(int x, int y, int n) {
        int k = 1;
        for (int i = 0; i < y; i++) {
            k = (k * x) % n;
        }
        return (int) k;
    }

    public static void main(String[] args) {
        int pt[] = new int[100];
        int ct[] = new int[100];
        int p, q, z, n, e, d;
        String msg;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message: ");
        msg = sc.nextLine();
        System.out.println("Enter 2 prime numbers:");
        p = sc.nextInt();
        q = sc.nextInt();
        n = p * q;
        z = (p - 1) * (q - 1);
        for (e = 2; e < z; e++) {
            if (z % e == 1)
                break;
        }
        for (d = 0; d < z; d++) {
            if ((d * e) % z == 1)
                break;
        }
        System.out.println("Public key: " + e + " " + n);
        System.out.println("Private key: " + d + " " + n);
        char msg1[] = msg.toCharArray();
        for (int i = 0; i < msg1.length; i++) {
            pt[i] = msg1[i];
        }

        System.out.println("Cipher Text:\n");
        for (int i = 0; i < msg1.length; i++) {
            ct[i] = mult(pt[i], e, n);
            System.out.print("\t" + ct[i]);
        }

        System.out.println("\nPlain text:\n");
        for (int i = 0; i < msg1.length; i++) {
            pt[i] = mult(ct[i], d, n);
            System.out.print((char) pt[i]);
        }
        sc.close();
    }

}