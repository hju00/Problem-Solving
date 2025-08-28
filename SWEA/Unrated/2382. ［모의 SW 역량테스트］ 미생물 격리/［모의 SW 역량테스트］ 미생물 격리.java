import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Solution
{
    static int N, M, K;
    static Microbe[] microbes;
    static int[] dy = {0, -1, 1, 0, 0};    // 상 하 좌 우
    static int[] dx = {0, 0, 0, -1, 1};
    static Microbe[][] visited;

    static boolean isEdge(int y, int x) { return y == 0 || y == N - 1 || x == 0 || x == N - 1; }

    static class Microbe implements Comparable<Microbe>{
        int index;
        int y, x;
        int cnt;
        int dir;

        public Microbe(int index, int y, int x, int cnt, int dir) {
            this.index = index;
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Microbe o) {
            return Integer.compare(o.cnt, this.cnt);
        }
    }
	public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            microbes = new Microbe[K];

            for(int i = 0; i < K; i++)  {
                st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                Microbe m = new Microbe(i, y, x, cnt, dir);
                microbes[i] = m;
            }

            // 미생물이 많은 순서대로 내림차순 정렬
            Arrays.sort(microbes);

            // M 시간 동안 진행
            // ... M 시간 동안 진행 ...
            while (M-- > 0) {
                // 1. (이동 단계) 이동 후 상태를 임시 Map에 저장
                // Key: "y,x" 좌표, Value: 해당 좌표로 모인 미생물 리스트
                Map<String, List<Microbe>> nextStateMap = new HashMap<>();

                for (Microbe m : microbes) {
                    if (m == null || m.cnt == 0) continue;

                    // 이동 및 약품 처리
                    m.y += dy[m.dir];
                    m.x += dx[m.dir];

                    if (isEdge(m.y, m.x)) {
                        m.cnt /= 2;

                        if (m.dir < 3) m.dir = m.dir % 2 + 1; // 1<->2
                        else           m.dir = m.dir % 2 + 3; // 3<->4
                    }
                    
                    // 임시 Map에 추가
                    String key = m.y + "," + m.x;
                    if (!nextStateMap.containsKey(key)) 
                        nextStateMap.put(key, new ArrayList<>());
                    
                    nextStateMap.get(key).add(m);
                }

                // 2. (합체 및 갱신 단계) 다음 세대의 미생물 리스트 생성
                List<Microbe> nextMicrobes = new ArrayList<>();
                for (List<Microbe> mergingList : nextStateMap.values()) {
                    // 합칠 미생물이 없거나, 이동 후 0마리가 되면 소멸
                    if (mergingList.isEmpty() || mergingList.get(0).cnt == 0) continue;

                    if (mergingList.size() == 1) {
                        nextMicrobes.add(mergingList.get(0)); // 합칠 필요 없으면 그대로 추가
                    } else {
                        // 합쳐야 할 경우, 승자를 찾고 미생물 수를 합산
                        Microbe winner = mergingList.get(0);
                        int totalCnt = 0;
                        
                        for (Microbe microbe : mergingList) {
                            if (microbe.cnt > winner.cnt) 
                                winner = microbe;
                            
                            totalCnt += microbe.cnt;
                        }
                        winner.cnt = totalCnt; // 승자의 미생물 수를 총합으로 갱신
                        nextMicrobes.add(winner);
                    }
                }
                
                // 3. 미생물 배열을 다음 세대로 교체
                microbes = new Microbe[K]; // K는 초기 미생물 수, 리스트 크기에 맞춰 조절 필요
                nextMicrobes.toArray(microbes);
            }

            int ans = 0;
            for(Microbe m : microbes)
                if(m != null)
                    ans += m.cnt;

            System.out.println("#" + test_case + " " + ans);
		}
	}
}