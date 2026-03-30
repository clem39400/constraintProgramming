import java.util.List;

class SendMoney {

    public static void main(String[] args) {
        int compteur = 0;
        KPermutations perm = new KPermutations(8, 10);
        for (List<Integer> p : perm) {
            if (isSolution(p)) {
                System.out.println(p);
                compteur++;
            }
        }
        System.out.println("nb de solutions = " + compteur);
    }

    public static boolean isSolution(List<Integer> perm) {
        int S = perm.get(0);
        int E = perm.get(1);
        int N = perm.get(2);
        int D = perm.get(3);
        int M = perm.get(4);
        int O = perm.get(5);
        int R = perm.get(6);
        int Y = perm.get(7);
        if (S == 0 || M == 0) {
            return false;
        }
        return S * 1000 + E * 100 + N * 10 + D + M * 1000 + O * 100 + R * 10 + E == M * 10_000 + O * 1000 + N * 100
                + E * 10 + Y;
    }
}