import java.util.Scanner;

public class TangoGame {
    private static final int SIZE = 6;
    private char[][] grid;
    private Scanner scanner;

    public TangoGame() {
        grid = new char[SIZE][SIZE];
        scanner = new Scanner(System.in);
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void play() {
        while (true) {
            printGrid();
            System.out.println("Enter row (0-5), column (0-5), and symbol (S for Sun, M for Moon): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char symbol = scanner.next().toUpperCase().charAt(0);

            if (isValidMove(row, col, symbol)) {
                grid[row][col] = symbol;
            } else {
                System.out.println("Invalid move. Try again.");
            }

            if (checkWin()) {
                printGrid();
                System.out.println("Congratulations! You've filled the grid correctly!");
                break;
            }
        }
    }

    private boolean isValidMove(int row, int col, char symbol) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || (symbol != 'S' && symbol != 'M')) {
            return false;
        }
        if (grid[row][col] != '.') {
            return false;
        }

        int sunCountRow = 0;
        int moonCountRow = 0;
        int sunCountCol = 0;
        int moonCountCol = 0;

        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == 'S')
                sunCountRow++;
            if (grid[row][i] == 'M')
                moonCountRow++;
            if (grid[i][col] == 'S')
                sunCountCol++;
            if (grid[i][col] == 'M')
                moonCountCol++;
        }

        if (symbol == 'S') {
            if (sunCountRow >= SIZE / 2 || sunCountCol >= SIZE / 2) {
                return false;
            }
        } else if (symbol == 'M') {
            if (moonCountRow >= SIZE / 2 || moonCountCol >= SIZE / 2) {
                return false;
            }
        }

        return true;
    }

    private boolean checkWin() {
        int sunCount = 0;
        int moonCount = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'S') {
                    sunCount++;
                } else if (grid[i][j] == 'M') {
                    moonCount++;
                }
            }
        }

        return sunCount == moonCount && sunCount + moonCount == SIZE * SIZE;
    }

    private void printGrid() {
        System.out.println("Current Grid:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TangoGame game = new TangoGame();
        game.play();
    }
}