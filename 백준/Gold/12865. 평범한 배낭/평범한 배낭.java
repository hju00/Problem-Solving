import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, K;    // 물품의 수 N(1 ≤ N ≤ 100), 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)
    static int[] weight;
    static int[] value;
    static int max_value = 0;
    
    // dfs 풀이 (시간초과)
    // static void dfs(int idx, int wei, int val)   {
    //     if(wei > K) return;
    //     if(idx == N)    {
    //         max_value = Math.max(max_value, val);
    //         return;
    //     }

    //     dfs(idx + 1, wei + weight[idx], val + value[idx]);
    //     dfs(idx + 1, wei, val);
    // }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weight = new int[N];
        value = new int[N];

        for(int i = 0; i < N; i++)  {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        // dfs(0, 0, 0);
        
        // dp 풀이 - O(N x K)
        int[] dp = new int[K + 1];
        for(int i = 0; i < N; i++)  
            for(int j = K; j >= weight[i]; j--) 
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            
        max_value = Arrays.stream(dp).max().getAsInt();

        System.out.println(max_value);
    }
}