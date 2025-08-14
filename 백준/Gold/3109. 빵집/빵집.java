import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int R, C;    // (1 ≤ R ≤ 10,000, 5 ≤ C ≤ 500)
    static int map[][];
    static boolean visited[][];
    static int dy[] = {-1, 0, 1};
    static int max_pipe = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++)  {
            String str = br.readLine();
            for(int j = 0; j < C; j++)  {
                if(str.charAt(j) == '.')        map[i][j] = 0;
                else if(str.charAt(j) == 'x')   map[i][j] = 1;
            }
        }

        for(int i = 0; i < R; i++) {
            if(map[i][0] != 0)  continue;
            if(dfs(i, 0))   max_pipe++; 
        }
        
        System.out.println(max_pipe);
    }

    static boolean isIn(int y, int x)   { return y >= 0 && y < R && x >= 0 && x < C; }

    // 답을 보긴했는데 dfs 함수가 return 값을 주도록 짜는건 다시 풀어도 생각 못할 것 같네요
    static boolean dfs(int y, int x)   {

        if(x == C - 1)  return true;

        for(int d = 0; d < 3; d++)  {
            int ny = y + dy[d];
            int nx = x + 1;
            
            if(!isIn(ny, nx))                          continue;
            if(map[ny][nx] != 0 || visited[ny][nx])    continue;

            visited[ny][nx] = true;
            // 갈 수 있는 경로면 true 반환? 어려운 것 같습니다
            if(dfs(ny, nx))     return true;
        }

        // 3가지 방향 모두 안되면 false 반환
        return false;
    }
}