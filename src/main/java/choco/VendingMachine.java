package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class VendingMachine implements ChocoSolver{

    private int N = 10;
    private final int A;
    private final int B;
    private final int C;
    private final int[] values = {200,100,50,20,10,5};
    Model model = new Model("VendingMachine");

    private final IntVar[] X = model.intVarArray("X", values.length, 0, N);

    //pseudo variable pour éviter de calculer le nombre de pièces rendues à tout moment
    private IntVar nbPieces = model.intVar("pieces", 0, N*values.length);

    //pseudo variable pour trouver la plus grande quantité de poève rendue d'une seule valeur
    private IntVar maxNbPiece = model.intVar("max nb piece", 0, N*values.length);

    public VendingMachine(int N, int A, int B){
        this.A = A;
        this.B = B;
        this.C = A - B;
        this.N = N;
    }

    @Override
    public Solver buildSolver() {
        System.out.println("Change = " + C);
        model.scalar(X, values, "=", C).post();
        model.sum(X, "=", nbPieces).post();
        model.max(maxNbPiece, X).post();
        Solver solver =  model.getSolver();
        return solver;
    }

    @Override
    public void solve() {
        Solver solver = buildSolver();
        System.out.println(solver.findSolution());
    }

    @Override
    public void solveAll() {
        Solver solver = buildSolver();
        solver.showShortStatisticsOnShutdown();
        int compteur = 0;
        while (solver.solve()){
            for (IntVar var : X){
                System.out.print(var.getValue() + " ");
            }
            System.out.print(" #Total Pièce = " + nbPieces.getValue());
            System.out.println(" #Nb max de Pièce rendue = " + maxNbPiece.getValue());
            compteur++;
        }
        System.out.println("Nombre de solutions = " + compteur);

    }
}
