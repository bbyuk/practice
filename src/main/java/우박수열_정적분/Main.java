package 우박수열_정적분;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        int n = 1;
        List<Integer> y = new ArrayList<>();
        y.add(k);
        int num = k;
        while((num = getNextNum(num)) > 1) {
            y.add(num);
            n++;
        }
        y.add(num);

        // 0 0
        // a b
        // a -b
        for (int w = 0; w < ranges.length; w++) {
            int[] range = ranges[w];
            int s, e;


            if (range[1] <= 0) {
                s = range[0];
                e = n + range[1];
            }
            else {
                s = range[0];
                e = range[1];
            }

            if (s > e) {
                answer[w] = -1;
            }
            else {
                for (int i = s; i < e; i++) {
                    answer[w] += getSpace(y.get(i), y.get(i + 1));
                }
            }
        }

        return answer;
    }

    private double getSpace(int y1, int y2) {
        if (y1 == y2) {
            // 직사각형
            return y1;
        }
        else {
            // 사다리꼴
            return (y1 + y2) / (double) 2;
        }
    }

    private int getNextNum(int num) {
        if (num % 2 == 0) {
            return num / 2;
        }
        else {
            return (num * 3) + 1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 5;
        int[][] ranges = {{0, 0}, {0, -1}, {2, -3}, {3, -3}};
        double[] answer = sol.solution(k, ranges);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}
