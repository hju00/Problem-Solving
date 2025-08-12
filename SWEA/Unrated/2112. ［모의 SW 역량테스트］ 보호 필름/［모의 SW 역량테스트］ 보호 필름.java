import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution
{
    static int D, W, K;     //  (3 ≤ D ≤ 13) (1 ≤ W ≤ 20)  (1 ≤ K ≤ D)
    static int[][] film;
    static int minDrug;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];

            for(int i = 0; i < D; i++)  {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++)  film[i][j] = Integer.parseInt(st.nextToken());
            }

            minDrug = Integer.MAX_VALUE;

            if(performanceTest())   minDrug = 0;
            else                    dfs(0, 0);

            System.out.println("#" + test_case + " " + minDrug);
		}
	}

    static boolean performanceTest() {
        for (int x = 0; x < W; x++) {
            boolean passColumn = false;
            int count = 1;

            // 세로 방향으로 탐색하며 K개 연속 셀이 있는지 확인
            for (int y = 1; y < D; y++) {
                if (film[y][x] == film[y - 1][x]) count++; 
                else  count = 1;
                
                if (count >= K) {
                    passColumn = true;
                    break;
                }
            }
            
            if (!passColumn) return false;
        }
        
        // 모든 열이 성능 검사를 통과했으면 전체 필름 합격
        return true;
    }

    static void dfs(int row, int count)   {
        if(count >= minDrug)    return;

        // 모든 행을 다 검사한 경우
        if(row == D)    {
            if(performanceTest())   minDrug = Math.min(minDrug, count);
            return;
        }

        // 현재 행에 약품을 투입하지 않는 경우
        dfs(row + 1, count);

        // 현재 행에 A(0) 약품을 투입하는 경우
        int[] originalRow = film[row].clone();
        for(int x = 0; x < W; x++)  film[row][x] = 0;
        dfs(row + 1, count + 1);
        film[row] = originalRow;

        // 현재 행에 B(1) 약품을 투입하는 경우
        originalRow = film[row].clone();
        for(int x = 0; x < W; x++)  film[row][x] = 1;
        dfs(row + 1, count + 1);
        film[row] = originalRow;
    }
}