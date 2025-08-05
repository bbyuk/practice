package 줄_서는_방법;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int n = 4;
        long k = 6;
        
        Solution sol = new Solution();

        int[] answer = sol.solution(n, k);

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    static class Solution {
        public int[] solution(int n, long k) {
            int[] answer = new int[n];
            List<Integer> nums = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());

            long[] fact = new long[n+1];
            fact[0] = 1;
            for (int i = 1; i <= n; i++) fact[i] = fact[i-1] * i;

            long remain = k - 1;
            for (int i = 0; i < n; i++) {
                long distance = fact[n - 1 - i];
                int idx = (int)(remain / distance);
                answer[i] = nums.get(idx);
                nums.remove(idx);
                remain = remain % distance;
            }

            return answer;
        }
    }
}
