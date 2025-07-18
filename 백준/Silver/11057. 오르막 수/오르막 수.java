import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;   // (1 ≤ N ≤ 1,000)
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if(N == 1)  {
            System.out.println(10);
            return;
        }   else if(N == 2){
            System.out.println(55);
            return;
        }

        int[] dp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for(int i = 2; i < N; i++){
            for(int j = 1; j <= 9; j++) {
                dp[j] = (dp[j - 1] + dp[j]) % 10007;
            }
        }

        int res = 0;
        for(int d : dp) res = (res + d) % 10007;
        System.out.println(res);

    }
}