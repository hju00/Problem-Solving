import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


class Solution
{
    static int N, K;    // (3 ≤ N ≤ 8), (1 ≤ K ≤ 5)
    static int map[][];
    static int max_height;
    static int dy[] = {1, 0, -1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int result;

    static class Node   {
        int y;
        int x;
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
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            max_height = 0;
            result = 0;

            map = new int[N][N];

            for(int i = 0; i < N; i++)  {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)  {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max_height = Math.max(max_height, map[i][j]);
                }
            }

            for(int i = 0; i < N; i++)  
                for(int j = 0; j < N; j++)  {
                    for(int k = 0; k <= K; k++) {
                        map[i][j] -= k;
                        bfs();
                        map[i][j] += k;
                    }
                }

            System.out.println("#" + test_case + " " + result);
		}
	}

    static void bfs()   {        

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(map[i][j] == max_height) {

                    Queue<Node> q = new ArrayDeque<>();
                    int visited[][] = new int[N][N];

                    q.add(new Node(i, j));
                    visited[i][j] = 1;

                    while(!q.isEmpty()) {
                        Node cNode = q.poll();

                        for(int d = 0; d < 4; d++)  {
                            int ny = cNode.y + dy[d];
                            int nx = cNode.x + dx[d];

                            if(!isIn(ny, nx))                           continue;
                            if(visited[ny][nx] > visited[cNode.y][cNode.x] + 1)     continue;
                            if(map[ny][nx] >= map[cNode.y][cNode.x])    continue;

                            visited[ny][nx] = visited[cNode.y][cNode.x] + 1;
                            q.add(new Node(ny, nx));
                        }
                    }

                    for(int a = 0; a < N; a++)  
                        for(int b = 0; b < N; b++)  
                            result = Math.max(result, visited[a][b]);

                }
    }
}