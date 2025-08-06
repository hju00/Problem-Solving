import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.text.AbstractDocument;

class Solution
{
    static int nums[];      // 카드 사용 여부 판단
    static int tc1[];       // tc1의 순서에 따라 승패 결정
    static int tc2[];       // tc2는 규영이의 카드 고정
    static int ans, sum1, sum2; // sum1 : 인영이의 점수, sum2 : 규영이의 점수

    static void dfs(int level)   {
        // tc1 에 카드가 모두 들어갔다면 게임 시작
        if(level == 9)  {
            sum1 = 0;
            sum2 = 0;
            
            // 각 카드를 비교하면서 점수 계산
            for(int i = 0; i < 9; i++)  {
                if(tc1[i] > tc2[i])     sum1 += tc1[i] + tc2[i];
                else                    sum2 += tc1[i] + tc2[i];
            }
            // 규영이가 이겼다면 ans 증가
            if(sum1 < sum2) ans++;
            return;
        }
        
        for(int i = 1; i < 19; i++) {
            // 사용한 카드가 아니라면 : 1이 아니라면
            if(nums[i] != 1)    {
                // tc1 에 카드 넣기
                tc1[level] = i;
                // 해당 카드 사용 처리
                nums[i]++;

                dfs(level + 1);
                // 빠져나오면 다시 미사용 처리 : 백트래킹
                nums[i]--;
            }
        }
    }
	public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            ans = 0;
            nums = new int[19];
            tc1 = new int[9];
            tc2 = new int[9];

            // tc1, tc2 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 9; i++)  {
                int temp = Integer.parseInt(st.nextToken());
                nums[temp]++;       // 주어진 카드 사용처리
                tc2[i] = temp;      // 규영이의 카드 저장
            }

            dfs(0);

            // 9! = 362880
            System.out.println("#" + test_case + " " + ans + " " + (362880 - ans));

            
		}
	}
}