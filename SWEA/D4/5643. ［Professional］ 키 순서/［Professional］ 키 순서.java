import java.io.*;
import java.util.*;

public class Solution {

    static final int INF = 900_000_000;
    static int dist[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++)
        {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            dist = new int[N + 1][N + 1];
            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++)
                    if(i != j)
                        dist[i][j] = INF;

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                dist[a][b] = 1;
            }

            for(int k = 1; k <= N; k++) 
                for(int i = 1; i <= N; i++)
                    for(int j = 1; j <= N; j++)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

            int ans = 0;
            for(int i = 1; i <= N; i++) {
                int cnt = 0;
                for(int j = 1; j <= N; j++) {
                    if(i == j)  continue;
                    if(dist[i][j] != INF || dist[j][i] != INF)  cnt++;
                }
                if(cnt == N - 1)    ans++;
            }

            System.out.println("#" + tc + " " + ans);
        }

    }
}
