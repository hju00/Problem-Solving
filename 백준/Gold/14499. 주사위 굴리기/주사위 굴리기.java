import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;    // (1 ≤ N, M ≤ 20)
    static int map[][];
    static int dice[][];

    public static boolean isIn(int y, int x) {  return y >= 0 && y < N && x >= 0 && x < M;  }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[4][3];
        // 주사위 윗면 dice[1][1], 아랫면 dice[3][1] 
        for(int i = 0; i < N; i++)  {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
        while(K-- > 0)
        {
            int command = Integer.parseInt(st.nextToken());
            
            if  (command == 1)    {
                int ny = y;
                int nx = x + 1;
                if(!isIn(ny, nx))   continue;

                int temp = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = dice[1][2];
                dice[1][2] = temp;

                if(map[ny][nx] == 0)    map[ny][nx] = dice[3][1];
                else    {
                    dice[3][1] = map[ny][nx];
                    map[ny][nx] = 0;
                }

                System.out.println(dice[1][1]);
                y = ny;
                x = nx;
            }
            else if (command == 2)  {
                int ny = y;
                int nx = x - 1;
                if(!isIn(ny, nx))   continue;

                int temp = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = dice[1][0];
                dice[1][0] = temp;

                if(map[ny][nx] == 0)    map[ny][nx] = dice[3][1];
                else    {
                    dice[3][1] = map[ny][nx];
                    map[ny][nx] = 0;
                }

                System.out.println(dice[1][1]);
                y = ny;
                x = nx;
            }
            else if (command == 3)  {
                int ny = y - 1;
                int nx = x;
                if(!isIn(ny, nx))   continue;

                int temp = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = dice[0][1];
                dice[0][1] = temp;

                if(map[ny][nx] == 0)    map[ny][nx] = dice[3][1];
                else    {
                    dice[3][1] = map[ny][nx];
                    map[ny][nx] = 0;
                }

                System.out.println(dice[1][1]);
                y = ny;
                x = nx;
            }
            else if (command == 4)  {
                int ny = y + 1;
                int nx = x;
                if(!isIn(ny, nx))   continue;

                int temp = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = temp;

                if(map[ny][nx] == 0)    map[ny][nx] = dice[3][1];
                else    {
                    dice[3][1] = map[ny][nx];
                    map[ny][nx] = 0;
                }

                System.out.println(dice[1][1]);
                y = ny;
                x = nx;
            }
        }
    }
}