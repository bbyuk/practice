package 시소_짝꿍;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        Arrays.sort(weights);

        for (int w : weights) {
            // 1) 이전까지 본 값들과만 매칭 (중복 X)
            answer += freq.getOrDefault(w, 0); // 1:1

            if (w % 3 == 0) answer += freq.getOrDefault(2 * w / 3, 0); // 2:3
            if (w % 2 == 0) answer += freq.getOrDefault(w / 2, 0);     // 1:2
            if (w % 4 == 0) answer += freq.getOrDefault(3 * w / 4, 0); // 3:4

            // 2) 현재 값 등록 (다음 원소들이 이걸 참조)
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] weights = {100, 180, 360, 100, 270};

        long answer = solution.solution(weights);
        System.out.println(answer);
    }
}
