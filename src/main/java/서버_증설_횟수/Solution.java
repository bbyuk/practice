package 서버_증설_횟수;

//{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        /**
         * 1. 0 ~ players length 까지 (총 24 elem) 순회
         * 2. 순회하면서 현재 servers 리스트를 참조해 순회중인 인덱스에 해당하는 시간대의 유저를 감당할 수 있는지 체크
         * 3. 매 순회 마지막에 servers의 peek를 확인해 servers에서 remove 처리
         */
        List<Integer> servers = new LinkedList<>();
        for (int i = 0; i < players.length; i++) {
            int currentTimePlayerCount = players[i];
            int addServerCount = currentTimePlayerCount / m - servers.size();

            // servers 증설
            if (addServerCount > 0) {
                System.out.println(i + "~" + (i +  1) + " : " + addServerCount);
                answer += addServerCount;
                for (int j = 0; j < addServerCount; j++) {
                    servers.add(i + k);
                }
            }

            // 이번 시간대에 반환할 서버 처리
            while (!servers.isEmpty() && servers.get(0) == i + 1) {
                servers.remove(0);
            }
        }
        return answer;
    }
}
