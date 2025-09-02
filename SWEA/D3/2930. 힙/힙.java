import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			
			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2, o1);
				}
			});
			
			StringBuilder sb = new StringBuilder();
			
			while(N-- > 0)	{
				st = new StringTokenizer(br.readLine());
				
				int command = Integer.parseInt(st.nextToken());
				
				switch (command) {
				case 1:
					int num = Integer.parseInt(st.nextToken());
					pq.add(num);
					break;
					
				case 2:
					if(pq.isEmpty())	{
						sb.append(" -1");
						break;
					}
					sb.append(" " + pq.poll());
					break;

				}
			}
			
			System.out.println("#" + test_case + sb);
		}
	}
}