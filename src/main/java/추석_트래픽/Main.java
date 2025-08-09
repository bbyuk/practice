package 추석_트래픽;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    private static class Traffic implements Comparable<Traffic> {
        private final int outSecond;
        private final int inSecond;

        public Traffic(String input) {
            String[] data = input.split(" ");
            this.outSecond = (int) (getSecond(data[1]) * 1000);
            this.inSecond = (int) (outSecond - (Double.parseDouble(data[2].substring(0, data[2].length() - 1))) * 1000);
        }

        @Override
        public int compareTo(Traffic opponent) {
            return Double.compare(this.outSecond, opponent.outSecond);
        }

        public boolean processing(int left, int right) {
            return left <= outSecond && right >= inSecond;
        }

        private double getSecond(String time) {
            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            double second = Double.parseDouble(split[2]);

            return Math.round((second + (minute * 60) + (hour * 60 * 60)) * 1000) / 1000.0;
        }
    }

    public int solution(String[] lines) {
        int answer = 0;
        List<Traffic> trafficList = new ArrayList<>();
        for (String line : lines) {
            trafficList.add(new Traffic(line));
        }
        Collections.sort(trafficList);

        Traffic lastTraffic = trafficList.get(trafficList.size() - 1);
        int right = lastTraffic.outSecond;
        int left = right - 1000;

        while(right >= trafficList.get(0).inSecond) {
            int throughput = 0;
            for (Traffic traffic : trafficList) {
                if (traffic.processing(left, right)) {
                    throughput++;
                }
            }
            answer = Math.max(answer, throughput);
            if (answer == 2) {
                System.out.println("throughput = " + throughput);
            }
            left--;
            right--;
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};

        int answer = sol.solution(lines);
        System.out.println("answer = " + answer);
    }
}
