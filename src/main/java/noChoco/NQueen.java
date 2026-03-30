package noChoco;

import noChoco.Permutations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NQueen {

    public static ArrayList<Integer> rp;

    public static Integer n;

    public static void main(String[] args) {
        n = 4;
        rp = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            rp.add(i, i);
        }
        Collections.shuffle(rp);
        reines(n);
    }

    public static void reines(int taille) {
        int compteur = 0;
        Long t0 = System.currentTimeMillis();
        Permutations perm = new Permutations(taille, 1);
        for (List<Integer> p : perm) {
            if (isSolution(p)) {
                for (int x : p) {
                    System.out.print(rp.get(x) + " ");

                }
                System.out.println();
                compteur++;
                if (n > 10) {
                    break;
                }
            }
        }
        Long t1 = System.currentTimeMillis();
        System.out.println("duree = " + (t1 - t0) + " ms");
        System.out.println("nb de solutions = " + compteur);
    }

    public static boolean isSolution(List<Integer> perm) {
        for (int i = 0; i < perm.size(); i++) {
            int Xi = rp.get(perm.get(i));
            for (int j = i + 1; j < perm.size(); j++) {
                int Xj = rp.get(perm.get(j));
                if (Math.abs(Xi - Xj) == j - i) {
                    return false;
                }
            }
        }
        return true;
    }
}