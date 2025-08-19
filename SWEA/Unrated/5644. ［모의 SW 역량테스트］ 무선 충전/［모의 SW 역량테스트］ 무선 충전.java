import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    // 이동 방향: 0(정지), 1(상), 2(우), 3(하), 4(좌)
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};
    static int M, A; // 이동 시간, BC 개수
    static int[] pathA, pathB;
    static BC[] bcs;

    static class BC {
        int x, y, c, p, id; // 위치, 충전범위, 성능, ID

        public BC(int x, int y, int c, int p, int id) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
            this.id = id;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            pathA = new int[M];
            pathB = new int[M];
            bcs = new BC[A];

            // A의 이동 경로
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++)     pathA[i] = Integer.parseInt(st.nextToken());

            // B의 이동 경로
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++)     pathB[i] = Integer.parseInt(st.nextToken());
            

            // BC 정보
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcs[i] = new BC(x, y, c, p, i);
            }

            // 초기 위치
            int ax = 1, ay = 1;
            int bx = 10, by = 10;
            
            int totalCharge = 0;

            // 0초부터 M초까지 M+1번 진행
            for (int t = 0; t <= M; t++) {
                // 1. 현재 위치에서 접속 가능한 BC 리스트 구하기
                List<BC> availableA = getAvailableBCs(ax, ay);
                List<BC> availableB = getAvailableBCs(bx, by);

                // 2. 현재 시간에 얻을 수 있는 최대 충전량 계산
                int maxChargeThisTime = 0;
                // A가 접속할 BC (접속 안하는 경우 포함)
                for (BC bcA : availableA) {
                    // B가 접속할 BC (접속 안하는 경우 포함)
                    for (BC bcB : availableB) {
                        int currentCharge = 0;
                         // 같은 BC에 접속
                        if (bcA.id == bcB.id)   currentCharge = bcA.p;
                        // 다른 BC에 접속
                        else                    currentCharge = bcA.p + bcB.p;
                        
                        maxChargeThisTime = Math.max(maxChargeThisTime, currentCharge);
                    }
                }
                totalCharge += maxChargeThisTime;
                
                // 3. 다음 시간으로 이동 (마지막 시간 M초에는 이동 안 함)
                if(t < M)   {
                    ax += dx[pathA[t]];
                    ay += dy[pathA[t]];
                    bx += dx[pathB[t]];
                    by += dy[pathB[t]];
                }
            }
            System.out.println("#" + test_case + " " + totalCharge);
        }
    }

    // 특정 위치에서 접속 가능한 BC 리스트를 반환하는 함수
    private static List<BC> getAvailableBCs(int x, int y) {
        List<BC> available = new ArrayList<>();
        for (BC bc : bcs) {
            int dist = Math.abs(x - bc.x) + Math.abs(y - bc.y);
            if (dist <= bc.c) available.add(bc);
        }

        // 접속 가능한 BC가 없을 때를 대비해 성능 0짜리 '없음' BC 추가
        available.add(new BC(0, 0, 0, 0, -1)); 
        return available;
    }
}