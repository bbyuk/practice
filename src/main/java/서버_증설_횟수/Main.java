package 서버_증설_횟수;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;

        int answer = solution.solution(players, m, k);

        System.out.println("answer = " + answer);
    }
}
