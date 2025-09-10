import java.io.*;
import java.util.*;

public class Solution {

    static int INF;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++)
        {   
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            INF = N + 1;
            int dist[][] = new int[N][N];

            for(int i = 0; i < N; i++) 
                for(int j = 0; j < N; j++) {
                    dist[i][j] = Integer.parseInt(st.nextToken());
                    if(dist[i][j] == 0 && i != j)     dist[i][j] = INF;
                }
                
            for(int k = 0; k < N; k++)
                for(int i = 0; i < N; i++)
                    for(int j = 0; j < N; j++)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            
            int ans = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++) {
                int sum = 0;
                for(int j = 0; j < N; j++) 
                    sum += dist[i][j];
                ans = Math.min(ans, sum);
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
    
}
