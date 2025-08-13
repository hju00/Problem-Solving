import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int N;   // (1 ≤ N ≤ 49) 홀수
    static int[][] farm;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = Integer.parseInt(br.readLine());
            farm = new int[N][N];
            int sum = 0;

            for(int i = 0; i < N; i++)  {
                String str = br.readLine();
                for(int j = 0; j < N; j++)  {
                    farm[i][j] = str.charAt(j) - '0';
                    sum += farm[i][j];
                }
            }

            int half = (int) N / 2;
            int start = 0, end = N - 1, temp = half;

            while(start != end) {
                for(int i = 0; i < temp; i++)   
                    sum -= farm[start][i] + farm[start][N - 1 - i] + farm[end][i] + farm[end][N - 1 - i];
                
                start++;
                end--;
                temp--;
            }

            System.out.println("#" + test_case + " " + sum);
		}
	}
}