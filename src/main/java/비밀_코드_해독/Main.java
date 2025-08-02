package 비밀_코드_해독;

public class Main {

    public static void main(String[] args) {
        int n = 10;
        int[][] q = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans = {2, 3, 4, 3, 3};

        Solution solution = new Solution();



        int answer = solution.solution(n, q, ans);
        System.out.println(answer == 3);
    }
}
