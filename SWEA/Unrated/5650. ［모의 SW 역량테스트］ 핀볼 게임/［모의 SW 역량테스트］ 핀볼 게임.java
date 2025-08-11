import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[][] map;
    static ArrayList<int[]>[] wormholes;
    static int maxScore;

    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 블록 종류에 따른 방향 전환 정보
    // changeDir[블록번호][들어온방향] = 나갈방향
    static int[][] changeDir = {
            {}, // 0번 블록 없음
            // 상(0)->하(1), 하(1)->우(3), 좌(2)->상(0), 우(3)->좌(2)
            {1, 3, 0, 2}, // 1번 블록
            {3, 0, 1, 2}, // 2번 블록
            {2, 0, 3, 1}, // 3번 블록
            {1, 2, 3, 0}, // 4번 블록
            {1, 0, 3, 2}  // 5번 블록
    };


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            wormholes = new ArrayList[11]; // 6번부터 10번 웜홀
            for (int i = 6; i <= 10; i++) {
                wormholes[i] = new ArrayList<>();
            }
            maxScore = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        wormholes[map[i][j]].add(new int[]{i, j});
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            playGame(i, j, d);
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + maxScore);
        }
    }

    private static void playGame(int startX, int startY, int dir) {
        int score = 0;
        int x = startX;
        int y = startY;
        int d = dir;

        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 1. 벽에 부딪히는 경우
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                d = changeDirection(d); // 반대 방향
                score++;
                x = nx; // 위치는 그대로 유지하고 다음 루프에서 방향만 바뀐 채로 진행
                y = ny;
                // 방향만 바꾸고 현재 위치에서 다시 출발
                x = x + dx[d];
                y = y + dy[d];
            } else { // 벽이 아닌 경우
                x = nx;
                y = ny;
            }


            // 2. 시작 위치로 돌아오거나 블랙홀을 만난 경우
            if ((x == startX && y == startY) || map[x][y] == -1) {
                maxScore = Math.max(maxScore, score);
                return;
            }

            int block = map[x][y];

            // 3. 블록을 만난 경우
            if (block >= 1 && block <= 5) {
                d = getNextDirectionFromBlock(block, d);
                score++;
            }
            // 4. 웜홀을 만난 경우
            else if (block >= 6 && block <= 10) {
                ArrayList<int[]> holePair = wormholes[block];
                int[] hole1 = holePair.get(0);
                int[] hole2 = holePair.get(1);

                if (x == hole1[0] && y == hole1[1]) {
                    x = hole2[0];
                    y = hole2[1];
                } else {
                    x = hole1[0];
                    y = hole1[1];
                }
            }
        }
    }

    // 벽 또는 5번 블록을 만났을 때 방향 전환
    private static int changeDirection(int d) {
        if (d == 0) return 1; // 상 -> 하
        if (d == 1) return 0; // 하 -> 상
        if (d == 2) return 3; // 좌 -> 우
        if (d == 3) return 2; // 우 -> 좌
        return -1;
    }

    // 1~4번 블록을 만났을 때 방향 전환
    private static int getNextDirectionFromBlock(int block, int d) {
        // 상(0), 하(1), 좌(2), 우(3)
        switch (block) {
            case 1:
                if (d == 1) return 3; // 하 -> 우
                if (d == 2) return 0; // 좌 -> 상
                return changeDirection(d); // 나머지는 반대 방향
            case 2:
                if (d == 0) return 3; // 상 -> 우
                if (d == 2) return 1; // 좌 -> 하
                return changeDirection(d);
            case 3:
                if (d == 0) return 2; // 상 -> 좌
                if (d == 3) return 1; // 우 -> 하
                return changeDirection(d);
            case 4:
                if (d == 1) return 2; // 하 -> 좌
                if (d == 3) return 0; // 우 -> 상
                return changeDirection(d);
            case 5:
                return changeDirection(d);
        }
        return d;
    }
}