package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class TwoPlusTwo implements ChocoSolver{

    private static final Model model = new Model("TWO+TWO=FOUR");
    private static final IntVar T = model.intVar("T", 1, 9);
    private static final IntVar W = model.intVar("W", 0, 9);
    private static final IntVar O = model.intVar("O", 0, 9);
    private static final IntVar F = model.intVar("F", 1, 9);
    private static final IntVar U = model.intVar("U", 0, 9);
    private static final IntVar R = model.intVar("R", 0, 9);


    @Override
    public Solver buildSolver() {

        //AllDifferent
        model.allDifferent(T, W, O, F, U, R).post();

        //Definition des variables
        IntVar[] vars = new IntVar[]{
                T, W, O,
                T, W, O,
                F, O, U, R};

        //Definition des coefficients (on metes des coefs negatif car on veut TWO+TWO-FOUR = 0
        int[] coeffs = new int[]{
                100, 10, 1,
                100, 10, 1,
                -1000, -100, -10, -1};

        //Produit scalaire entre les deux avec 0 comme objectif
        model.scalar(vars, coeffs, "=", 0).post();

        Solver solver =  model.getSolver();
        solver.showShortStatisticsOnShutdown();
        return solver;
    }

    @Override
    public void solve() {
        Solver solver = buildSolver();
        System.out.println(solver.findSolution());
    }

    @Override
    public void solveAll(){
        Utils.solveAll(buildSolver());
        Utils.displayDomains(T,W,O,F,U,R);
    }


}
