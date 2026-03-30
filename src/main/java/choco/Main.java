package choco;


import javax.naming.NameAlreadyBoundException;

public class Main {
    public static void main(String[] args) {
        TwoPlusTwo twoPlusTwo = new TwoPlusTwo();
        //twoPlusTwo.solve();
        //twoPlusTwo.solveAll();

        SendMore sendMore = new SendMore();
        //sendMore.solve();
        //sendMore.solveAll();

        Alpha26 alpha26 = new Alpha26();
        //alpha26.solve();
        //alpha26.solveAll();

        VendingMachine vendingMachine = new VendingMachine(10, 200, 135);
        //vendingMachine.solve();
        //vendingMachine.solveAll();

        NQueen nQueen = new NQueen(8);
        //nQueen.solve();
        //nQueen.solveAll();

        MagicSquare magicSquare = new MagicSquare(8);
        //magicSquare.solve();
        magicSquare.solveAll();
    }
}