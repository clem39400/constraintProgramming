package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class MagicSquare extends MagicSquareAbstract implements ChocoSolver {
    
    private final Model model = new Model("MagicSquare");
    private final IntVar[][] square = model.intVarMatrix("Square", n, n, 1, n2);

    public MagicSquare(int n) {
        super(n);
    }

    @Override
    public boolean solveMagicSquare() {
        return false;
    }

    @Override
    public Solver buildSolver() {

        //AllDiff
        IntVar[] all = new IntVar[n2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                all[i* n + j] = square[i][j];
            }
        }
        model.allDifferent(all).post();

        // Somme des colones, lignes et des diagonales
        IntVar[] diag1 = new IntVar[n];
        IntVar[] diag2 = new IntVar[n];
        for(int i = 0; i < n; i++) {
            model.sum(square[i], "=", sum).post(); //somme des lignes
            IntVar[] colone = new IntVar[n];
            for(int j = 0; j < n; j++) {
                colone[j] = square[j][i]; //création de la colonne
                if(i == j){
                    diag1[i] = square[i][j]; //ajout à la diagonale descendente
                }
                if(j == n - i - 1){
                    diag2[i] = square[i][j]; //ajout à la diagonale montante
                }
            }
            model.sum(colone, "=", sum).post();
        }

        model.sum(diag1, "=", sum).post();
        model.sum(diag2, "=", sum).post();

        //contraintes redondantes pour casser les symétries sinon on a toutes les rotations des chiffres
        model.arithm(square[0][0], "<", square[n-1][0]).post(); //on enlève les symétries sur les lignes
        model.arithm(square[0][0], "<", square[0][n-1]).post(); //on enlève les symétries sur les colones
        model.arithm(square[0][n-1], "<", square[n-1][0]).post(); //on enlève les rotations

        Solver solver = model.getSolver();
        solver.showShortStatisticsOnShutdown();
        return solver;
    }

    @Override
    public void solve() {
        Solver solver = buildSolver();
        if(solver.solve()){
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sol[i][j] = square[i][j].getValue();
                }
            }
        }
        printMagicSquare();
    }

    @Override
    public void solveAll() {
        Solver solver = buildSolver();
        int compteur = 0;
        while(solver.findSolution() != null){
            compteur++;
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sol[i][j] = square[i][j].getValue();
                }
            }
            printMagicSquare();
        }
        System.out.println("total des solutions: " + compteur + " (attention, comprend les rotations des carés)");
    }
}
