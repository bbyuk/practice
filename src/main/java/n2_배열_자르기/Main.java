package n2_배열_자르기;

import java.util.stream.IntStream;

class Solution {
    public int[] solution(int n, long left, long right) {
        int dist = (int) (right - left + 1);
        int[] answer = new int[dist];
        int[] unit = IntStream.range(1, n + 1).toArray();

        for (int i = 0; i < dist; i++) {
            int filter = (int) ((left + i) / n);
            int target = (int) ((left + i) % n);

            answer[i] = Math.max(unit[target], unit[filter]);
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int left = 2;
        int right =5;
        int[] answer = sol.solution(n, left, right);
        for (int i : answer) {
            System.out.println("i = " + i);
        }
    }
}
