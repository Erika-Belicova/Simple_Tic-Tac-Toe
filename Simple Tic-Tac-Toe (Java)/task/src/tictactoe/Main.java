package tictactoe;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public int i;
    public int j;
    public int xWins;
    public int oWins;
    int moveCounter = 0;


    public String[] grid() {
        String input = "_________";
        String[] grid = input.split("");
        printGrid(grid);
        return grid;
    }

    public void printGrid(String[] grid) {
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", grid[0], grid[1], grid[2]);
        System.out.printf("| %s %s %s |\n", grid[3], grid[4], grid[5]);
        System.out.printf("| %s %s %s |\n", grid[6], grid[7], grid[8]);
        System.out.println("---------");
    }

    public void gridCheck(String[] grid) {
        // method calls for all checks
        horizontalCheck(grid);
        verticalCheck(grid);
        diagonalCheck(grid);
        winsCheck(grid);
        userMove(grid);
    }

    public void userMove(String[] grid) {
        int coordA = 0;
        int coordB = 0;
        System.out.println("Please enter two numbers for the coordinates of your next move [1-3] [1-3]: ");
        while ((coordA == 0) || (coordB == 0)) {
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNextInt()) {
                coordA = scanner1.nextInt();
                if (!((coordA >= 1) && (coordA <= 3))) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    coordA = 0;
                    continue;
                }
                if (scanner1.hasNextInt()) {
                    coordB = scanner1.nextInt();
                    if (!((coordB >= 1) && (coordB <= 3))) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        coordB = 0;
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        tryMove(grid, coordA, coordB);
    }

    public void tryMove(String[] grid, int coordA, int coordB) {
        int k = 0;
        k = ((coordA - 1) * 3 + (coordB - 1));

        if (grid[k].equals("_")) {
            if (moveCounter % 2 == 0) {
                grid[k] = "X";
                moveCounter++;
            } else {
                grid[k] = "O";
                moveCounter++;
            }
            printGrid(grid);
            gridCheck(grid);
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            userMove(grid);
        }
    }

    public void horizontalCheck(String[] grid) {
        StringBuilder res = new StringBuilder();
        for (i = 0; i < (grid.length - 1); i = i + 3) {
            res.setLength(0);
            for (j = 0; j < 3; j++) {
                res.append(grid[i + j]);
            }
            resultCheck(res.toString());
        }
    }

    public void verticalCheck(String[] grid) {
        StringBuilder res = new StringBuilder();
        for (i = 0; i < 3 ; i++) {
            res.setLength(0);
            for (j = 0; j < grid.length - 1; j = j + 3) {
                res.append(grid[i + j]);
            }
            resultCheck(res.toString());
        }
    }

    public void resultCheck(String res) {
        if (res.equals("XXX")) {
            xWins++;
        } else if (res.equals("OOO")) {
            oWins++;
        }
    }

    public void diagonalCheck(String[] grid) {
        if ((grid[4].equals(grid[0])) && (grid[4].equals(grid[8]))) {
            if (grid[4].equals("X")) {
                xWins++;
            } else if (grid[4].equals("O")) {
                oWins++;
            }
        }

        if (grid[2].equals(grid[4]) && (grid[4].equals(grid[6]))) {
            if (grid[4].equals("X")) {
                System.out.println("X wins");
                xWins++;
            } else if (grid[4].equals("O")) {
                System.out.println("O wins");
                oWins++;
            }
        }
    }

    public void symbolCount(String[] grid) {
        int counterX = 0;
        int counterO = 0;
        for (i = 0; i <= grid.length - 1; i++) {
            if (grid[i].equals("X")) {
                counterX++;
            }
            if (grid[i].equals("O")) {
                counterO++;
            }
        }

        if ((counterX - counterO >= 2) || (counterO - counterX >= 2)) {
            System.out.println("Impossible");
            System.exit(0);
        }
    }

    public void winsCheck(String[] grid) {
        String input = Arrays.toString(grid);
        symbolCount(grid);
        if ((xWins == 0) && (oWins == 0)) {
            if (input.contains("_")) {
                userMove(grid);
            } else {
                System.out.println("Draw");
                System.exit(0);
            }
        } else if ((xWins != 0) && (oWins != 0)) {
            System.out.println("Impossible");
            System.exit(0);
        } else if ((xWins != 0) && (oWins == 0)) {
            System.out.println("X wins");
            System.exit(0);
        } else if ((xWins == 0) && (oWins != 0)) {
            System.out.println("O wins");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        String[] grid = main.grid();
        main.userMove(grid);
    }
}