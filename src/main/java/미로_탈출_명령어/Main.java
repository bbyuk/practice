package 미로_탈출_명령어;

import java.util.*;

class Solution {
    private static final int START = 1;
    private static final int END = 2;

    private static final int[] DI = {1, 0, 0, -1};
    private static final int[] DJ = {0, -1, 1, 0};

    private static final char[] DC = {'d', 'l', 'r', 'u'};

    private static class State {
        int i;
        int j;
        String command;

        State(int i, int j, String command) {
            this.i = i;
            this.j = j;
            this.command = command;
        }
    }

    private PriorityQueue<String> commands = new PriorityQueue<>();

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        int[][] board = new int[n + 1][m + 1];
        board[x][y] = START;
        board[r][c] = END;

        answer = bfs(n, m, x, y, r, c, board, k);

        return answer;
    }

    private static String bfs(int n, int m, int x, int y, int r, int c, int[][] board, int k) {
        Set<String> visit = new HashSet<>();

        Queue<State> q = new LinkedList<>();
        q.add(new State(x, y, ""));

        while (!q.isEmpty()) {
            State current = q.remove();
            if (board[current.i][current.j] == END && current.command.length() == k) {
                return current.command;
            }

            for (int w = 0; w < 4; w++) {
                int nextI = current.i + DI[w];
                int nextJ = current.j + DJ[w];
                String nextCommand = current.command + DC[w];
                if (nextI < 1 || nextI > n || nextJ < 1 || nextJ > m
                        || visit.contains(nextCommand)
                        || nextCommand.length() > k
                        || getDist(nextI, nextJ, r, c) > k - nextCommand.length()) {
                    continue;
                }
                visit.add(nextCommand);
                q.add(new State(nextI, nextJ, nextCommand));
            }
        }

        return "impossible";
    }

    private static int getDist(int curI, int curJ, int targetI, int targetJ) {
        return Math.abs(targetI - curI) + Math.abs(targetJ - curJ);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int m = 4;
        int x = 2;
        int y = 3;
        int r = 3;
        int c = 1;
        int k = 5;

        String answer = sol.solution(n, m, x, y, r, c, k);
        System.out.println("answer = " + answer);
    }
}
