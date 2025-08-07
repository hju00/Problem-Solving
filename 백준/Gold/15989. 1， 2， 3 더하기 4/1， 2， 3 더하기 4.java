import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int n;   //  n은 양수이며 10,000보다 작거나 같다.
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
            
        int dp[] = new int[10001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i = 4; i <= 10000; i++) {
            if(i % 3 == 0)  dp[i] = dp[i - 1] + dp[i - 2] - dp[i - 3] + 1;
            else            dp[i] = dp[i - 1] + dp[i - 2] - dp[i - 3];
        }

        while(T-- > 0)
        {
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}