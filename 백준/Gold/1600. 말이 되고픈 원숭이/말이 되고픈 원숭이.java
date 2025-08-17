import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int K, H, W;    // W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수
    static int map[][];
    static boolean visited[][][];
    static int knight_DY[] = {-1 , -1, -2, -2, 1, 1, 2, 2};
    static int knight_DX[] = {-2, 2, -1, 1, -2, 2, -1, 1};
    static int dy[] = {1, 0, -1, 0};
    static int dx[] = {0, 1, 0, -1};
    // static int min_moving = Integer.MAX_VALUE;

    static class Node   {
        int y, x, cnt, knight_cnt;
        Node(int y, int x, int cnt, int knight_cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.knight_cnt = knight_cnt;
        }
    }

    static boolean isIn(int y, int x)   {   return y >= 0 && y < H && x >= 0 && x < W;  }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for(int i = 0; i < H; i++)  {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++)  map[i][j] = Integer.parseInt(st.nextToken());
        }

        // visited[0][0] = true;
        // dfs(0, 0, 0);

        // 도착점 갈 수 없는 경우
        // if(min_moving == Integer.MAX_VALUE) System.out.println(-1);
        // else                                System.out.println(min_moving);

        System.out.println(bfs(0, 0));
    }

    static int bfs(int cy, int cx) {
        Queue<Node> q = new ArrayDeque<>();
        visited[cy][cx][K] = true;
        q.offer(new Node(cy, cx, 0, K));

        while(!q.isEmpty())
        {
            Node cNode = q.poll();
            
            if(cNode.y == H - 1 && cNode.x == W - 1)    return cNode.cnt;

            if(cNode.knight_cnt > 0)    {
                for(int d = 0; d < 8; d++)  {
                    int ny = cNode.y + knight_DY[d];
                    int nx = cNode.x + knight_DX[d];
                    int nKnightCnt = cNode.knight_cnt - 1;

                    if(!isIn(ny, nx))       continue;
                    if(map[ny][nx] == 1)    continue;
                    if(visited[ny][nx][nKnightCnt])     continue;

                    visited[ny][nx][nKnightCnt] = true;
                    q.offer(new Node(ny, nx, cNode.cnt + 1, nKnightCnt));
                }
            }

            for(int d = 0; d < 4; d++)  {
                int ny = cNode.y + dy[d];
                int nx = cNode.x + dx[d];

                if(!isIn(ny, nx))       continue;
                if(visited[ny][nx][cNode.knight_cnt])     continue;
                if(map[ny][nx] == 1)    continue;

                visited[ny][nx][cNode.knight_cnt] = true;
                q.offer(new Node(ny, nx, cNode.cnt + 1, cNode.knight_cnt));
            }
        }

        return -1;
    }

    // // 요즘 백트래킹만 풀다 보니 깊게 생각안하고 백트래킹으로 짜버렸다.
    // static void dfs(int cy, int cx, int cnt)   {
    //     // 도착지점 도착 시 최소 이동 횟수 갱신
    //     if(cy == H - 1 && cx == W - 1)  {
    //         min_moving = Math.min(min_moving, cnt);
    //         return;
    //     }

    //     // 말처럼 이동할 수 있는 횟수가 남아있을 때만 나이트 이동
    //     if(K > 0)   {
    //         for(int d = 0; d < 8; d++)  {
    //             int ny = cy + knight_DY[d];
    //             int nx = cx + knight_DX[d];

    //             if(!isIn(ny, nx))       continue;
    //             if(map[ny][nx] == 1)    continue;
    //             if(visited[ny][nx])     continue;

    //             K--;
    //             visited[ny][nx] = true;

    //             dfs(ny, nx, cnt + 1);

    //             K++;
    //             visited[ny][nx] = false;
    //         }
    //     }

    //     // 인접 칸 이동
    //     for(int d = 0; d < 4; d++)  {
    //         int ny = cy + dy[d];
    //         int nx = cx + dx[d];

    //         if(!isIn(ny, nx))       continue;
    //         if(map[ny][nx] == 1)    continue;
    //         if(visited[ny][nx])     continue;

    //         visited[ny][nx] = true;

    //         dfs(ny, nx, cnt + 1);

    //         visited[ny][nx] = false;
    //     }

    // }
}