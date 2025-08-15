import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static int[][] A; // 추가되는 양분
    static int[][] ground; // 현재 땅의 양분
    static PriorityQueue<Integer>[][] trees; // 나무의 나이를 int로 저장
    static int[][] deadTrees;
    static int[] dy = { 1, 0, -1, 0, 1, 1, -1, -1 };
    static int[] dx = { 0, 1, 0, -1, 1, -1, 1, -1 };

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        ground = new int[N][N];
        // 우선순위 큐를 이중배열 사용
        trees = new PriorityQueue[N][N];
        deadTrees = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
                trees[i][j] = new PriorityQueue<>();
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            trees[y][x].add(age);
        }

        while (K-- > 0) {
            spring();
            summer();
            autumn();
            winter();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                cnt += trees[i][j].size();

        System.out.println(cnt);
    }

    static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Queue<Integer> aliveTrees = new ArrayDeque<>();

                while (!trees[i][j].isEmpty()) {
                    int age = trees[i][j].poll();
                    if (ground[i][j] >= age) {
                        ground[i][j] -= age;
                        aliveTrees.offer(age + 1);
                    } else
                        deadTrees[i][j] += age / 2;

                }

                while (!aliveTrees.isEmpty())
                    trees[i][j].offer(aliveTrees.poll());
            }
        }
    }

    static void summer() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                ground[i][j] += deadTrees[i][j];
                deadTrees[i][j] = 0;
            }
    }

    static void autumn() {

        Queue<Point> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int age : trees[i][j])
                    if (age % 5 == 0)
                        q.offer(new Point(i, j));

        while (!q.isEmpty()) {
            Point cPoint = q.poll();

            for (int d = 0; d < 8; d++) {
                int ny = cPoint.y + dy[d];
                int nx = cPoint.x + dx[d];

                if (!isIn(ny, nx))
                    continue;

                trees[ny][nx].offer(1);
            }
        }

    }

    static void winter() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                ground[i][j] += A[i][j];
    }
}