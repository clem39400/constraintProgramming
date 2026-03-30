package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;
import org.chocosolver.solver.variables.IntVar;

public class Decomposition implements ChocoSolver{

    private final Model model = new Model("Decomposition");
    private final int n;
    private final IntVar[] v;

    public Decomposition(int n){
        this.n = n;
        v = model.intVarArray("v", n,0, n);
    }

    @Override
    public Solver buildSolver() {
        model.sum(v, "=", n).post();
        model.regular(v, new FiniteAutomaton("[1-<"+n+">]+0*")).post();//on force les 0 à aller à la fin pour éviter les répétitions
        Solver solver = model.getSolver();
        solver.showShortStatisticsOnShutdown();
        return solver;
    }

    @Override
    public void solve() {
        solveAll();
    }

    @Override
    public void solveAll() {
        Utils.solveAll(buildSolver());
    }
}
