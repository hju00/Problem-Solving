import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int y, x;
    static int map[][];

    static boolean isIn(int y, int x)   { return y >= 0 && y < 100 && x >= 0 && x < 100; }
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int T = Integer.parseInt(br.readLine());
            map = new int[100][100];

            for(int i = 0; i < 100; i++)    {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 100; j++)    {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 2)  {    // 출발점
                        y = i;
                        x = j;
                    }
                }
            }

            while(y != 0)
            {
                if(isIn(y, x - 1) && map[y][x - 1] == 1) {
                    while(isIn(y, x - 1) && map[y][x - 1] == 1)
                        x--;
                    y--;
                }
                else if(isIn(y, x + 1) && map[y][x + 1] == 1)   {
                    while(isIn(y, x + 1) && map[y][x + 1] == 1)
                        x++;
                    y--;
                }
                else y--;
            }

            System.out.println("#" + T + " " + x);
            
		}
	}
}