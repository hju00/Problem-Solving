import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution
{
	static int N;	// 7 ≤ N ≤ 12
	static int[][] map;
	static int min_length, max_connected;
	static ArrayList<Point> maxinos;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Point	{
		int y, x;
		boolean connected;
		public Point(int y, int x, boolean connected) {
			this.y = y;
			this.x = x;
			this.connected = connected;
        }
	}

	static boolean isIn(int y, int x) 	{ return y >= 0 && y < N && x >= 0 && x < N; }

	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			maxinos = new ArrayList<>();

			for(int i = 0; i < N; i++)	{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)	{
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1)	{
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1)	continue;
						else	maxinos.add(new Point(i, j, false));
					}
				}
			}

			min_length = Integer.MAX_VALUE;
			max_connected = 0;

			dfs(0);

			System.out.println("#" + test_case + " " + min_length);
		}
	}

	static void dfs(int index)	{

		if(index == maxinos.size())	{
			int connected_cnt = 0;
			for(Point p : maxinos)
				if(p.connected)	connected_cnt++;
			
			if(connected_cnt > max_connected)	{
				max_connected = connected_cnt;
				min_length = wire_length();
			}
			else if(connected_cnt == max_connected)	
				min_length = Math.min(min_length, wire_length());

			return;
		}

		Point cPoint = maxinos.get(index);

		dfs(index + 1);


		for(int d = 0; d < 4; d++)	{

			int ny = cPoint.y;
			int nx = cPoint.x;

			if(can_place_wire(ny, nx, d))	{
				place_wire(ny, nx, d, 2);
				maxinos.get(index).connected = true;
				dfs(index + 1);
				place_wire(ny, nx, d, 0);
				maxinos.get(index).connected = false;
			}
		}
	}

	static boolean can_place_wire(int y, int x, int dir)	{
		y += dy[dir];
		x += dx[dir];
		while(isIn(y, x))	{
			if(map[y][x] != 0)	return false;
			y += dy[dir];
			x += dx[dir];
		}
		return true;
	}

	static void place_wire(int y, int x, int dir, int value)	{
		int ny = y + dy[dir];
		int nx = x + dx[dir];

		while(isIn(ny, nx))	{
			map[ny][nx] = value;
			ny += dy[dir];
			nx += dx[dir];
		}
	}
	static int wire_length()	{
		int length = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(map[i][j] == 2)	length++;
		return length;
	}
}