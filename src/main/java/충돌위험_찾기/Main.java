package 충돌위험_찾기;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] points = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes = {{2, 3, 4, 5}, {1, 3, 4, 5}};

        int answer = solution.solution(points, routes);

        System.out.println(answer);
    }
}
