package 퍼즐_게임_챌린지;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int mid = 0;

        int max = 300000;
        int min = 1;

        while(min < max) {
            mid = (max + min) / 2;

            if (canPass(diffs, times, limit, mid)) {
                max = mid;
            }
            else {
                min = mid + 1;
            }
        }

        return max;
    }

    private boolean canPass(int[] diffs, int[] times, long limit, int level) {
        int n = diffs.length;
        long needTime = 0L;

        for (int i = 0; i < n; i++) {
            int diff = diffs[i];
            int time = times[i];
            int prevTime = i == 0 ? 0 : times[i - 1];

            if (diff <= level) {
                needTime += time;
            }
            else {
                needTime += (long) (diff - level) * (time + prevTime) + time;
            }

        }


        return limit >= needTime;
    }
}

public class Main {
    public static void main(String[] args) {
//        int[] diffs = {1, 328, 467, 209, 54};
//        int[] times = {2, 7, 1, 4, 3};
//        long limit = 1723L;

        int[] diffs = {1, 4, 4, 2};
        int[] times = {6, 3, 8, 2};
        long limit = 59L;

        Solution sol = new Solution();
        int answer = sol.solution(diffs, times, limit);

        System.out.println("answer = " + answer);
    }
}
