import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int N, B;    // N, B(1 ≤ N ≤ 20, 1 ≤ B ≤ S)
    static int[] people;
    static int min_height;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            people = new int[N];
            min_height = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++)  people[i] = Integer.parseInt(st.nextToken());

            dfs(0, 0);

            System.out.println("#" + test_case + " " + (min_height - B));
		}
	}

    static void dfs(int index, int height)  {
        
        if(index == N) {
            if(height >= B)
                min_height = Math.min(min_height, height);
            return;
        }
        
        dfs(index + 1, height + people[index]);
        dfs(index + 1, height);
    }
}