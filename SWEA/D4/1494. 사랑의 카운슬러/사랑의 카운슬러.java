
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] x;
    static int[] y;

    static int[] choose;

    static long ans = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++)
        {
            N = Integer.parseInt(br.readLine());

            x = new int[N];
            y = new int[N];
            choose = new int[N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int xi = Integer.parseInt(st.nextToken());
                int yi = Integer.parseInt(st.nextToken());

                x[i] = xi;
                y[i] = yi;
            }

            ans = Long.MAX_VALUE;
            dfs(0, 0);

            System.out.println("#" + tc + " " + ans);
        }
    }

    static void dfs(int index, int cnt) {

        if (cnt > N / 2)    return;
        
        if (index - cnt > N / 2)    return;
        
        if(index == N) {
            long x_sum = 0;
            long y_sum = 0;
            for(int i = 0; i < N; i++) {
                x_sum += choose[i] * x[i];
                y_sum += choose[i] * y[i];
            }

            ans = Math.min(ans, x_sum * x_sum + y_sum * y_sum);
            return;
        }
        
        choose[index] = 1;
        dfs(index + 1, cnt);
        
        choose[index] = -1;
        dfs(index + 1, cnt + 1);
    }
}
