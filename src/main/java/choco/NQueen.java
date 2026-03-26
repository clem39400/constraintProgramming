package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;

public class NQueen implements ChocoSolver{

    private final Model model = new Model("N-Queen");
    private final int N;
    private final IntVar[] Q;

    public NQueen(int N){
        this.N = N;
        Q = model.intVarArray("Queens", N, 1, N);
    }

    @Override
    public Solver buildSolver() {
        model.allDifferent(Q).post();

        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++) {

                //model.arithm(Q[i], "!=", Q[j], "-", j - i).post();
                //model.arithm(Q[i], "!=", Q[j], "+", j - i).post();
                model.distance(Q[i], Q[j], "!=", j - i).post();
            }
        }
        Solver solver = model.getSolver();
        solver.showShortStatisticsOnShutdown();
        return solver;
    }

    @Override
    public void solve() {
        Solver solver = buildSolver();
        System.out.println(solver.findSolution());
    }

    @Override
    public void solveAll() {
        Utils.solveAll(buildSolver());
    }
}
