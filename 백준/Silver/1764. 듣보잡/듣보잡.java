import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashSet<String> hs = new HashSet<>();
        List<String> ansList = new ArrayList<>();

        String s;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = 0;

        for(int i = 0; i < N; i++) {
            s = br.readLine();
            hs.add(s);
        }

        for(int i = 0; i < M; i++) {
            s = br.readLine();
            if(hs.contains(s)) {
                ans++;
                ansList.add(s);
            }
        }

        Collections.sort(ansList);
        
        System.out.println(ans);
        for(String a : ansList)
            System.out.println(a);
    }
}