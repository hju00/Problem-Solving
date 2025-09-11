import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static List<Person> people;
    static List<Stair> stairs;
    static int minTotalTime;
    static int[] selectedStair; // 각 사람이 선택한 계단 인덱스 (0 또는 1)

    static class Person {
        int y, x;
        public Person(int y, int x) { this.y = y; this.x = x; }
    }

    static class Stair {
        int y, x, k;
        public Stair(int y, int x, int k) { this.y = y; this.x = x; this.k = k; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            people = new ArrayList<>();
            stairs = new ArrayList<>();
            minTotalTime = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1)       people.add(new Person(i, j));
                    else if (val > 1)   stairs.add(new Stair(i, j, val));
                    
                }
            }
            
            selectedStair = new int[people.size()];
            dfs(0);

            System.out.println("#" + tc + " " + minTotalTime);
        }
    }

    // 1. 모든 경우의 수 생성 (DFS)
    private static void dfs(int personIdx) {
        // 기저 조건: 모든 사람의 계단 배정이 끝났을 때
        if (personIdx == people.size()) {
            calculateTime();
            return;
        }

        // 재귀 호출: 현재 사람을 0번 또는 1번 계단에 배정
        selectedStair[personIdx] = 0;
        dfs(personIdx + 1);
        
        selectedStair[personIdx] = 1;
        dfs(personIdx + 1);
    }

    // 2. 각 경우에 대한 시간 시뮬레이션
    private static void calculateTime() {
        List<Integer> arrivals1 = new ArrayList<>();
        List<Integer> arrivals2 = new ArrayList<>();

        // a. 각 사람의 계단 도착 시간 계산
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            Stair s = stairs.get(selectedStair[i]);
            int distance = Math.abs(p.y - s.y) + Math.abs(p.x - s.x);
            switch (selectedStair[i]) {
                case 0:
                    arrivals1.add(distance);
                    break;
                case 1:
                    arrivals2.add(distance);
                    break;
            }
        }

        // b. 도착 시간 정렬
        Collections.sort(arrivals1);
        Collections.sort(arrivals2);

        int time1 = simulateStair(arrivals1, stairs.get(0).k);
        int time2 = simulateStair(arrivals2, stairs.get(1).k);

        // 3. 최솟값 갱신
        int totalTime = Math.max(time1, time2);
        minTotalTime = Math.min(minTotalTime, totalTime);
    }

    // c. 계단 내려가기 시뮬레이션
    private static int simulateStair(List<Integer> arrivals, int stairLength) {
        if (arrivals.isEmpty()) return 0;

        List<Integer> finishTimes = new ArrayList<>();
        
        for (int arrivalTime : arrivals) {
            int startTime = arrivalTime + 1; // 계단 입구 도착 후 1분 뒤부터 내려갈 수 있음

            // 계단에 3명이 꽉 차 있다면, 3명 중 가장 먼저 내려온 사람 시간까지 기다려야 함
            if (finishTimes.size() >= 3) {
                int earliestFinish = finishTimes.get(finishTimes.size() - 3);
                startTime = Math.max(startTime, earliestFinish);
            }
            
            int finishTime = startTime + stairLength;
            finishTimes.add(finishTime);
            Collections.sort(finishTimes); // 완료 시간도 정렬을 유지해야 함
        }
        
        return finishTimes.get(finishTimes.size() - 1);
    }
}