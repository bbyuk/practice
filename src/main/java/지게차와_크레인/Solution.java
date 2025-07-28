package 지게차와_크레인;

public class Solution {

    private final int[] di = {-1, 0, 1, 0};
    private final int[] dj = {0, 1, 0, -1};

    public int solution(String[] storage, String[] requests) {
        int answer = 0;

        /**
         * 1. storage 2차원 배열의 가장자리를 ''로 감싸는 배열을 두고 파싱
         * 2. 일반 pick 함수(DFS or BFS)와 pickWithCrane 함수(리니어 순회) 구현
         * 3. requests를 순회하면서 함수 처리
         */

        // 1. 배열 전처리
        char[][] storageMap = preprocess(storage);

        // 3. requests 순회하면서 pick 처리
        for (String request : requests) {
            if (request.length() == 1) {
                pick(storageMap, request.charAt(0), 0, 0, new boolean[storageMap.length][storageMap[0].length]);
            }
            else if (request.length() == 2) {
                pickWithCrane(storageMap, request.charAt(0));
            }
        }
        for (int i = 0; i < storageMap.length; i++) {
            for (int j = 0; j < storageMap[0].length; j++) {
                if (storageMap[i][j] != 0) {
                    answer++;
                }
            }
        }


        return answer;
    }

    // 1. storage 전처리 및 파싱
    private char[][] preprocess(String[] storage) {
        int n = storage.length;
        int m = storage[0].length();

        char[][] result = new char[n + 2][m + 2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i + 1][j + 1] = storage[i].charAt(j);
            }
        }

        return result;
    }

    // 2-1. pickWithCrane 함수 - 리니어 순회
    private void pickWithCrane(char[][] storageMap, char target) {
        for (int i = 0; i < storageMap.length; i++) {
            for (int j = 0; j < storageMap[i].length; j++) {
                if (storageMap[i][j] == target) {
                    storageMap[i][j] = 0;
                }
            }
        }
    }

    // 2-2. pick - DFS
    // 0, 0에서 시작
    private void pick(char[][] storageMap, char target, int curI, int curJ, boolean[][] visited) {
        visited[curI][curJ] = true;
        // 0 || target hit || not hit
        if (storageMap[curI][curJ] == 0) {
            for (int w = 0; w < 4; w++) {
                int nextI = curI + di[w];
                int nextJ = curJ + dj[w];
                if (nextI < 0 || nextI == storageMap.length || nextJ < 0 || nextJ == storageMap[0].length || visited[nextI][nextJ]) {
                    continue;
                }

                pick(storageMap, target, nextI, nextJ, visited);
            }
        }
        else if (storageMap[curI][curJ] == target) {
            storageMap[curI][curJ] = 0;
        }
    }
}
