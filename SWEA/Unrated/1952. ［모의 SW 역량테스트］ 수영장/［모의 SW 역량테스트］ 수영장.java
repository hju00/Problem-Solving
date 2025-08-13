import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int price[];
    static int plan[];
    static int dp[];
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            price = new int[4];
            plan = new int[13];
            dp = new int[13];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++)  price[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < 13; i++)    plan[i] = Integer.parseInt(st.nextToken());

            for(int i = 0; i < 13; i++) dp[i] = Integer.MAX_VALUE;
            dp[0] = 0;

            for(int i = 1; i < 13; i++) {
                // 일일권으로 i 월 처리
                dp[i] = Math.min(dp[i], dp[i - 1] + plan[i] * price[0]);

                // 한달권으로 i 월 처리
                dp[i] = Math.min(dp[i], dp[i - 1] + price[1]);

                // 세달권으로 처리
                if(i >= 3)  dp[i] = Math.min(dp[i], dp[i - 3] + price[2]);
                else        dp[i] = Math.min(dp[i], price[2]);
            }

            // 마지막으로 1년권과 비교
            int answer = Math.min(dp[12], price[3]);

            System.out.println("#" + test_case + " " + answer);

		}
	}
}