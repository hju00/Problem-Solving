import java.io.*;
import java.util.*;

class Main {

    // N, M (3 ≤ N, M ≤ 10)
    static int N, M;
    static char[][] map;

    static int dy[] = {-1, 0, 1, 0};    // 상 우 하 좌
    static int dx[] = {0, 1, 0, -1};

    static class State {
        int ry, rx, by, bx, cnt;

        public State(int ry, int rx, int by, int bx, int cnt) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int ry = 0, rx = 0, by = 0, bx = 0;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'R') {
                    ry = i;
                    rx = j;
                }
                else if(map[i][j] == 'B') {
                    by = i;
                    bx = j;
                }
            }
        }

        bfs(ry, rx, by, bx);
    }

    static boolean isIn (int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static void bfs(int ry, int rx, int by, int bx) {
        Queue<State> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        q.add(new State(ry, rx, by, bx, 0));
        visited[ry][rx][by][bx] = true;

        while(!q.isEmpty()) {
            State c = q.poll();

            if(c.cnt == 10)     continue;

            for(int d = 0; d < 4; d++) {

                int nry = c.ry;
                int nrx = c.rx;
                int nby = c.by;
                int nbx = c.bx;

                int redMoving = 0;
                int blueMoving = 0;
                boolean endRed = false;
                boolean endBlue = false;

                while(map[nry + dy[d]][nrx + dx[d]] != '#') {
                    nry += dy[d];
                    nrx += dx[d];
                    if(map[nry][nrx] == 'O') {
                        endRed = true;
                        break;
                    }
                    redMoving++;
                }

                while(map[nby + dy[d]][nbx + dx[d]] != '#') {
                    nby += dy[d];
                    nbx += dx[d];
                    if(map[nby][nbx] == 'O') {
                        endBlue = true;
                        break;
                    }
                    blueMoving++;
                }

                if(endBlue)     continue;
                if(endRed) {
                    System.out.println(c.cnt + 1);
                    return;
                }

                if(nry == nby && nrx == nbx) {
                    if(redMoving > blueMoving) {
                        nry -= dy[d];
                        nrx -= dx[d];
                    }
                    else {
                        nby -= dy[d];
                        nbx -= dx[d];
                    }
                }
                
                if(visited[nry][nrx][nby][nbx])     continue;

                visited[nry][nrx][nby][nbx] = true;
                q.add(new State(nry, nrx, nby, nbx, c.cnt + 1));
            }
        }

        System.out.println(-1);
    }
}