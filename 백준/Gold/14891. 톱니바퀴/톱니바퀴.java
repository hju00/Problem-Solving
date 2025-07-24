import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int gear[][];

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N극은 0, S극은 1
        gear = new int[4][8];
        for(int i = 0; i < 4; i++)  {
            String line = br.readLine();
            for(int j = 0; j < 8; j++)  gear[i][j] = line.charAt(j) - '0';
        }

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            // 방향이 1인 경우는 시계 방향, -1인 경우는 반시계 방향
            // [0][2] - [1][6], [1][2] - [2][6], [2][2] - [3][6]

            int canRotate[] = new int[4];
            canRotate[n - 1] = r;

            // 왼쪽으로 전파
            for (int i = n - 1; i > 0; i--) {
                if (gear[i - 1][2] != gear[i][6]) {
                    canRotate[i - 1] = -canRotate[i];
                } else {
                    break;  // 맞물리지 않으면 멈춤
                }
            }

            // 오른쪽으로 전파
            for (int i = n - 1; i < 3; i++) {
                if (gear[i][2] != gear[i + 1][6]) {
                    canRotate[i + 1] = -canRotate[i];
                } else {
                    break;  // 맞물리지 않으면 멈춤
                }
            }

            for(int i = 0; i < 4; i++)  {

                switch (canRotate[i]) {
                    case 1:
                        {
                            int temp = gear[i][7];
                            for (int j = 7; j > 0; j--) gear[i][j] = gear[i][j - 1];
                            gear[i][0] = temp;
                            break;
                        }
                    case -1:
                        {
                            int temp = gear[i][0];
                            for(int j = 0; j < 7; j++)  gear[i][j] = gear[i][j + 1];
                            gear[i][7] = temp;
                            break;
                        }
                }

            }
            
        }

        System.out.println(gear[0][0] * 1 + gear[1][0] * 2 + gear[2][0] * 4 + gear[3][0] * 8);
            
    }
}