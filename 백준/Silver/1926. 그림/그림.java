import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int dy[] = {1, -1, 0, 0};
    static int dx[] = {0, 0, 1, -1};
    static int n, m;        // 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)
    static int map[][];
    static boolean check[][];
    static int max_size = 0;

    static class Node {
        int y;
        int x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x)   { return y >= 0 && y < n && x >= 0 && x < m; }

    static void bfs(int y, int x)
    {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(y, x));
        check[y][x] = true;
        int size = 0;

        while(!q.isEmpty())
        {
            Node cNode = q.poll();
            size++;

            for(int d = 0; d < 4; d++)  {
                int ny = cNode.y + dy[d];
                int nx = cNode.x + dx[d];

                if(!isIn(ny, nx))       continue;
                if(check[ny][nx])       continue;
                if(map[ny][nx] != 1)    continue;

                check[ny][nx] = true;
                q.add(new Node(ny, nx));
            }
        }

        max_size = Math.max(size, max_size);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        check = new boolean[n][m];

        for(int i = 0; i < n; i++)  {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(!check[i][j] && map[i][j] == 1)  {
                    bfs(i, j);
                    cnt++;
                }
                    
        System.out.println(cnt);
        System.out.println(max_size);
    }
}