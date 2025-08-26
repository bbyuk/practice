package NQueen;

class Solution {

    private int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    private int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};

    private int answer = 0;
    public int solution(int n) {

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            backtracking(board, 0, i);
        }

        return answer;
    }

    public void backtracking(int[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board.length) {
            return;
        }
        if (!canPut(board, i, j)) {
            return;
        }
        if (i == board.length - 1) {
            answer++;
            return;
        }

        for (int nextJ = 0; nextJ < board.length; nextJ++) {
            board[i][j] = 1;
            backtracking(board, i + 1, nextJ);
            board[i][j] = 0;
        }
    }

    public boolean canPut(int[][] board, int i, int j) {
        for (int ci = 0; ci < board.length; ci++) {
            // column 체크
            if (board[ci][j] == 1) {
                return false;
            }

            int checkI;
            int checkJ;

            checkI = i - ci;
            checkJ = j - ci;

            if ((checkI > -1 && checkJ > -1) && board[checkI][checkJ] == 1) {
                return false;
            }

            checkI = i - ci;
            checkJ = j + ci;
            if ((checkI > -1 && checkJ < board.length) && board[checkI][checkJ] == 1) {
                return false;
            }

            checkI = i + ci;
            checkJ = j - ci;
            if ((checkI < board.length && checkJ > -1) && board[checkI][checkJ] == 1) {
                return false;
            }

            checkI = i + ci;
            checkJ = j + ci;
            if ((checkI < board.length && checkJ < board.length) && board[checkI][checkJ] == 1) {
                return false;
            }

        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 4;

        int answer = sol.solution(4);
        System.out.println("answer = " + answer);
    }
}
