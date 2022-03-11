
/* Shortest Path */
import java.util.*;

public class DVT {
    public static void main(String args[]) {
        int dist[][] = new int[20][20];
        int from[][] = new int[20][20];
        int costmat[][] = new int[10][10];
        int i, j, k, nodes;
        Scanner s = new Scanner(System.in);
        System.out.println("\nEnter the number of nodes : ");
        nodes = s.nextInt();
        System.out.println("\nEnter the cost matrix :\n");
        for (i = 1; i <= nodes; i++) {
            for (j = 1; j <= nodes; j++) {
                costmat[i][j] = s.nextInt();
                costmat[i][i] = 0;
                dist[i][j] = costmat[i][j];
                from[i][j] = j;
            }
        }

        for (i = 1; i <= nodes; i++) {
            for (j = 1; j <= nodes; j++) {
                for (k = 1; k <= nodes; k++) {
                    if ((dist[i][j]) > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        from[i][j] = k;
                    }
                }
            }
        }
        for (i = 1; i <= nodes; i++) {
            System.out.println("\n\nFrom Router Node :" + i);

            System.out.println("\nDesti Node\tNext-Hop\tdistance\n");

            for (j = 1; j <= nodes; j++) {
                System.out.println(j + "\t \t " + from[i][j] + "\t\t " + dist[i][j]);
            }
        }
        System.out.println("\n\n");
    }

}