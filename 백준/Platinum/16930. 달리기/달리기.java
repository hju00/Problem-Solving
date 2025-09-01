import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;	// (2 ≤ 𝑁, 𝑀 ≤ 1000, 1 ≤ 𝐾  ≤ 1000)
	static int map[][];
	static int[][] visited;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Point start, end;
	
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++)
				if(line.charAt(j) == '.')	map[i][j] = 0;
				else						map[i][j] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken()) - 1;
		int sx = Integer.parseInt(st.nextToken()) - 1;
		int ey = Integer.parseInt(st.nextToken()) - 1;
		int ex = Integer.parseInt(st.nextToken()) - 1;
		
		start = new Point(sy, sx);
		end = new Point(ey, ex);
		
		bfs();
		
		if(visited[end.y][end.x] == 0)
			System.out.println("-1");
		else
			System.out.println(visited[end.y][end.x]);
	}

	static boolean isIn(int y, int x)	{ return y >= 0 && y < N && x >= 0 && x < M; }
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		visited = new int[N][M];
		
		q.add(start);
		
		while(!q.isEmpty())	{
			int qsize = q.size();
			
			for(int i = 0; i < q.size(); i++) {
				Point c = q.poll();
				
				for(int d = 0; d < 4; d++) {
					for (int k = 1; k <= K; k++) {
						int ny = c.y + dy[d] * k;
						int nx = c.x + dx[d] * k;

						if (!isIn(ny, nx)) 		break;
						if(map[ny][nx] == 1)	break;
						if (visited[ny][nx] != 0) {
							if (visited[ny][nx] < visited[c.y][c.x] + 1) break;
							else continue;
						}

						visited[ny][nx] = visited[c.y][c.x] + 1;
						q.add(new Point(ny, nx));
					}
				}
				
			}
			
			// print();
		}
	}
	
	static void print()	{
		System.out.println("-----------------");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------");
	}
}
