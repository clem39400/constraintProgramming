package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class Alpha26 implements ChocoSolver{

    private static final Model model = new Model("Alpha26");
    private static final IntVar A = model.intVar("A", 1,26);
    private static final IntVar B = model.intVar("B", 1,26);
    private static final IntVar C = model.intVar("C", 1,26);
    private static final IntVar D = model.intVar("D", 1,26);
    private static final IntVar E = model.intVar("E", 1,26);
    private static final IntVar F = model.intVar("F", 1,26);
    private static final IntVar G = model.intVar("G", 1,26);
    private static final IntVar H = model.intVar("H", 1,26);
    private static final IntVar I = model.intVar("I", 1,26);
    private static final IntVar J = model.intVar("J", 1,26);
    private static final IntVar K = model.intVar("K", 1,26);
    private static final IntVar L = model.intVar("L", 1,26);
    private static final IntVar M = model.intVar("M", 1,26);
    private static final IntVar N = model.intVar("N", 1,26);
    private static final IntVar O = model.intVar("O", 1,26);
    private static final IntVar P = model.intVar("P", 1,26);
    private static final IntVar Q = model.intVar("Q", 1,26);
    private static final IntVar R = model.intVar("R", 1,26);
    private static final IntVar S = model.intVar("S", 1,26);
    private static final IntVar T = model.intVar("T", 1,26);
    private static final IntVar U = model.intVar("U", 1,26);
    private static final IntVar V = model.intVar("V", 1,26);
    private static final IntVar W = model.intVar("W", 1,26);
    private static final IntVar X = model.intVar("X", 1,26);
    private static final IntVar Y = model.intVar("Y", 1,26);
    private static final IntVar Z = model.intVar("Z", 1,26);

    @Override
    public Solver buildSolver(){

        model.allDifferent(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z).post();

        setConstraint(model, 74, C,O,N,C,E,R,T);
        setConstraint(model, 30, F,L,U,T,E);
        setConstraint(model, 74, C, O, N, C, E, R, T);
        setConstraint(model, 30, F, L, U, T, E);
        setConstraint(model, 50, F, U, G, U, E);
        setConstraint(model, 66, G, L, E, E);
        setConstraint(model, 58, J, A, Z, Z);
        setConstraint(model, 47, L, Y, R, E);
        setConstraint(model, 53, O, B, O, E);
        setConstraint(model, 65, O, P, E, R, A);
        setConstraint(model, 59, P, O, L, K, A);
        setConstraint(model, 50, Q, U, A, R, T, E, T);
        setConstraint(model, 134, S, A, X, O, P, H, O, N, E);
        setConstraint(model, 51, S, C, A, L, E);
        setConstraint(model, 37, S, O, L, O);
        setConstraint(model, 61, S, O, N, G);
        setConstraint(model, 82 ,S ,O ,P ,R ,A ,N ,O);
        setConstraint(model, 72,T, H, E, M, E);
        setConstraint(model, 100, V, I, O, L, I, N);
        setConstraint(model, 34, W, A, L, T, Z);
        Solver sol = model.getSolver();
        sol.showShortStatisticsOnShutdown();
        return sol;
    }

    @Override
    public void solve(){
        Solver sol = buildSolver();
        System.out.println(sol.findSolution());
    }

    @Override
    public void solveAll(){
        Utils.solveAll(buildSolver());
        System.out.println("Informations sur les domaines : ");
        Utils.displayDomains(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z);
    }
    private static void setConstraint(Model model, int sum, IntVar... v) {
        model.sum(v, "=", sum).post();
    }
}
