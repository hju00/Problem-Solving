import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] lq;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        lq = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)  lq[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(lq);
        
        int l = 0, r = N - 1;                   // 좌 우 맨끝에서 시작
        int minSum = Integer.MAX_VALUE;
        int ansL = 0, ansR = 0;                 // 두 용액의 특성값 저장

        while(l < r)                            // l 이 r 보다 클때까지 진행
        {
            int sum = lq[l] + lq[r];
            int absSum = Math.abs(sum);         // 두 용액 합의 절댓값

            if(absSum < minSum)    {            
                minSum = absSum;
                ansL = lq[l];
                ansR = lq[r];
            }

            if(sum < 0) l++;                    // sum 이 음수인 경우는 왼쪽의 특성값의 절댓값이 더 크다는 의미이므로 왼쪽의 index를 오른쪽으로 한 칸 이동
            else        r--;                    // sum 이 양수인 경우 오른쪽 index를 왼쪽으로 한 칸 이동
        }

        System.out.println(ansL + " " + ansR);
    }
}