import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        
        int left = 0, right = 0, sum = 0, cnt = 0;
        while(true)    
        {
            // 합이 M 보다 크거나 같을 경우 left를 오른쪽으로 이동
            if (sum >= M)   sum -= A[left++];
            // right가 N(배열의 끝)일 경우 중단
            else if (right == N)    break;
            // 합이 M 보다 작을 경우 right를 오른쪽으로 이동
            else    sum += A[right++];

            // 합이 M 일 경우 cnt 1 증가 
            if(sum == M)    cnt++;
        }

        System.out.println(cnt);
    }
}