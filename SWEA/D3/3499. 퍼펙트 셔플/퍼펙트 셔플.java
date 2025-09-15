import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++)
        {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Queue<String> s1 = new ArrayDeque<>();
            Queue<String> s2 = new ArrayDeque<>();

            for(int i = 0; i < N / 2; i++)
                s1.add(st.nextToken());
            
            if(N % 2 == 1)
                s1.add(st.nextToken());
            
            for(int i = 0; i < N / 2; i++)
                s2.add(st.nextToken());
            
            System.out.print("#" + tc + " ");
            while(!s1.isEmpty()) {
                System.out.print(s1.poll() + " ");
                if(!s2.isEmpty())
                    System.out.print(s2.poll() + " ");
            }
            System.out.println();
        }
    }
}