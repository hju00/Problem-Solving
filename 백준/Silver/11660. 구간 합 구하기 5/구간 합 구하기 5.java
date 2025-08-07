import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;    // 표의 크기 N과 합을 구해야 하는 횟수 M (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000)
    static int sumArr[][];
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumArr = new int[N][N];

        for(int i = 0; i < N; i++)  {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)  {
                if(j == 0)  sumArr[i][j] = Integer.parseInt(st.nextToken());
                else        sumArr[i][j] = Integer.parseInt(st.nextToken()) + sumArr[i][j - 1];
            }
        }

        while(M-- > 0)
        {
            int ans = 0;
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;

            for(int i = y1; i <= y2; i++)
                if(x1 == 0)     ans += sumArr[i][x2];
                else            ans += sumArr[i][x2] - sumArr[i][x1 - 1];
            
            System.out.println(ans);
        }
    }
}