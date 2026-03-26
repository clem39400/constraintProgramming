package choco;

import org.chocosolver.solver.Solver;

public interface ChocoSolver {

    Solver buildSolver();
    void solve();
    void solveAll();
}
