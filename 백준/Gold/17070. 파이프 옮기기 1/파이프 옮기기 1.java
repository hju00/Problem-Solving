import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int home[][] = new int[N + 1][N + 1];
        int dp[][][] = new int[17][17][3];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++)
                home[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[1][2][0] = 1;

        for(int y = 1; y <= N; y++)
            for(int x = 3; x <= N; x++) {
                if(home[y][x] == 1) continue;

                dp[y][x][0] = dp[y][x - 1][0] + dp[y][x - 1][2];

                dp[y][x][1] = dp[y - 1][x][1] + dp[y - 1][x][2];

                if(home[y - 1][x] != 1 && home[y][x - 1] != 1)
                    dp[y][x][2] = dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
            }

        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }
}