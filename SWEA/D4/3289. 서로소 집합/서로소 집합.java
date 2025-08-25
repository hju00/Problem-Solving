import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int[] parent; // 각 원소의 부모를 저장할 배열

    // 2. find: 원소 x의 대표자를 찾는 함수 (경로 압축 최적화 적용)
    static int find(int x) {
        // 자기 자신이 대표자이면 반환
        if (parent[x] == x) return x;
        // 아니면, 부모를 따라 올라가면서 경로상의 모든 노드가 직접 대표자를 가리키도록 함
        return parent[x] = find(parent[x]);
    }

    // 3. union: 두 원소 x, y를 같은 집합으로 합치는 함수
    static void union(int x, int y) {
        int rootX = find(x); // x의 대표자
        int rootY = find(y); // y의 대표자

        // 이미 같은 집합이면 합칠 필요 없음
        if (rootX == rootY) return;

        // 한쪽의 대표자가 다른 쪽 대표자를 가리키도록 함
        parent[rootY] = rootX;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 1. 초기화: 모든 원소가 자기 자신을 대표자로 갖도록 설정
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) 
                parent[i] = i;

            StringBuilder sb = new StringBuilder();

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                switch (command) {
                    case 0:
                        union(a, b);
                        break;
                    
                    case 1:
                        if(find(a) == find(b))  sb.append("1");
                        else                    sb.append("0");
                        break;
                }
            }

            System.out.println("#" + test_case + " " + sb.toString());
        }
    }
}