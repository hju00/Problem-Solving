import java.io.*;
import java.util.*;

class Main {

    static int N, M, T, K;

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x <= N && y >= 0 && y <= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Point[] jewels = new Point[T];
        HashSet<Integer> dx = new HashSet<>();
        HashSet<Integer> dy = new HashSet<>();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            jewels[i] = new Point(x, y);
            dx.add(x);
            dy.add(y);
        }

        dx.add(0);
        dy.add(0);
        
        int maxCnt = 0, ansX = 0, ansY = 0;

        for (int tx : dx) {
            for (int ty : dy) {
                for (int cx : new int[]{tx, tx - K}) 
                    for (int cy : new int[]{ty, ty - K}) {
                        if (cx < 0 || cy < 0) continue;
                        if (!isIn(cx + K, cy + K)) continue;

                        int cnt = 0;
                        for (Point p : jewels) 
                            if (p.x >= cx && p.x <= cx + K && p.y >= cy && p.y <= cy + K) 
                                cnt++;

                        if (cnt > maxCnt) {
                            maxCnt = cnt;
                            ansX = cx;
                            ansY = cy + K; // 위쪽 y좌표
                        }
                    }
            }
        }

        System.out.println(ansX + " " + ansY);
        System.out.println(maxCnt);
    }
}
