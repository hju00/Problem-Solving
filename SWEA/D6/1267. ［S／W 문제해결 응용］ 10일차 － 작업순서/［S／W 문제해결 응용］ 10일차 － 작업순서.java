import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
    static int V, E;    // 정점의 개수 V(3 ≤ V ≤ 1000)와 간선의 개수 E(2 ≤ E ≤ 3000)
    static List<Integer> list[];
    static int degeree[];
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V + 1];
            for(int i = 1; i <= V; i++) list[i] = new ArrayList<>();
            degeree = new int[V + 1];

            st = new StringTokenizer(br.readLine());
            while(E-- > 0)  {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                list[from].add(to);
                degeree[to]++;
            }

            System.out.print("#" + test_case + " ");
            topology_sort();
            System.out.println();
		}
	}

    static void topology_sort()  {
        Queue<Integer> q = new ArrayDeque<>();
        for(int v = 1; v <= V; v++) 
            if(degeree[v] == 0) 
                q.add(v);
            
        
        while(!q.isEmpty()) {
            int c = q.poll();
            System.out.print(c + " ");

            for(int next : list[c])    {
                degeree[next]--;
                if(degeree[next] == 0)
                    q.offer(next);
            }

        }
        
    }
}