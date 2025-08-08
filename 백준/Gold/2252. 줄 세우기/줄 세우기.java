import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;    // N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)
    static List<Integer> list[];
    static int degeree[];

    static ArrayList<Integer> topology_sort()   {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean visited[] = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int i = 1; i <= N; i++) {
            if(degeree[i] == 0) {
                q.add(i);
                visited[i] = true;
                break;
            }
        }

        while(!q.isEmpty()) {
            int c = q.poll();
            ans.add(c);

            for(int nx : list[c])   {
                if(visited[nx]) continue;
                degeree[nx]--;
            }

            for(int i = 1; i <= N; i++) 
                if(degeree[i] == 0 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
        }

        return ans;
    }   
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        degeree = new int[N + 1];
        list = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) list[i] = new ArrayList<>();

        while(M-- > 0)  {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            degeree[b]++;
        }

        ArrayList<Integer> arr = topology_sort();
        for(int a : arr)    System.out.print(a + " ");

    }
}