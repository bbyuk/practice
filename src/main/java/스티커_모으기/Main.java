package 스티커_모으기;

class Solution {
    private int answer = 0;
    public int solution(int[] sticker) {
        boolean[] teared = new boolean[sticker.length];

        for (int start = 0; start < sticker.length; start++) {
            teared[start] = true;
            traversal(sticker, teared, start, 0);
            teared[start] = false;
        }

        return answer;
    }

    public void traversal(int[] sticker, boolean[] teared, int cur, int sum) {
        int left = (cur + 1) % sticker.length;
        int right = (cur + 2) % sticker.length;

        if (teared[left] && teared[right]) {
            // leaf node
            answer = Math.max(answer, sum);
            return;
        }

        if (!teared[left]) {
            teared[(sticker.length + left - 1) % sticker.length] = true;
            teared[left] = true;
            teared[(sticker.length + left + 1) % sticker.length] = true;

            traversal(sticker, teared, left, sum + sticker[left]);

            teared[(sticker.length + left - 1) % sticker.length] = false;
            teared[left] = false;
            teared[(sticker.length + left + 1) % sticker.length] = false;
        }

        if (!teared[right]) {
            teared[(sticker.length + right - 1) % sticker.length] = true;
            teared[right] = true;
            teared[(sticker.length + right + 1) % sticker.length] = true;

            traversal(sticker, teared, right, sum + sticker[right]);

            teared[(sticker.length + right - 1) % sticker.length] = false;
            teared[right] = false;
            teared[(sticker.length + right + 1) % sticker.length] = false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
        int answer = sol.solution(sticker);

        System.out.println("answer = " + answer);
    }
}
