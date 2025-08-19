package 거스름돈;

import java.util.HashSet;
import java.util.Set;

class Solution {

    private Set<String> methods = new HashSet<>();

    public int solution(int n, int[] money) {
        int answer = 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i - m] % 1000000007;
            }
        }

        answer = dp[n];
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[] money = {1, 2, 5};
        int answer = sol.solution(n, money);
        System.out.println("answer = " + answer);
    }
}
