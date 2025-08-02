package 비밀_코드_해독;

import java.util.*;

public class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;

        /**
         * 1. n으로 5자리 조합 생성 10 C 5
         * 2. 후보군을 순회하면서 q를 모두 만족하면 answer++
         */
        List<List<Integer>> combinations = new ArrayList<>();

        // 1
        for (int i = 1; i <= n - 4; i++) {
            List<Integer> combination = new ArrayList<>();
            combination.add(i);
            fillCombinations(n, combination, combinations);
        }


        // 2
        for (List<Integer> combination : combinations) {
            Set<Integer> set = new HashSet<>(combination);
            boolean complete = true;
            for (int i = 0; i < q.length; i++) {
                int[] query = q[i];
                int hit = 0;
                for (int num : query) {
                    if (set.contains(num)) {
                        hit++;
                    }
                }
                if (ans[i] != hit) {
                    complete = false;
                    break;
                }
            }

            if (complete) {
                answer++;
            }
        }

        return answer;
    }

    private void fillCombinations(int n, List<Integer> current, List<List<Integer>> combinations) {
        if (current.size() == 5) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = current.get(current.size() - 1) + 1; i <= n; i++) {
            current.add(i);
            fillCombinations(n, current, combinations);
            current.remove(current.size() - 1);
        }
    }


}
