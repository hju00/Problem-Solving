import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, max_safety_zone = 0;    // 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
    static int map[][];
    static int dy[] = {1, -1, 0, 0};
    static int dx[] = {0, 0, 1, -1};
    static ArrayList<Node> empty_walls;     // 벽을 설치할 수 있는 빈 공간 목록
    static LinkedList<Node> selected;       // selected 에 있는 좌표에 벽을 설치
    
    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x)   { return y >= 0 && y < N && x >= 0 && x < M; }

    static void bfs_BackTracking(int idx, int count)
    {
        // 3개의 좌표가 준비되었을 때 bfs 실행
        if(count == 3) 
        {
            // 원본 보존하기 위한 맵 복사
            int map_copy[][] = new int[N][M];
            for(int i = 0; i < N; i++)
                for(int j = 0; j < M; j++)
                    map_copy[i][j] = map[i][j];

            // selected 의 좌표에 벽 설치
            for(Node n : selected)   map_copy[n.y][n.x] = 1;

            Queue<Node> q = new LinkedList<>();

            // 큐에 바이러스가 있는 좌표 삽입
            for(int i = 0; i < N; i++)
                for(int j = 0; j < M; j++)
                    if(map_copy[i][j] == 2) q.add(new Node(i, j));

            // 바이러스 전파
            while(!q.isEmpty())
            {
                Node curN = q.poll();

                for(int d = 0; d < 4; d++)  {
                    int ny = curN.y + dy[d];
                    int nx = curN.x + dx[d];

                    if(!isIn(ny, nx))           continue;
                    if(map_copy[ny][nx] != 0)   continue;

                    map_copy[ny][nx] = 2;
                    q.add(new Node(ny, nx));
                }
            }

            // 맵의 0 개수를 세어 max_safety_zone 에 반영
            int cnt = 0;
            for(int i = 0; i < N; i++)
                for(int j = 0; j < M; j++)
                    if(map_copy[i][j] == 0) cnt++;
            
            max_safety_zone = Math.max(max_safety_zone, cnt);
            return;
        }

        // idx 부터 시작해 3개의 좌표 조합을 만들 수 있음
        for(int i = idx; i < empty_walls.size(); i++) {
            selected.add(empty_walls.get(i));
            bfs_BackTracking(i + 1, count + 1);
            selected.pollLast();
        }
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        empty_walls = new ArrayList<>();
        selected = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)  {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)  empty_walls.add(new Node(i, j));
            }
        }
                
        bfs_BackTracking(0, 0);
        System.out.println(max_safety_zone);

    }
}