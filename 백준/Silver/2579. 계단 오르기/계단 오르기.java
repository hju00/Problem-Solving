import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int score[] = new int[301];
        long dp[] = new long[301];

        for(int i = 1; i <= N; i++)
            score[i] = Integer.parseInt(br.readLine());

        dp[1] = score[1];
        dp[2] = dp[1] + score[2];
        dp[3] = Math.max(dp[1] + score[3], score[2] + score[3]);

        for(int i = 4; i <= N; i++)
            dp[i] = Math.max(dp[i - 3] + score[i - 1], dp[i - 2]) + score[i];
        
        System.out.println(dp[N]);
    }
}