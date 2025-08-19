package 거리두기_확인하기;

import java.util.Arrays;

class Solution {

    private int[] di = {-1, 0, 1, 0};
    private int[] dj = {0, 1, 0, -1};

    private boolean hit;

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);

        for (int t = 0; t < answer.length; t++) {
            hit = false;
            String[] place = places[t];
            for (int i = 0; i < place.length; i++) {
                if (hit) {
                    break;
                }
                for (int j = 0; j < place[i].length(); j++) {
                    if (hit) {
                        break;
                    }
                    if (place[i].charAt(j) == 'P') {
                        boolean[][] visit = new boolean[place.length][place[i].length()];
                        dfs(0, i, j, place, visit);

                        if (hit) {
                            answer[t] = 0;
                            break;
                        }
                    }
                }
            }
            if (!hit) {
                answer[t] = 1;
            }
        }

        return answer;
    }

    private void dfs(int lev, int i, int j, String[] place, boolean[][] visit) {
        if (place[i].charAt(j) == 'P') {
            hit = true;
            return;
        }
        if (lev == 2) {
            return;
        }

        visit[i][j] = true;

        for (int w = 0; w < 4; w++) {
            int nextI = i + di[w];
            int nextJ = j + dj[w];

            if (nextI < 0 || nextI == place.length || nextJ < 0 || nextJ == place[0].length()) {
                continue;
            }
            if (visit[nextI][nextJ]) {
                continue;
            }
            if (place[nextI].charAt(nextJ) == 'X') {
                continue;
            }

            dfs(lev + 1, nextI, nextJ, place, visit);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] answer = s.solution(places);

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }
}
