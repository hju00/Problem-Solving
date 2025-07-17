import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, R;
    static int[][] arr;

    static void rotate()    {
        for(int i = 0; i < Math.min(N, M) / 2; i++)  {
            int s = arr[i + 1][i + 1];
            for(int j = i + 1; j < M - i; j++) arr[i + 1][j] = arr[i + 1][j + 1];
            for(int j = i + 1; j < N - i; j++) arr[j][M - i] = arr[j + 1][M - i];
            for(int j = M - i; j > i + 1; j--) arr[N - i][j] = arr[N - i][j - 1];
            for(int j = N - i; j > i + 1; j--) arr[j][i + 1] = arr[j - 1][i + 1];
            arr[i + 2][i + 1] = s;
        }
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 2 ≤ N, M ≤ 300, 1 ≤ R ≤ 1,000
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++)  arr[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < R; i++)  rotate();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}