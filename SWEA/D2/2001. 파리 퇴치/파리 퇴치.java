import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 예전에 brute-force 로 푼 적이 있는 문제여서, 누적합으로 시간을 줄여봤습니다.
class Solution
{
    static int N, M;        // N 은 5 이상 15 이하이다. M은 2 이상 N 이하이다.
    static int map[][];     // 파리 저장 배열
    static int sumMap[][];  // 누적합 저장 배열

    static int kill(int y, int x)   {
        int sum = 0;

        for(int k = 0; k < M; k++)  {
            if(x == M - 1)  sum += sumMap[y + k][x];
            else            sum += sumMap[y + k][x] - sumMap[y + k][x - M];
        }

        return sum;
    }
	public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            sumMap = new int[N][N];

            for(int i = 0; i < N; i++)  {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)  {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(j == 0)  sumMap[i][j] = map[i][j];
                    // 가로로 쭉 더해주는 형식의 누적합 배열입니다.
                    // map[0]이 이런식으로 있다면 {1, 2, 3, 4, 5}
                    // sumMap[0]은 {1, 1 + 2, 1 + 2 + 3, 1 + 2 + 3 + 4, 1 + 2 + 3 + 4 + 5} 이렇게 저장됩니다.
                    else    sumMap[i][j] = sumMap[i][j - 1] + map[i][j];
                }
            }

            int ans = 0;
            
            for(int i = 0; i <= N - M; i++) 
                for(int j = M - 1; j < N; j++)  
                    ans = Math.max(ans, kill(i, j));

            System.out.println("#" + test_case + " " + ans);
		}
	}
}