import java.io.*;
import java.util.*;

class Main {

    static class Wire implements Comparable<Wire> {
        int a;
        int b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Wire o) {
            return Integer.compare(this.a, o.a);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Wire[] wires = new Wire[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(a, b);
        }

        Arrays.sort(wires);

        int[] dp = new int[N];
        int maxLISLength = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 모든 전깃줄은 그 자체로 길이 1의 LIS가 될 수 있음
            
            // i 이전의 모든 전깃줄 j를 확인
            for (int j = 0; j < i; j++) 
                // j번째 B번호가 i번째 B번호보다 작으면 (교차하지 않으면)
                if (wires[j].b < wires[i].b) 
                    // i는 j 뒤에 연결될 수 있으므로, 길이를 1 늘릴 수 있음
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                
            // 전체 LIS 길이는 dp 배열의 최댓값
            maxLISLength = Math.max(maxLISLength, dp[i]);
        }
        
        // (전체 전깃줄 수) - (남길 수 있는 최대 전깃줄 수)
        System.out.println(N - maxLISLength);
    }
}
