import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N, M;    // (1 ≤ N, M ≤ 8)
    static int room[][];
    static int min_blindSpot = Integer.MAX_VALUE;
    static List<Point> cctvList;
    static int dy[] = {-1, 0, 1, 0};    // 	↑	→   ↓   ←
    static int dx[] = {0, 1, 0, -1};

    static class Point  {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x)   { return y >= 0 && y < N && x >= 0 && x < M; }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        cctvList = new ArrayList<Point>();
        
        for(int i = 0; i < N; i++)  {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)  {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] >= 1 && room[i][j] <= 5)     cctvList.add(new Point(i, j));
            }
        }

        dfs(0);

        System.out.println(min_blindSpot);

    }

    static void dfs(int index)  {

        if(index == cctvList.size())    {
            min_blindSpot = Math.min(min_blindSpot, countingBlindSpot());
            return;
        }

        // 현재 인덱스의 cctv 좌표
        Point cPoint = cctvList.get(index);

        // 현재 cctv 의 종류에 따라 달라짐
        switch (room[cPoint.y][cPoint.x]) {
            // 1번 cctv 의 경우 4가지
            case 1:
                
                // 4가지 방법으로 1방향 감시
                for(int d = 0; d < 4; d++)  {
                    // 원본 복사
                    int copy_room[][] = copy();
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[d] * i;
                        int nx = cPoint.x + dx[d] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }
                    dfs(index + 1);
                    // 원복
                    room = copy_room;
                }

                break;

            // 2번 cctv 의 경우 2가지
            case 2:
                
                // 2가지 방법으로 2방향 감시
                for(int d = 0; d < 2; d++)  {
                    // 원본 복사
                    int copy_room[][] = copy();
                    //  ↑	→
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[d] * i;
                        int nx = cPoint.x + dx[d] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }
                    //  ↓   ←
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[d + 2] * i;
                        int nx = cPoint.x + dx[d + 2] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }

                    dfs(index + 1);
                    room = copy_room;
                }
                break;

            // 3번 cctv 의 경우 4가지
            case 3:
                
                // 4가지 방법으로 2방향 감시
                for(int d = 0; d < 4; d++)  {
                    // 원본 복사
                    int copy_room[][] = copy();
                    //  ↑	→   ↓   ←
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[d] * i;
                        int nx = cPoint.x + dx[d] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }
                    //  →   ↓   ←   ↑
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[(d + 1) % 4] * i;
                        int nx = cPoint.x + dx[(d + 1) % 4] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }

                    dfs(index + 1);
                    room = copy_room;
                }
                break;

            // 4번 cctv 의 경우 4가지
            case 4:

                // 4가지 방법으로 3방향 감시
                for(int d = 0; d < 4; d++)  {
                    // 원본 복사
                    int copy_room[][] = copy();
                    //  ↑	→   ↓   ←
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[d] * i;
                        int nx = cPoint.x + dx[d] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }
                    //  →   ↓   ←   ↑
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[(d + 1) % 4] * i;
                        int nx = cPoint.x + dx[(d + 1) % 4] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }
                    //  ↓   ←   ↑   →
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[(d + 2) % 4] * i;
                        int nx = cPoint.x + dx[(d + 2) % 4] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }

                    dfs(index + 1);
                    room = copy_room;
                }
                break;

            // 5번 cctv 의 경우 1가지
            case 5:
                // 원본 복사
                int copy_room[][] = copy();

                // 1가지 방법으로 1방향 감시
                for(int d = 0; d < 4; d++)  {
                    for(int i = 0; i < 8; i++)  {
                        int ny = cPoint.y + dy[d] * i;
                        int nx = cPoint.x + dx[d] * i;
                        if(!isIn(ny, nx))   break;
                        if(room[ny][nx] == 6)   break;
                        if(room[ny][nx] >= 1 && room[ny][nx] <= 5)  continue;
                        room[ny][nx] = 7;
                    }
                }

                dfs(index + 1);
                room = copy_room;
                break;

        }

    }

    // 현재 사무실의 사각지대를 반환하는 함수
    static int countingBlindSpot()  {
        int cnt = 0;

        for(int i = 0; i < N; i++)  
            for(int j = 0; j < M; j++)  
                if(room[i][j] == 0)
                    cnt++;
            
        return cnt;
    }

    static int[][] copy()   {
        int[][] ret = new int[N][M];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++) 
                ret[i][j] = room[i][j];

        return ret;
    }  

    static void roomPrint() {
        for(int i = 0; i < N; i++)  {
            for(int j = 0; j < M; j++)  System.out.print(room[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}