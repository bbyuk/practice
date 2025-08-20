package 뒤에_있는_큰_수_찾기;

import java.util.Stack;

class Solution {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < numbers.length; i++) {
            while(!st.isEmpty() && numbers[st.peek()] < numbers[i]) {
                answer[st.pop()] = numbers[i];
            }
            st.push(i);
        }

        while(!st.isEmpty()) {
            answer[st.pop()] = -1;
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] numbers = {2, 3, 3, 5};
        int[] answer = sol.solution(numbers);

        for (int i : answer) {
            System.out.println("i = " + i);
        }
    }
}
