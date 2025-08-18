package 괄호_변환;

import java.util.Stack;

class Solution {

    public String solution(String p) {
        return process(p);
    }
    private String process(String p) {
        if (p.isEmpty()) {
            return p;
        }

        // 2.
        String[] uv = divide(p);

        // 3.
        String u = uv[0];
        String v = uv[1];

        if (isValid(u)) {
            return u.concat(process(v));
        }
        else {
            StringBuilder answer = new StringBuilder("(");

            answer.append(process(v)).append(")");
            u = u.substring(1, u.length() - 1);
            char[] uCharArray = u.toCharArray();

            for (char c : uCharArray) {
                if (c == ')') {
                    answer.append("(");
                }
                else if (c == '(') {
                    answer.append(")");
                }
            }

            return answer.toString();
        }
    }

    private boolean isValid(String p) {
        Stack<Character> st = new Stack<>();
        char[] charArray = p.toCharArray();

        for (char c : charArray) {
            if (c == '(') {
                st.push(c);
            }
            else if (c == ')') {
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
        }

        return st.isEmpty();
    }

    // u - result[0] / v - result[1];
    private String[] divide(String paren) {
        StringBuilder uBuilder = new StringBuilder();
        StringBuilder vBuilder = new StringBuilder();
        int left = paren.charAt(0) == '(' ? 1 : 0;
        int right = paren.charAt(0) == ')' ? 1 : 0;
        uBuilder.append(paren.charAt(0));
        boolean uBuilt = false;

        char[] charArray = paren.toCharArray();
        for (int i = 1; i < charArray.length; i++) {
            char c = charArray[i];
            if (left == right) {
                uBuilt = true;
            }
            if (!uBuilt) {
                if (c == '(') {
                    left++;
                }
                else if (c == ')') {
                    right++;
                }
                uBuilder.append(c);
            } else {
                vBuilder.append(c);
            }
        }

        return new String[] {uBuilder.toString(), vBuilder.toString()};
    }
}

public class Main {
    public static void main(String[] args) {

        Solution s = new Solution();
        String p = "()))((()";
        String answer = s.solution(p);

        System.out.println("answer = " + answer);
    }
}
