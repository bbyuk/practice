package 셔틀버스;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int time = 540; // 540 (09:00) ~ 1440 (23:59)
        int shuttleCheck = 0;
        int shuttleCount = 0;
        Arrays.sort(timetable, String::compareTo);
        Queue<Integer> line = new LinkedList<>();
        int timetableIndex = 0;
        int lastCrew = 0;

        while (time < 1440) {

            while(timetableIndex < timetable.length) {
                int nextCrewArrivalTime = getMinute(timetable[timetableIndex]);
                if (nextCrewArrivalTime > time) {
                    break;
                }
                line.add(nextCrewArrivalTime);
                timetableIndex++;
            }

            // 1분마다 체크
            if (shuttleCheck % t == 0 && shuttleCount < n) {
                shuttleCount++;
                // 큐에서 m만큼 빼서 태우기
                int remain = m;
                while (remain > 0
                        && !line.isEmpty()
                        && line.peek() <= time) {
                    lastCrew = line.remove();
                    remain--;
                }

                if (shuttleCount == n) {
                    // 마지막 셔틀
                    if (remain == m) {
                        // 빈셔틀
                        answer = getTimeStringWithMinute(time);
                        break;
                    }
                    else {
                        // 누군가 탔음
                        if (remain == 0) {
                            // 마지막셔틀에 빈자리가 없음 -> 마지막 크루보다 1분일찍
                            answer = getTimeStringWithMinute(lastCrew - 1);
                        }
                        else {
                            // 마지막셔틀에 빈자리 있음 time과 같이 오면 됨
                            answer = getTimeStringWithMinute(time);
                        }
                    }
                }
            }

            shuttleCheck++;
            time++;
        }

        return answer;
    }

    private String getTimeStringWithMinute(int totalMinute) {
        int hour = totalMinute / 60;
        int minute = totalMinute % 60;

        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }

    private int getMinute(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 1;
        int t = 1;
        int m = 5;
        String[] timetable = {"08:00", "08:01", "08:02", "08:03"};

        String answer = sol.solution(n, t, m, timetable);
        System.out.println("answer = " + answer);
    }
}
