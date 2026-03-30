package choco;

/**
 * Sudoku solver (abstract class)
 *
 * @author Daniel Diaz
 */
public abstract class SudokuAbstract {

    protected int n;  // size of the grid, e.g. 9
    protected int n0; // square root of n (size of block side), e.g. 3
    protected int[][] grid; // intial grid (incomplete)
    protected int nbClues; // number of known cells
    protected int nbMissing; // number of unknown cells
    protected int[][] sol;  // solution grid

    public SudokuAbstract(SudokuInstance problemData) {
        this(problemData, true);
    }

    public SudokuAbstract(SudokuInstance problemData, boolean displayInfos) {
        this(problemData.getGridAscii(), false);
        if (displayInfos) {
            System.out.println("Sudoku instance: " + problemData
                    + "  size: " + n + "x" + n
                    + "  #clues: " + nbClues
                    + "  #missing: " + nbMissing
                    + "  ER: " + problemData.getLevelER()
                    + "  level Thonky: " + problemData.getLevelThonky());
            displaySudoku(grid);
        }
    }

    public SudokuAbstract(String gridAscii) {
        this(gridAscii, true);
    }

    public SudokuAbstract(String gridAscii, boolean displayInfos) {
        this((int) Math.sqrt(gridAscii.length()));
        int k = 0;
        nbClues = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = gridAscii.charAt(k++);
                if (c == '.') {
                    c = '0';
                }
                if (c != '0') {
                    nbClues++;
                }

                grid[i][j] = (c >= 'A') ? c - 'A' + 10 : c - '0';
            }
        }
        nbMissing = n * n - nbClues;
        if (displayInfos) {
            System.out.println("  size: " + n + "x" + n + "  #clues: " + nbClues + "  #missing: " + nbMissing);
            displaySudoku(grid);
        }

    }

    public SudokuAbstract(int n) { // used by the generator
        this.n = n;
        this.n0 = (int) Math.sqrt(n);
        if (n0 * n0 != n) {
            throw new IllegalArgumentException("size is not a square " + n);
        }
        grid = new int[n][n];
        sol = new int[n][n];
    }

    protected boolean checkSolution() {
        boolean[][] valOnLine = new boolean[n][n];
        boolean[][] valOnCol = new boolean[n][n];
        boolean[][] valOnBlock = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int v = sol[i][j];
                if (v < 1 || v > n) {
                    System.out.println("ERROR given value " + v + " at [" + i + "][" + j + "] not in 1.." + n);
                    return false;
                }
                if (grid[i][j] != 0 && grid[i][j] != v) {
                    System.out.println("ERROR given value " + grid[i][j] + " at [" + i + "][" + j + "] replaced by " + v);
                    return false;
                }
                int k = i / n0 * n0 + j / n0;
                int v0 = v - 1;
                valOnLine[v0][i] = true;
                valOnCol[v0][j] = true;
                valOnBlock[v0][k] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int v = 1; v <= n; v++) {
                if (!valOnLine[v - 1][i]) {
                    System.out.println("on line " + i + " missing value " + v);
                    return false;
                }
                if (!valOnCol[v - 1][i]) {
                    System.out.println("on column " + i + " missing value " + v);
                    return false;
                }
                if (!valOnBlock[v - 1][i]) {
                    System.out.println("on block " + i + " missing value " + v);
                    return false;
                }
            }
        }
        return true;
    }

    public void displaySudoku() {
        displaySudoku(sol);
    }

    public final void displaySudoku(int[][] g) {
        int width = String.valueOf(n).length();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n0; i++) {
            s.append("+");
            for (int j = 0; j < n0 * (width + 1) + 1; j++) {
                s.append("-");
            }
        }
        s.append("+");
        for (int i = 0; i < n; i++) {
            if (i % n0 == 0) {
                System.out.println(" " + s);
            }
            for (int j = 0; j < n; j++) {
                if (j % n0 == 0) {
                    System.out.print(" |");
                }
                if (g[i][j] > 0) {
                    System.out.printf(" %" + width + "d", g[i][j]);
                } else {
                    System.out.printf(" %" + width + "s", ".");
                }
            }
            System.out.println(" |");
        }
        System.out.println(" " + s);
    }

    public String gridToString() {
        return gridToAscii(grid);
    }

    public String gridToAscii(int[][] grid) {
        StringBuilder s = new StringBuilder(n * n);
        for (int[] l : grid) {
            for (int x : l) {
                if (x == 0) {
                    s.append('.');
                } else if (x < 10) {
                    s.append(x);
                } else {
                    s.append((char) (x - 10 + 'A'));
                }
            }
        }

        return s.toString();
    }
}
