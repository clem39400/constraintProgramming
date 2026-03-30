package choco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class Sudoku extends SudokuAbstract implements ChocoSolver{

    private Model model = new Model("Sudoku");
    private IntVar[][] v = new IntVar[n][n];

    Sudoku(SudokuInstance sudokuInstance){
        super(sudokuInstance, false);
        for(int i =0; i< n; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 0){
                    v[i][j] = model.intVar(1,9);
                }else{
                    v[i][j] = model.intVar(grid[i][j]);
                }
            }
        }
    }


    @Override
    public Solver buildSolver() {

        //lignes
        for(int i =0; i< n; i++){
            model.allDifferent(v[i]).post();
        }

        //colonnes
        for(int i=0; i<n; i++){
            IntVar[] colone = new IntVar[n];
            for (int j=0; j<n;j++){
                colone[j] = v[j][i];
            }
            model.allDifferent(colone).post();
        }

        //blocs
        for(int b = 0; b<n;b++){
            IntVar[] block = new IntVar[n];
            int k = 0;
            int i0 = b / n0 * n0;
            int j0 = b % n0 * n0;
            for(int i = i0; i < i0 + n0; i++){ //début du block en ligne
                for(int j = j0; j < j0 + n0; j++){ //début du block en colonne
                    block[k++] = v[i][j];
                }
            }
            model.allDifferent(block).post();
        }
        Solver solver = model.getSolver();
        solver.showShortStatisticsOnShutdown();
        return solver;
    }

    @Override
    public void solve(){
        Solver solver = buildSolver();
        if(solver.solve()){
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sol[i][j] = v[i][j].getValue();
                }
            }
        }
        System.out.println("Solution OK ? " + checkSolution());
        displaySudoku(sol);
    }

    @Override
    public void solveAll() {
        Solver solver = buildSolver();
        int nbSolutions = 0;
        while (solver.solve()){
            nbSolutions++;
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sol[i][j] = v[i][j].getValue();
                }
            }
            System.out.println("Solution OK ? " + checkSolution());
            displaySudoku(sol);
            if(nbSolutions > 1){
                System.out.println("ERREUR, on trouve plus d'une solution");
                break;
            }
        }
    }

}
