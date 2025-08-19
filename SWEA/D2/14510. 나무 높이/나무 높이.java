import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			int tree[] = new int[N];
			int max_height = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)	{
				tree[i] = Integer.parseInt(st.nextToken());
				max_height = Math.max(max_height, tree[i]);
			}
			
			int one = 0;
			int two = 0;
			
			for(int i = 0; i < N; i++) {
				int diff = max_height - tree[i];
				if(diff > 0)	{
					two += diff / 2;
					one += diff % 2;
				}
			}
			
			while(two > one + 1) {
				two -= 1;
				one += 2;
			}
			
			long result = 0;
			
			if(one > two)		result = (long) (one * 2 - 1);
			else if(two > one)	result = (long) two * 2;
			else				result = (long) (two + one);
			

			System.out.println("#" + test_case + " " + result);
		}

	}
}
