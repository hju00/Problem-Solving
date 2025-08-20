import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


class Solution
{
    static int N;   //  (2 ≤ N ≤ 100)
    static int cheese[][];
    static boolean visited[][];
    static int max_cnt;
    static int dy[] = {1, 0, -1, 0};
    static int dx[] = {0, 1, 0, -1};

    static class Node  {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x)   { return y >= 0 && y < N && x >= 0 && x < N; }
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());	

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N];
            max_cnt = 0;

            for(int i = 0; i < N; i++)  {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)  cheese[i][j] = Integer.parseInt(st.nextToken());
            }

            for(int day = 0; day <= 100; day++) {

                visited = new boolean[N][N];
                int cnt = 0;

                for(int i = 0; i < N; i++)
                    for(int j = 0; j < N; j++)  
                        if(!visited[i][j] && cheese[i][j] > day)  {
                            bfs(i, j, day);
                            cnt++;
                        }

                max_cnt = Math.max(max_cnt, cnt);
            }

            System.out.println("#" + test_case + " " + max_cnt);
		}
	}

    static void bfs(int y, int x, int day)    {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x));
        visited[y][x] = true;

        while(!q.isEmpty()) {
            Node c = q.poll();

            for(int d = 0; d < 4 ; d++) {
                int ny = c.y + dy[d];
                int nx = c.x + dx[d];

                if(!isIn(ny, nx))   continue;
                if(visited[ny][nx]) continue;
                if(cheese[ny][nx] <= day)    continue;

                q.add(new Node(ny, nx));
                visited[ny][nx] = true;
            }
        }
        
    }
}