import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int N;
    static long arr[];
    static ArrayList<Long> ans;
    static long min_diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            arr[i] = Long.parseLong(st.nextToken());

        ans = new ArrayList<>();

        int left = 0, right = N - 1;
        while(left < right) {
            long sum = Math.abs(arr[left] + arr[right]);
            if(min_diff > sum) {
                min_diff = sum;
                ans.clear();
                ans.add(arr[left]);
                ans.add(arr[right]);
            }
            
            if(Math.abs(arr[left + 1] + arr[right]) < sum)  left++;
            else if(Math.abs(arr[left] + arr[right - 1]) < sum)  right--;
            else left++;
                
        }

        for(long a : ans)
            System.out.print(a + " ");
    }
}