import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution
{
    static int N, M;    //  (1 ≤ N ≤ 20, 0 ≤ M ≤ 400)
    static boolean impossible[][];
    static int cnt;

    static void dfs(int idx, ArrayList<Integer> al) {
        // dfs 종료조건
        if(idx == N + 1)    {
            cnt++;
            // 제약조건을 만족하는 부분집합을 확인할 수 있다
            // System.out.println(al);
            return;
        }
        
        // ArrayList를 복사해준다, 복사방법 몰라서 이렇게 했는데 좋은 방법 아시는 분 알려주세요
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        for(int i = 0; i < al.size(); i++)  {
            a1.add(al.get(i));
            a2.add(al.get(i));
        }

        // 부분집합 내의 원소가 현재 idx와 부분집합을 이룰 수 있는 지 확인
        boolean check = true;
        for(int i = 0; i < al.size(); i++)  
            if(impossible[idx][al.get(i)])  check = false; 

        // 이룰 수 있으면 현재 idx를 추가한 부분집합을 dfs에 넘겨주기
        if(check)   {
            a1.add(idx);
            dfs(idx + 1, a1);
        }
        
        // 현재 idx를 부분집합에 추가하지 않은 경우
        dfs(idx + 1, a2);
    }
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 부분집합에 같이 있을 수 없는 수를 true 처리
            impossible = new boolean[N + 1][N + 1];
            while(M-- > 0)  {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                impossible[a][b] = true;
                impossible[b][a] = true;
            }

            cnt = 0;
            ArrayList<Integer> a = new ArrayList<>();
            dfs(1, a);

            System.out.println("#" + test_case + " " +cnt);
		}
	}
}