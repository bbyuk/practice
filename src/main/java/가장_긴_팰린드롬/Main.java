package 가장_긴_팰린드롬;


class Solution {
    public int solution(String s) {
        int n = s.length();

        String[] dp = new String[s.length()];
        dp[0] = String.valueOf(s.charAt(0));

        for (int i = 1; i < n; i++) {
            // substring(0, i) 검사
            String s1 = s.substring(0, i + 1);
            // dp[i - 1] 검사
            String s2 = dp[i - 1];
            // String.valueOf(s.charAt(i)) 검사
            String s3 = String.valueOf(s.charAt(i));

            if (isPalindrome(s1)) {
                dp[i] = s1;
            }
            else if (isPalindrome(s2)) {
                dp[i] = s2;
            }
            else if (isPalindrome(s3)) {
                dp[i] = s3;
            }
        }

        return dp[n - 1].length();
    }

    private boolean isPalindrome(String s) {
        int n = s.length() / 2;

        for (int i = 0; i < n; i++) {
            char left = s.charAt(i);
            char right = s.charAt(s.length() - i - 1);

            if (left != right) {
                return false;
            }
        }

        return true;
    }
}

public class Main {

    public static void main(String[] args) {
        String s = "abaabc";

        Solution sol = new Solution();
        int answer = sol.solution(s);

        System.out.println("answer = " + answer);
    }
}
