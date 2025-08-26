
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, min_moving;   // 2 ≤ N ≤ 10
    static Point[] customerPoints;
    static boolean[] visited;
    static int[] order;
    static Point company, house;

    static class Point  {
        int y, x;

        public Point() {
        }
        
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            // 초기화
            N = Integer.parseInt(br.readLine());
            customerPoints = new Point[N];
            visited = new boolean[N];
            order = new int[N];
            min_moving = Integer.MAX_VALUE;
            company = new Point();
            house = new Point();

            // 입력
            st = new StringTokenizer(br.readLine());
            company.x = Integer.parseInt(st.nextToken());
            company.y = Integer.parseInt(st.nextToken());
            house.x = Integer.parseInt(st.nextToken());
            house.y = Integer.parseInt(st.nextToken());

            for(int i = 0; i < N; i++)  {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                customerPoints[i] = new Point(y, x);
            }

            // 순열 완전 탐색 호출
            dfs(0);

            // 출력
            System.out.println("#" + test_case + " " + min_moving);
		}
	}

    static void dfs(int index) {

        if (index == N) {
            int moving = 0;

            // 1. 회사 -> 첫 번째 방문 고객
            moving += Math.abs(company.x - customerPoints[order[0]].x) + Math.abs(company.y - customerPoints[order[0]].y);

            // 2. 고객들 사이의 이동 경로
            for (int i = 0; i < N - 1; i++) {
                Point currentCustomer = customerPoints[order[i]];
                Point nextCustomer = customerPoints[order[i + 1]];
                moving += Math.abs(currentCustomer.x - nextCustomer.x) + Math.abs(currentCustomer.y - nextCustomer.y);
            }

            // 3. 마지막 방문 고객 -> 집
            moving += Math.abs(house.x - customerPoints[order[N - 1]].x) + Math.abs(house.y - customerPoints[order[N - 1]].y);

            min_moving = Math.min(min_moving, moving);
            return;
        }

        // 순열 만들기
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                order[index] = i;

                visited[i] = true;

                dfs(index + 1);

                visited[i] = false;
            }
        }
    }
    
}
