package 두_큐_합_같게_만들기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class State {
    int lev;
    int q1Total;
    int q2Total;
    int q1Idx;
    int q2Idx;

    State(int lev, int q1Total, int q2Total, int q1Idx, int q2Idx) {
        this.lev = lev;
        this.q1Total = q1Total;
        this.q2Total = q2Total;
        this.q1Idx = q1Idx;
        this.q2Idx = q2Idx;
    }
}

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        int q1Total = Arrays.stream(queue1).sum();
        int q2Total = Arrays.stream(queue2).sum();
        int max = queue2.length + queue1.length;

        Queue<State> queue = new LinkedList<>();
        boolean[][] visited = new boolean[2 * queue1.length + 1][2 * queue2.length + 1];

        State init = new State(0, q1Total, q2Total, 0, 0);
        queue.add(init);

        while(true) {
            State currentState = queue.remove();
            if (currentState.q1Total == currentState.q2Total) {
                answer = currentState.lev;
                break;
            }
            if (currentState.q1Idx == max && currentState.q2Idx == max) {
                break;
            }

            // q1 -> q2
            int currentQ1Idx = (currentState.q1Idx) % queue1.length;
            int currentQ2Idx = (currentState.q2Idx) % queue2.length;

            if (!visited[currentState.q1Idx + 1][currentState.q2Idx] && currentState.q1Total - queue1[currentQ1Idx] > 0) {
                visited[currentState.q1Idx + 1][currentState.q2Idx] = true;
                queue.add(new State(currentState.lev + 1,
                        currentState.q1Total - queue1[currentQ1Idx],
                        currentState.q2Total + queue1[currentQ1Idx],
                        currentState.q1Idx + 1,
                        currentState.q2Idx));
            }

            // q2 -> q1
            if (!visited[currentState.q1Idx][currentState.q2Idx + 1] && currentState.q2Total - queue2[currentQ2Idx] > 0) {
                visited[currentState.q1Idx][currentState.q2Idx + 1] = true;
                queue.add(new State(currentState.lev + 1,
                        currentState.q1Total + queue2[currentQ2Idx],
                        currentState.q2Total - queue2[currentQ2Idx],
                        currentState.q1Idx,
                        currentState.q2Idx + 1));
            }

        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] queue1 = {1, 1};
        int[] queue2 = {1, 5};

        Solution solution = new Solution();

        int answer = solution.solution(queue1, queue2);
        System.out.println(answer);
    }
}

