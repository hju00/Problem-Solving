
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;


public class Solution {

    static int N, M;    // N, M(1 ≤ N ≤ 100, 0 ≤ M ≤ N(N-1)/2)
    static int[] parent;

    public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            for(int i = 1; i <= N; i++)
                parent[i] = i;

            while(M-- > 0)  {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            Set<Integer> s = new HashSet<>();
            for(int i = 1; i <= N; i++) 
                s.add((Integer) find(i));

            System.out.println("#" + test_case + " " + s.size());
		}
	}

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB)  return;
        parent[rootB] = rootA;
    }

    static int find(int a)   {
        if(parent[a] == a)  return a;
        return parent[a] = find(parent[a]);
    }
}
