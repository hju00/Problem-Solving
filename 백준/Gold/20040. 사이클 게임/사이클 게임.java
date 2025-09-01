import java.io.*;
import java.util.*;

public class Main {

	static int n, m; 	// 유적지의 개수 𝑛 (3 ≤ 𝑛  ≤ 500,000), 설치될 통로의 수 𝑚 (3 ≤ 𝑚  ≤ 1,000,000)
	static int parent[];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for(int i = 0; i < n; i++)
			parent[i] = i;
		
		int edge_cnt = 0;
		boolean is_cycle = false;
		
		while(m-- > 0)	{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(union(x, y))	
				edge_cnt++;
			else	{
				edge_cnt++;
				is_cycle = true;
				break;
			}
		}
		
		if(is_cycle)
			System.out.println(edge_cnt);
		else
			System.out.println("0");
	}

	static int find(int x)	{
		if(parent[x] == x)	return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y)	{
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX == rootY)	return false;
		
		parent[rootY] = rootX;
		return true;
	}
}
