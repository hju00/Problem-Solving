import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int R, C; // 1 <= R, C <= 20
    static char[][] board;
    static boolean[][] visited;
    static int max_cnt = 0;
    static boolean[] alpha;
    static int dy[] = { 1, 0, -1, 0 };
    static int dx[] = { 0, 1, 0, -1 };

    static boolean isIn(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++)
                board[i][j] = line.charAt(j);
        }

        alpha = new boolean[26];

        visited[0][0] = true;
        alpha[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(max_cnt);
    }

    static void dfs(int y, int x, int cnt) {

        max_cnt = Math.max(max_cnt, cnt);

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (!isIn(ny, nx))
                continue;

            int next = board[ny][nx] - 'A';

            if (visited[ny][nx])
                continue;
            if (alpha[next])
                continue;

            visited[ny][nx] = true;
            alpha[next] = true;

            dfs(ny, nx, cnt + 1);

            visited[ny][nx] = false;
            alpha[next] = false;
        }
    }
}