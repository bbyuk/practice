package 완전범죄;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] info = {{1, 2}, {2, 3}, {2, 1}};
        int n = 4;
        int m = 4;

        Solution sol = new Solution();
        int answer = sol.solution(info, n, m);

        System.out.println(answer);
    }
    static class Solution {
        public int solution(int[][] info, int n, int m) {
            int answer = 0;

            int[][] dp = new int[info.length + 1][m];
            Arrays.stream(dp).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
            dp[0][0] = 0;

            for (int i = 0; i < info.length; i++) {
                int a = info[i][0];
                int b = info[i][1];

                for (int B = 0; B < m; B++) {
                    if (dp[i][B] == Integer.MAX_VALUE) {
                        continue;
                    }

                    dp[i + 1][B] = Math.min(dp[i + 1][B], dp[i][B] + a);

                    if (B + b < m) {
                        dp[i + 1][B + b] = Math.min(dp[i + 1][B + b], dp[i][B]);
                    }
                }
            }

            answer = Integer.MAX_VALUE;

            for (int i = 0; i < m; i++) {
                if (dp[info.length][i] < n) {
                    answer = Math.min(answer, dp[info.length][i]);
                }
            }

            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }

            return answer;
        }
    }
}
