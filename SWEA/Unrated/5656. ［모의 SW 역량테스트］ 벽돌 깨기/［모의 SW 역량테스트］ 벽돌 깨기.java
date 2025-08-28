import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    // 벽돌을 깨는 경우의 수가 12 ^ 4 이기 때문에 백트래킹 완전 탐색
    static int N, W, H; // 1 ≤ N ≤ 4, 2 ≤ W ≤ 12, 2 ≤ H ≤ 15
    static int map[][];
    static int min_brick;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static boolean isIn(int y, int x)   { return y >= 0 && y < H && x >= 0 && x < W; }

    static class Point  {
        int y, x;
        Point(int y, int x) {
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
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            min_brick = Integer.MAX_VALUE;
            
            for(int i = 0; i < H; i++)  {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            // 백트래킹 시작
            dfs(0);

            System.out.println("#" + test_case + " " + min_brick);
		}
	}

    static void dfs(int cnt)   {

        // N개의 벽돌을 모두 떨어트린 경우 최소 벽돌 갯수 갱신
        if(cnt == N)  {
            min_brick = Math.min(min_brick, counting());
            return;
        }

        for(int j = 0; j < W; j++)  {
            // 원본 복사
            int[][] original_map = copy(map);

            // 벽돌 깨기
            break_brick(j);

            // 다음 구슬 사용
            dfs(cnt + 1);

            // 원복
            map = original_map;
        }
    }

    static void break_brick(int j) {

        // 벽돌이 나올 때 까지 아래로 이동
        int h = -1;
        for(int i = 0; i < H; i++)  
            if(map[i][j] != 0)  {
                h = i;
                break;
            }

        // 선택된 행에 벽돌이 없을 경우 종료
        if(h == -1)  return;

        // 선택된 행의 가장 위의 벽돌이 1인 경우
        else if(map[h][j] == 1)  map[h][j] = 0;

        // 선택된 행의 가장 위의 벽돌이 1보다 큰 경우 연쇄 작용
        else    {
            Queue<Point> q = new ArrayDeque<>();
            q.add(new Point(h, j));

            while(!q.isEmpty()) {
                Point c = q.poll();
                int power = map[c.y][c.x];
                map[c.y][c.x] = 0;

                for(int d = 0; d < 4; d++)  {
                    for(int len = 1; len < power; len++)   {
                        int ny = c.y + dy[d] * len;
                        int nx = c.x + dx[d] * len;

                        if(!isIn(ny, nx))       continue;
                        if(map[ny][nx] == 0)    continue;

                        q.add(new Point(ny, nx)); 
                    }
                }
            }
        }

        // 다 깬 후엔 아래로 압축
        for(int x = 0; x < W; x++)  {
            for(int y = H - 1; y >= 0; y--) {
                int k = y;
                while(k >= 0 && map[k][x] == 0) k--;

                if(k < 0)   map[y][x] = 0;
                else    {
                    map[y][x] = map[k][x];
                    if(y != k)  map[k][x] = 0;
                }
            }
        }
    }

    static int[][] copy(int[][] src)    {
        int[][] ret = new int[H][W];
        for(int i = 0; i < H; i++)
            System.arraycopy(src[i], 0, ret[i], 0, W);
        return ret;
    }

    static int counting()   {
        int ret = 0;
        for(int i = 0; i < H; i++) 
            for(int j = 0; j < W; j++)
                if(map[i][j] > 0)
                    ret++;
        return ret;        
    }
}
