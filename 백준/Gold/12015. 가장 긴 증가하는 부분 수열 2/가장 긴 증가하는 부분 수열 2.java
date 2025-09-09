import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int num[] = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> lis = new ArrayList<>();

        for(int i : num) {
            int pos = Collections.binarySearch(lis, i);

            if(pos < 0)     pos = -(pos + 1);

            if(pos == lis.size())   lis.add(i);
            else                    lis.set(pos, i);
        }

        System.out.println(lis.size());
    }
}