package noChoco;

import choco.MagicSquareAbstract;

import java.util.List;

/**
 * Magic Square by Generate and Test
 * with permutations
 * Can solve n=3
 *
 * @author Daniel Diaz
 */
public class MagicSquareGT extends MagicSquareAbstract {

    public static void main(String... args) {
        MagicSquareAbstract m = new MagicSquareGT(3);
        if (m.solveMagicSquare()) {
            m.printMagicSquare();
        }
    }

    public MagicSquareGT(int n) {
        super(n);
    }

    @Override
    public boolean solveMagicSquare() {
        Permutations perm = new Permutations(n2, 1);
        for (List<Integer> p : perm) {
            if (isSolution(p)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSolution(List<Integer> p) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sol[i][j] = p.get(i * n + j);
            }
        }
        for (int k = 0; k < n; k++) {
            if (sumLine(k) != sum || sumColumn(k) != sum) {
                return false;
            }
        }
        return sumDiagonal1() == sum && sumDiagonal2() == sum;
    }
}