package 연속_부분_수열_합의_개수;

import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int[][] dp = new int[elements.length][elements.length];
        dp[0] = elements;

        Set<Integer> set = new HashSet<>();
        Arrays.stream(elements).forEach(set::add);
        for (int i = 1; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                dp[i][j] = elements[j] + dp[i - 1][(j + 1) % elements.length];
                set.add(dp[i][j]);
            }
        }

        return set.size();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] elements = {7, 9, 1, 1, 4};

        int answer = sol.solution(elements);
        System.out.println(answer);
    }
}
