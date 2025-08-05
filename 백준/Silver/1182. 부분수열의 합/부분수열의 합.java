import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, S, cnt = 0;
    static int nums[];

    static void bt(int idx, int sum)
    {
        if(idx >= N)    return;
        if(sum + nums[idx] == S)    cnt++;

        bt(idx + 1, sum + nums[idx]);
        bt(idx + 1, sum);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)  nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);

        bt(0, 0);
        System.out.println(cnt);
    }
}