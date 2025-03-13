import java.util.Scanner;

public class TangoGame {
    private static final int SIZE = 5; // Size of the grid
    private char[][] grid; // Game grid
    private Scanner scanner;

    public TangoGame() {
        grid = new char[SIZE][SIZE];
        scanner = new Scanner(System.in);
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '.'; // Use '.' to represent empty cells
            }
        }
    }

    public void play() {
        while (true) {
            printGrid();
            System.out.println("Enter row (0-4), column (0-4), and symbol (S for Sun, M for Moon): ");
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
            return false; // Out of bounds or invalid symbol
        }
        if (grid[row][col] != '.') {
            return false; // Cell already filled
        }

        // Count existing symbols in the row and column
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

        // Check if placing the symbol would keep the counts equal
        if (symbol == 'S') {
            if (sunCountRow >= SIZE / 2 || sunCountCol >= SIZE / 2) {
                return false; // Cannot place more Suns than allowed
            }
        } else if (symbol == 'M') {
            if (moonCountRow >= SIZE / 2 || moonCountCol >= SIZE / 2) {
                return false; // Cannot place more Moons than allowed
            }
        }

        return true;
    }

    private boolean checkWin() {
        // Check if the grid is filled correctly (basic check)
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

        // Example win condition: equal number of suns and moons
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