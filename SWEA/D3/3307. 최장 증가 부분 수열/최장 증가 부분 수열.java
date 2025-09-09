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

            int arr[] = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            ArrayList<Integer> list = new ArrayList<>();
            for(int num : arr) {
                int pos = Collections.binarySearch(list, num);
                if(pos < 0)
                    pos = -(pos + 1);
                if(pos == list.size())
                    list.add(num);
                else
                    list.set(pos, num);
            }

            System.out.println("#" + tc + " " + list.size());
        }
    }
    
}
