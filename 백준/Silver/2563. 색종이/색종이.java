import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        boolean board[][] = new boolean[100][100];

        while(N-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            for(int i = y; i < y + 10; i++)
                for(int j = x; j < x + 10; j++)
                    board[i][j] = true;
        }

        int cnt = 0;
        for(int i = 0; i < 100; i++)
            for(int j = 0; j < 100; j++)
                if(board[i][j]) cnt++;

        System.out.println(cnt);

    }
}