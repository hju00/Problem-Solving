import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int N;
    static int[] nums;
    static int[] operators;
    static int min_num, max_num;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            operators = new int[4];
            min_num = Integer.MAX_VALUE;
            max_num = Integer.MIN_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) operators[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

            // 백트래킹 시작: 첫 번째 숫자를 초기 결과값으로 설정하고, 두 번째 숫자부터 연산
            dfs(1, nums[0]);

            System.out.println("#" + test_case + " " + (max_num - min_num));
        }
    }

    static void dfs(int index, int result) {

        // 종료 조건: 모든 숫자를 다 사용했을 경우
        if(index == N) {
            min_num = Math.min(min_num, result);
            max_num = Math.max(max_num, result);
            return;
        }

        // 4가지 연산자에 대해 순서대로 시도
        for (int i = 0; i < 4; i++) {
            // 해당 연산자가 남아있을 경우
            if (operators[i] > 0) {
                operators[i]--; // 연산자 사용

                int new_result = 0;
                switch (i) {
                    case 0: // 덧셈
                        new_result = result + nums[index];
                        break;
                    case 1: // 뺄셈
                        new_result = result - nums[index];
                        break;
                    case 2: // 곱셈
                        new_result = result * nums[index];
                        break;
                    case 3: // 나눗셈
                        new_result = result / nums[index];
                        break;
                }

                dfs(index + 1, new_result); // 다음 숫자로 재귀 호출

                operators[i]++; // 백트래킹: 사용한 연산자 복구
            }
        }
    }
}
