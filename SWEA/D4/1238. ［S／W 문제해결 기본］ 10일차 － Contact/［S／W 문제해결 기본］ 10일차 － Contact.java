import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


class Solution
{
    static ArrayList<Integer> a[];
	public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()) / 2;
            int start = Integer.parseInt(st.nextToken());

            a = new ArrayList[101];
            for(int i = 1; i <= 100; i++)     a[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            while(N-- > 0)  {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                a[from].add(to);
            }

            System.out.println("#" + test_case + " " + bfs(start));
		}
	}

    static int bfs(int start) {
        int max_num = 0;

        Queue<Integer> q = new ArrayDeque<>();
        boolean visited[] = new boolean[101];

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            max_num = 0;
            int level = q.size();

            for(int l = 0; l < level; l++)  {
                int c = q.poll();
                max_num = Math.max(max_num, c);

                for(int i : a[c])   {
                    if(visited[i])  continue;

                    q.offer(i);
                    visited[i] = true;
                }
            }            
        }

        return max_num;
    }
}