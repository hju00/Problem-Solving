import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int n, m;    // 상근이의 동기의 수 n (2 ≤ n ≤ 500) 리스트의 길이 m (1 ≤ m ≤ 10000)
    static boolean check[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        List<Integer> list[] = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)     list[i] = new ArrayList<>();
        check = new boolean[n + 1];
        check[1] = true;    // 상근이는 방문 처리

        while(m-- > 0)  {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a와 b가 친구라는 뜻이며, b와 a도 친구관계이다
            list[a].add(b);
            list[b].add(a);
        }

        // 상근이가 1이니까 1부터 출발
        dfs(1, 0, list);
        // 상근이는 세면 안되기 때문에 -1부터 시작
        int ans = -1;
        // 방문한 인덱스들이 친구들
        for(boolean b : check)  if(b)   ans++;
        System.out.println(ans);
    }

    static void dfs(int idx, int depth, List<Integer> list[])
    {
        // 친구의 친구까지만 초대하기로 했으므로 depth가 2이면 cut
        if(depth == 2)  return;

        // 현재 list[idx] 에 존재하는 친구 목록은 모두 방문 처리
        for(int i = 0; i < list[idx].size(); i++)   {
            int nIdx = list[idx].get(i);
            check[nIdx] = true;
            // 친구의 친구까지 초대하기 위해서 depth + 1 하고 다른 친구 찾기
            dfs(nIdx, depth + 1, list);
        }
    }
}