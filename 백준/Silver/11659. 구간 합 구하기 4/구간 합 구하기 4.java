import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int arr[];
    static int sumArr[];


    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        sumArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)  {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i == 0)  sumArr[i] = arr[i];
            else        sumArr[i] = arr[i] + sumArr[i - 1];
        }

        while(M-- > 0)  {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            if(a != 0)  System.out.println(sumArr[b] - sumArr[a - 1]);
            else        System.out.println(sumArr[b]);
        }
    }
}