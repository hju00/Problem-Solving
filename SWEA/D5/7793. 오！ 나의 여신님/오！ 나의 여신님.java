import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x)   { return y >= 0 && y < N && x >= 0 && x < M; }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            Queue<Point> suyeonQ = new ArrayDeque<>();
            Queue<Point> devilQ = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'S') {
                        suyeonQ.add(new Point(i, j));
                        visited[i][j] = true;
                    } else if (map[i][j] == '*') 
                        devilQ.add(new Point(i, j));
                }
            }

            int time = 0;
            boolean escaped = false;

            // 수연이가 더 이상 움직일 곳이 없을 때까지
            while (!suyeonQ.isEmpty()) {

                // 1. 악마 확산
                int devilSize = devilQ.size();
                for (int i = 0; i < devilSize; i++) {
                    Point c = devilQ.poll();

                    for (int d = 0; d < 4; d++) {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];

                        if (!isIn(ny, nx))      continue;
                        if (map[ny][nx] == 'D') continue;
                        if (map[ny][nx] == 'X') continue;
                        if (map[ny][nx] == '*') continue;
                        
                        map[ny][nx] = '*';
                        devilQ.add(new Point(ny, nx));
                    }
                }

                // 2. 수연 이동 
                int suyeonSize = suyeonQ.size();
                for (int i = 0; i < suyeonSize; i++) {
                    Point c = suyeonQ.poll();

                    // 여신 도달
                    if (map[c.y][c.x] == 'D') {
                        escaped = true;
                        break;
                    }

                    for (int d = 0; d < 4; d++) {
                        int ny = c.y + dy[d];
                        int nx = c.x + dx[d];
                        
                        if (!isIn(ny, nx))          continue;
                        if (visited[ny][nx])        continue;
                        if (map[ny][nx] == 'X')     continue;
                        if (map[ny][nx] == '*')     continue;

                        visited[ny][nx] = true;
                        suyeonQ.add(new Point(ny, nx));
                    }
                }
                
                if (escaped) break;
                time++; 
            }

            if (escaped) 
                System.out.println("#" + test_case + " " + time);
            else 
                System.out.println("#" + test_case + " GAME OVER");
            
        }
    }
}