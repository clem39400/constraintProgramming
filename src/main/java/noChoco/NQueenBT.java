package noChoco;

import java.util.Arrays;

public class NQueenBT {

    static int n = 10;
    static boolean[] taken = new boolean[n];
    static int[] t = new int[n];
    static int count = 0;

    public static void main(String[] args) {
        solve(0);
        System.out.println("nombre de solutions = " + count);
    }

    static boolean solve(int k) {
        if (k >= n) {
            count++;
            System.out.println(Arrays.toString(t));
            return true;
        }
        for (int v = 0; v < n; v++) {
            if (possibleValue(k, v)) {
                // essais des valeurs
                int saveVar = t[k];
                t[k] = v;
                taken[v] = true;
                if (solve(k + 1)) {
                    return true; // Arrête à la première solution
                }
                // j'anule l'essais et je reviens à l'étape d'avant.
                t[k] = saveVar;
                taken[v] = false;
            }
        }
        return false;
    }

    // k = colone de la reine, v = ligne de la reine
    static boolean possibleValue(int k, int v) {
        if (taken[v]) {
            return false;
        }
        // passage en revue de la compatibilité avec les reines précédentes
        int xCol = k;
        int xLigne = v;
        for (int yCol = 0; yCol < k; yCol++) {
            int yLig = t[yCol];
            if (xCol - yCol == Math.abs(xLigne - yLig)) {
                return false;
            }
        }
        return true;
    }
}
