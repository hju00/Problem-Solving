import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        long sum[] = new long[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + temp;
        }

        int s = 1, e = 1;
        int ans = Integer.MAX_VALUE;
        while(s <= N && e <= N) {
            if(sum[e] - sum[s - 1] >= S) {
                ans = Math.min(ans, e - s + 1);
                s++;
            }
            else    {
                if(e == N)  s++;
                else        e++;
            }
        }

        if(ans == Integer.MAX_VALUE)    System.out.println(0);      
        else                            System.out.println(ans);
    }
}