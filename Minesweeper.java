package chap_00;
import java.util.*;

public class Minesweeper {
    private static final char MINE = '*';
    private static final char EMPTY = ' ';
    private static final char UNKNOWN = '?';
    private static final char FLAG = '★';
    private static final int SIZE = 5;
    private static final char[][] board = new char[SIZE][SIZE];
    private static final char[][] displayedBoard = new char[SIZE][SIZE];
    private static final Random random = new Random();

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        while (true) {
            System.out.println("1. 탐색, 2. 마킹");
            int choice = new Scanner(System.in).nextInt();

            if (choice == 1 || choice == 2) { // 선택 검사
                System.out.print("행과 열을 선택하세요 (예: 2 3): ");
                int row = new Scanner(System.in).nextInt();
                int col = new Scanner(System.in).nextInt();

                if (choice == 1) {
                    if (board[row][col] == MINE) {
                        System.out.println("폭탄을 밟았습니다!");
                        return;
                    } else {
                        reveal(row, col);
                    }
                } else if (choice == 2) {
                    mark(row, col);
                }

                displayBoard();

                if (allNonMinesRevealed()) {
                    System.out.println("지뢰찾기성공");
                    return;
                }
            } else {
                System.out.println("잘못 선택하셨습니다");
            }
        }
    }

    private static void initializeBoard() { //지뢰갯수설정
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = (random.nextInt(5) == 0) ? MINE : EMPTY;
                displayedBoard[i][j] = UNKNOWN;
            }
        }
    }

    private static void displayBoard() { //지뢰판 표시
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("+===");
            }
            System.out.println("+");

            for (int j = 0; j < SIZE; j++) {
                System.out.print("| " + displayedBoard[i][j] + " ");
            }
            System.out.println("|");
        }

        for (int j = 0; j < SIZE; j++) {
            System.out.print("+===");
        }
        System.out.println("+");
    }

    private static void reveal(int row, int col) { //밝히기
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || (displayedBoard[row][col] != UNKNOWN && displayedBoard[row][col] != FLAG)) {
            return;
        }

        int minesAround = countMinesAround(row, col);
        if (minesAround > 0) {
            displayedBoard[row][col] = (char) (minesAround + '0');
        } else {
            displayedBoard[row][col] = EMPTY;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    reveal(row + i, col + j);
                }
            }
        }
    }

    private static int countMinesAround(int row, int col) { //주변 지뢰 몇개인지 표시
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (isMine(row + i, col + j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isMine(int row, int col) {   //지뢰여부
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        return board[row][col] == MINE;
    }

    private static boolean allNonMinesRevealed() { //지뢰만 남았다면 게임 끝내기
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != MINE && (displayedBoard[i][j] == UNKNOWN || displayedBoard[i][j] == FLAG)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void mark(int row, int col) { //마킹
        if (displayedBoard[row][col] == UNKNOWN) {
            displayedBoard[row][col] = FLAG;
        } else if (displayedBoard[row][col] == FLAG) {
            displayedBoard[row][col] = UNKNOWN;
        }
    }
}