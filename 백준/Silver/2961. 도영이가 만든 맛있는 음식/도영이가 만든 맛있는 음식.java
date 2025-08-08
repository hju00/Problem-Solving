import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;   // (1 ≤ N ≤ 10)
    static int acidity[];   // 신 맛
    static int acerbity[];  // 쓴 맛
    static long min_diff = Long.MAX_VALUE;

    // 한 가지 이상의 재료를 선택해야 하기 때문에 재료의 선택 여부 변수(isSelected) 추가
    static void dfs(int idx, long acid, long acer, boolean isSelected)  {
        if(idx == N)    {
            // 재료를 한 가지 이상 선택한 경우만 최솟값 갱신
            if(isSelected)  min_diff = Math.min(min_diff, Math.abs(acid - acer));
            return;
        }

        // 현재 재료를 선택한 경우
        dfs(idx + 1, acid * acidity[idx], acer + acerbity[idx], true);
        // 현재 재료를 선택하지 않은 경우
        dfs(idx + 1, acid, acer, isSelected);
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        acidity = new int[N];
        acerbity = new int[N];

        for(int i = 0; i < N; i++)  {
            StringTokenizer st = new StringTokenizer(br.readLine());
            acidity[i] = Integer.parseInt(st.nextToken());
            acerbity[i] = Integer.parseInt(st.nextToken());
        }

        // 신 맛은 곱해야 하기 때문에 초기값이 0이 아닌 1
        dfs(0, 1, 0, false);

        System.out.println(min_diff);
    }
}