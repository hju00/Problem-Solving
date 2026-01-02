import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int window = 0;
        for(int i = 0; i < K; i++)
            window += arr[i];
        
        int ans = window;
        for(int i = 1; i < N - K + 1; i++) {
            window += arr[i + K - 1] - arr[i - 1];
            ans = Math.max(ans, window);
        }

        System.out.println(ans);
    }
}