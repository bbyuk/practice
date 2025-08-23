package 시소짝꿍;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private class Pair {
        private int root;
        private int id;

        public Pair(int root, int id) {
            this.root = root;
            this.id = id;
        }
    }

    private final int[] DISTANCES = {2, 3, 4};

    /**
     * 1. 선형순회 1회 -> 배열에 숫자들의 갯수 저장 (ex -> arr[100] = 2)
     * 2. 선형순히 1회 -> elem * 2 3 4 : x * 2 3 4
     *
     * @param weights
     * @return
     */

    public long solution(int[] weights) {
        long answer = 0;

        Map<Integer, List<Pair>> nums = new HashMap<>();
        for (int id = 0; id < weights.length; id++) {
            int weight = weights[id];
            for (int distance : DISTANCES) {
                int value = weight * distance;
                nums.putIfAbsent(value, new ArrayList<>());
                nums.get(value).add(new Pair(weight, id));
            }
        }

        for (int id = 0; id < weights.length; id++) {
            int weight = weights[id];
            answer += find(id, weight, nums);
        }

        return answer / 2;
    }

    /**
     * @param target
     * @param nums
     * @return
     */
    private long find(int id, int target, Map<Integer, List<Pair>> nums) {
        long answer = 0;
        // 1. 같은 수

        long sameNumberCount = 0;
        for (int distance : DISTANCES) {
            List<Pair> targetPairs = nums.get(distance * target);
            if (targetPairs != null && targetPairs.size() > 1) {
                for (Pair pair : targetPairs) {
                    if (pair.id == id) {
                        // do nothing
                    }
                    else if (pair.root == target) {
                        sameNumberCount++;
                    }
                    else {
                        answer++;
                    }


                }
            }
        }
        sameNumberCount /= 3;
        answer += sameNumberCount;

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] weights = {100, 180, 360, 100, 270};
        long solution = s.solution(weights);
        System.out.println("solution = " + solution);
    }
}
