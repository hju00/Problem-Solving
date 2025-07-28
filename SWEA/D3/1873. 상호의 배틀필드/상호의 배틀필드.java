import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution
{
    static int H, W, N;    // (2 ≤ H, W ≤ 20), (0 < N ≤ 100)
    static char map[][];
    static char tank[] = {'^', 'v', '<', '>'};
    static int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static boolean isIn(int y, int x)    {   return y >= 0 && y < H && x >= 0 && x < W;  }
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            int cy = 0, cx = 0;

            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                    for(char c : tank)  
                        if(map[i][j] == c)  {
                            cy = i;
                            cx = j;
                        }
                }
            }

            N = Integer.parseInt(br.readLine());
            char[] commands = br.readLine().toCharArray();
            
            for(char c : commands)
            {
                switch (c) {
                    case 'U':
                        {
                            int ny = cy - 1;
                            int nx = cx;
                            if(isIn(ny, nx) && map[ny][nx] == '.')  {
                                map[ny][nx] = '^';
                                map[cy][cx] = '.';
                                cy = ny;
                                cx = nx;
                            }
                            else    {                            
                                map[cy][cx] = '^';
                            }       
                            break;
                        }
                    case 'D':
                        {
                            int ny = cy + 1;
                            int nx = cx;
                            if(isIn(ny, nx) && map[ny][nx] == '.')  {
                                map[ny][nx] = 'v';
                                map[cy][cx] = '.';
                                cy = ny;
                                cx = nx;
                            }
                            else    {
                                map[cy][cx] = 'v';
                            }       
                            break;
                        }
                    case 'L':
                        {
                            int ny = cy;
                            int nx = cx - 1;
                            if(isIn(ny, nx) && map[ny][nx] == '.')  {
                                map[ny][nx] = '<';
                                map[cy][cx] = '.';
                                cy = ny;
                                cx = nx;
                            }
                            else    {
                                map[cy][cx] = '<';
                            }       
                            break;
                        }
                    case 'R':
                        {
                            int ny = cy;
                            int nx = cx + 1;
                            if(isIn(ny, nx) && map[ny][nx] == '.')  {
                                map[ny][nx] = '>';
                                map[cy][cx] = '.';
                                cy = ny;
                                cx = nx;
                            }
                            else    {
                                map[cy][cx] = '>';
                            }       
                            break;
                        }
                    case 'S':
                        int d = 0;
                        for(;d < 4; d++)    if(map[cy][cx] == tank[d])  break;

                        int ny = cy + deltas[d][0];
                        int nx = cx + deltas[d][1];
                        while(isIn(ny, nx))
                        {
                            if (map[ny][nx] == '*') {
                                map[ny][nx] = '.';
                                break;
                            }
                            else if (map[ny][nx] == '#')    break;
                            
                            ny += deltas[d][0];
                            nx += deltas[d][1];
                        }
                                
                        break;
                }

            }

            System.out.print("#" + test_case + " ");
            for(int i = 0; i < H; i++)  {
                for(int j = 0; j < W; j++)
                    System.out.print(map[i][j]);
                System.out.println();
            }
		}
	}
}