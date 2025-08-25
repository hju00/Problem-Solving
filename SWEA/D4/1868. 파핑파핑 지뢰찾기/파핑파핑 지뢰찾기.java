
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Solution {
    
    static int N, ans;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dx = {0, 1, 0, -1, 1, -1, 1, -1};

    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x) { return y >= 0 && y < N && x >= 0 && x < N; }
    public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ans = 0;

            for(int i = 0; i < N; i++){
                String str = br.readLine();
                for(int j = 0; j < N; j++)  {
                    if(str.charAt(j) == '.')    map[i][j] = -1;
                    else                        map[i][j] = -2;
                }
            }
            
            for(int i = 0; i < N; i++)  
                for(int j = 0; j < N; j++)  {
                    if(map[i][j] != -1) continue;
                    if(isZero(i, j))    {
                        bfs(i, j);
                        ans++;
                    }
                }
            
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++) 
                    if(map[i][j] == -1)
                        ans++;
            
            System.out.println("#" + test_case + " " + ans);
		}
	}

    static void bfs(int y, int x) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(y, x));
        map[y][x] = 0;
        
        while(!q.isEmpty()) {
            Point c = q.poll();

            for(int d = 0; d < 8; d++) {
                int ny = c.y + dy[d];
                int nx = c.x + dx[d];

                if(!isIn(ny, nx))       continue;
                if(map[ny][nx] != -1)   continue;
                
                if(isZero(ny, nx))
                    q.add(new Point(ny, nx));
                map[ny][nx] = 0;
            }
        }


    }

    static boolean isZero(int y, int x) {
        for(int d = 0; d < 8; d++)  {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx))       continue;
            if(map[ny][nx] == -2)   return false;
        }

        return true;
    }
}
