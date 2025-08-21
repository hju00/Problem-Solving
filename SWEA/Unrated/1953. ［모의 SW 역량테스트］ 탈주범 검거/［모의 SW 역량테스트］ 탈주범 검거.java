import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
static int N, M;    //  (5 ≤ N, M ≤ 50)
static int L;   // 탈출 후 소요된 시간 L은 1 이상 20 이하 (1 ≤ L ≤ 20)
static int map[][];
static boolean visited[][];
static int dy[] = {-1, 0, 1, 0};    //  상 우 하 좌
static int dx[] = {0, 1, 0, -1};

static class Node   {
    int y, x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

static boolean isIn(int y, int x)   { return y >= 0 && y < N && x >= 0 && x < M; }
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
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++)  {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
		if(L == 1)  {
            System.out.println("#" + test_case + " " + 1);
            continue;
        }
        bfs(R, C);

        int result = 0;
        for(int i = 0; i < N; i++)  
            for(int j = 0; j < M; j++)  
                if(visited[i][j])   
                    result++;
                    
        
        System.out.println("#" + test_case + " " + result);
    }
}

static void print() {
    for(int i = 0; i < N; i++)  {
        System.out.println();
        for(int j = 0; j < M; j++)  {
            if(visited[i][j])   System.out.print("O ");
            else                System.out.print("X ");
        }
    }
}

static void bfs(int y, int x)   {
    Queue<Node> q = new ArrayDeque<>();
    q.offer(new Node(y, x));
    visited[y][x] = true;
    int cycle = 1;  // 큐에 시작점을 넣었으므로 1부터 시작

    while(!q.isEmpty()) {
        if(cycle == L)  return;
        int qs = q.size();

        for(int i = 0; i < qs; i++)  {
            Node c = q.poll();
        
            switch (map[c.y][c.x]) {
                case 1:
                    for(int d = 0; d < 4; d++)  {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d == 0)  {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }
                        else if(d == 1)  {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 5)    continue;
                        }
                        else if(d == 2) {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 5)    continue;
                            if(map[ny][nx] == 6)    continue;
                        }
                        else if(d == 3) {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 6)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;

                case 2:
                    for(int d = 0; d < 4; d += 2)   {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d == 0)  {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }
                        else if(d == 2) {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 5)    continue;
                            if(map[ny][nx] == 6)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;

                case 3:
                    for(int d = 1; d < 4; d += 2)   {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d == 1)  {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 5)    continue;
                        }
                        else if(d == 3) {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 6)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;

                case 4:
                    for(int d = 0; d < 2; d++)   {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d == 0)  {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }
                        else if(d == 1) {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 5)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;

                case 5:
                    for(int d = 1; d < 3; d++)   {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d == 1)  {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 5)    continue;
                        }
                        else if(d == 2) {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 5)    continue;
                            if(map[ny][nx] == 6)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;

                case 6:
                    for(int d = 2; d < 4; d++)   {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d == 2)  {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 5)    continue;
                            if(map[ny][nx] == 6)    continue;
                        }
                        else if(d == 3) {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 6)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;

                case 7:
                    for(int d = 3; d < 5; d++)   {
                        int ny = c.y + dy[d % 4];
                        int nx = c.x + dx[d % 4];

                        if(!isIn(ny, nx))   continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx] == 0)    continue;
                        if(d % 4== 0)  {
                            if(map[ny][nx] == 3)    continue;
                            if(map[ny][nx] == 4)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }
                        else if(d == 3) {
                            if(map[ny][nx] == 2)    continue;
                            if(map[ny][nx] == 6)    continue;
                            if(map[ny][nx] == 7)    continue;
                        }

                        q.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }
                    break;
            }

        }
        
        cycle++;
    }
}
}