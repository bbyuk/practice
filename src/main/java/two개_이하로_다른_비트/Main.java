package two개_이하로_다른_비트;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = {};

        long test = 1000000000000000L;

        String binaryString = Long.toBinaryString(test);
        System.out.println("binaryString = " + binaryString);

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new long[10]);
    }
}
