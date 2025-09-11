import java.io.*;
import java.util.*;

class Main {
    
    static int N;   // (0 ≤ N ≤ 500,000)
    static HashSet<Integer> bt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        bt = new HashSet<>();

        int M = Integer.parseInt(br.readLine());
        if(M > 0) {
            st = new StringTokenizer(br.readLine());
            while(M-- > 0) {
                int temp = Integer.parseInt(st.nextToken());
                bt.add(temp);
            }
        }

        int ans = Math.abs(N - 100);
        
        for(int i = 0; i <= 1000000; i++) {
            if(canClick(i)) {
                int cnt = String.valueOf(i).length() + Math.abs(N - i);
                ans = Math.min(ans, cnt);
            }
        }

        System.out.println(ans);

    }

    static boolean canClick(int n) {
        String temp = String.valueOf(n);
        for(int i = 0; i < temp.length(); i++) 
            if(bt.contains(temp.charAt(i) - '0'))
                return false;
        return true;
    }
}