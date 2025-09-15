import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static ArrayList<Integer> tree[];
	static boolean visited[];
	static int dp[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N + 1];
		dp = new int[N + 1][2];
		tree = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) 
			tree[i] = new ArrayList<>();
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int node) {
		visited[node] = true;
		dp[node][0] = 0;	// 일반인
		dp[node][1] = 1;	// 얼리어답터

		for(int next : tree[node]) {
			if(visited[next]) 	continue;

			dfs(next);

			// 현재 노드가 일반인인 경우 : 다음 노드는 반드시 얼리어답터
			dp[node][0] += dp[next][1];

			// 현재 노드가 얼리어답터인 경우 : 다음 노드는 일반인 / 얼리어답터 중 최소 선택
			dp[node][1] += Math.min(dp[next][0], dp[next][1]);
		}
	}

}
