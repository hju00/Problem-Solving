import java.io.*;
import java.util.*;

public class Solution {

    static int N, K;
    static int v[];
    static int c[];

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) 
        {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            v = new int[N];
            c = new int[N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }

            int dp[] = new int[K + 1];
            for(int i = 0; i < N; i++)
                for(int j = K; j >= v[i]; j--)
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + c[i]);
            
            int ret = Arrays.stream(dp).max().getAsInt();
            System.out.println("#" + tc + " " + ret);
        }
    }
}
