package noChoco;

import noChoco.Permutations;

import java.util.List;

class Alpha12 {
    public static void main(String[] args) {
        Long t0 = System.currentTimeMillis();
        Permutations perm = new Permutations(13, 1);
        for (List<Integer> p : perm) {
            if (isSolution(p)) {
                System.out.println(p);
            }
        }
        Long t1 = System.currentTimeMillis();
        System.out.println("duree = " + (t1 - t0) + " ms");
    }

    public static boolean isSolution(List<Integer> perm) {
        int A = perm.get(0);
        int B = perm.get(1);
        int C = perm.get(2);
        int E = perm.get(3);
        int F = perm.get(4);
        int L = perm.get(5);
        int N = perm.get(6);
        int O = perm.get(7);
        int Q = perm.get(8);
        int R = perm.get(9);
        int T = perm.get(10);
        int U = perm.get(11);
        int S = perm.get(12);
        if (B + A + L + L + E + T == 24 && C + O + N + C + E + R + T == 48 && O + B + O + E == 34
                && C + E + L + L + O == 29 && F + L + U + T + E == 32 && Q + U + A + R + T + E + T == 39
                && S + O + L + O == 37) {
            return true;
        }
        return false;
    }
}