import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;    // N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다
    static List<Integer> list[];
    static ArrayList<Integer> ans;
    static int max_hacking = 0;

    static int bfs(int s)
    {
        Queue<Integer> q = new ArrayDeque<>();
        boolean visited[] = new boolean[N + 1];
        int cnt = 0;

        q.add(s);
        visited[s] = true;

        while(!q.isEmpty())
        {
            int cur = q.poll();
            cnt++;

            for(int next : list[cur])   {
                if(visited[next])    continue;
                visited[next] = true;
                q.add(next);
            }
        }

        return cnt;
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new ArrayList<>(N + 1);
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();

        while(M-- > 0)  {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[b].add(a);
        }

        for(int i = 1; i <= N; i++) {
            int cnt_hacking = bfs(i);
            if(cnt_hacking > max_hacking)   {
                ans.clear();
                ans.add(i);
                max_hacking = cnt_hacking;
            }
            else if(cnt_hacking == max_hacking)     ans.add(i);
        }

        for(int a : ans)    System.out.print(a + " ");
    }
}