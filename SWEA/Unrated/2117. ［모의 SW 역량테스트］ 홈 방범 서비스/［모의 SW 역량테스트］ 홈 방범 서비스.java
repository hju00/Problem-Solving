
import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int map[][];
    static ArrayList<Point> houses;
    static int ans;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++)
        {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new int[N][N];
			houses = new ArrayList<>();
            
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1)  houses.add(new Point(i, j));
                }
            }

            for(int k = 1; k < 2 * N; k++) {
                int cost = k * k + (k - 1) * (k - 1);
                if(cost > houses.size() * M)    break;

                for(int i = 0; i < N; i++) 
                    for(int j = 0; j < N; j++) {
                        Point c = new Point(i, j);
                        int cnt = 0;

                        for(Point h : houses) 
                            if(getDist(c, h) < k) 
                                cnt++;
                        
                        if(cost <= cnt * M)
                            ans = Math.max(ans, cnt);
                    }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    static int getDist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
