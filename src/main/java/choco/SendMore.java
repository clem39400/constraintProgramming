package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class SendMore implements ChocoSolver{

    private static final Model model = new Model("SEND+MORE=MONEY");
    private static final IntVar S = model.intVar("S", 1, 9);
    private static final IntVar E = model.intVar("E", 0, 9);
    private static final IntVar N = model.intVar("N", 0, 9);
    private static final IntVar D = model.intVar("D", 0, 9);
    private static final IntVar M = model.intVar("M", 1, 9);
    private static final IntVar O = model.intVar("O", 0, 9);
    private static final IntVar R = model.intVar("R", 0, 9);
    private static final IntVar Y = model.intVar("R", 0, 9);

    @Override
    public Solver buildSolver(){
        model.allDifferent(S,E,N,D,M,O,R,Y).post();

        IntVar[] vars = new IntVar[]{
                S,E,N,D,
                M,O,R,E,
                M,O,N,E,Y
        };

        int[] coefs = new int[]{
                1_000, 100, 10, 1,
                1_000, 100, 10, 1,
                -10_000, -1_000, -100, -10, -1
        };

        model.scalar(vars, coefs, "=" , 0).post();
        Solver solver = model.getSolver();
        solver.showShortStatisticsOnShutdown();
        return solver;
    }

    @Override
    public void solve(){
        Solver solver = buildSolver();
        System.out.println(solver.findSolution());
    }

    @Override
    public void solveAll(){
        Utils.solveAll(buildSolver());
    }
}
