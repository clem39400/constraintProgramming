class Main {

    public static void main(String[] args) {
        method();
    }

    public static void method() {
        int compteur = 0;
        for (int T = 1; T <= 9; T++) {
            for (int W = 0; W <= 9; W++) {
                if (T == W) {
                    continue;
                }
                for (int O = 0; O <= 9; O++) {
                    if (O == T || O == W) {
                        continue;
                    }
                    for (int F = 1; F <= 9; F++) {
                        if (F == T || F == W || F == O) {
                            continue;
                        }
                        for (int U = 0; U <= 9; U++) {
                            if (U == T || U == W || U == O || U == F) {
                                continue;
                            }
                            for (int R = 0; R <= 9; R++) {
                                if (R == T || R == W || R == O || R == F || R == U) {
                                    continue;
                                }
                                if (isSolution(T, W, O, F, U, R)) {
                                    compteur++;
                                    System.out.print("    ");
                                    System.out.println(T * 100 + W * 10 + O);
                                    System.out.print("  + ");
                                    System.out.println(T * 100 + W * 10 + O);
                                    System.out.println(" -------");
                                    System.out.print("  = ");
                                    System.out.println(F * 1000 + O * 100 + U * 10 + R + "\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("nb de solutions = " + compteur);
    }

    public static boolean isSolution(int T, int W, int O, int F, int U, int R) {
        return 2 * (T * 100 + W * 10 + O) == (F * 1000 + O * 100 + U * 10 + R);
    }
}