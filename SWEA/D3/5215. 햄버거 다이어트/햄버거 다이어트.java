import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution
{
    static int N, L;        // 재료의 수 N, 제한 칼로리 L (1 ≤ N ≤ 20, 1 ≤ L ≤ 10^4)
    static int score[];     // 맛에 대한 점수
    static int kcal[];      // 칼로리
    static int max_score = 0;

    static void dfs(int idx, int s, int k)  {
        if(k > L)   return;
        if(idx == N)    {                           // 현재 칼로리가 L을 넘지않고 끝까지 도달했을 경우
            max_score = Math.max(max_score, s);     // 최대 점수 갱신
            return;
        }

        dfs(idx + 1, s + score[idx], k + kcal[idx]);    // 현재 idx의 햄버거를 선택한 경우
        dfs(idx + 1, s, k);                             // 현재 idx의 햄버거를 선택하지 않은 경우
    }
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            score = new int[N];
            kcal = new int[N];

            for(int i = 0; i < N; i++)  {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                kcal[i] = Integer.parseInt(st.nextToken());
            }

            max_score = 0;
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + max_score);
		}
	}
}