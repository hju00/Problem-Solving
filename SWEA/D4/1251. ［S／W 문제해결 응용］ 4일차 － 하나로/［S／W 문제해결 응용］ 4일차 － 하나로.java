import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int N;           // 섬의 개수
    static double E;        // 환경 부담 세율
    static int[] parent;    // Union-Find를 위한 부모 노드 배열
    static int[] islandX;   // 섬들의 X 좌표
    static int[] islandY;   // 섬들의 Y 좌표

    // 1. 간선 정보를 저장할 클래스 (비용 기준으로 정렬 가능하도록 Comparable 구현)
    static class Edge implements Comparable<Edge> {
        int from, to; // 연결되는 두 섬의 인덱스
        long cost;    // 두 섬 사이의 비용 (L^2)

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        // 비용(cost) 기준으로 오름차순 정렬
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    // 2. Union-Find 로직
    // 초기화: 모든 섬이 자기 자신을 대표자로 갖도록 설정
    static void makeSet() {
        parent = new int[N];
        for (int i = 0; i < N; i++) 
            parent[i] = i;
    }

    // find: 원소 x의 대표자를 찾는 함수 (경로 압축 최적화 적용)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // union: 두 원소 x, y를 같은 집합으로 합치는 함수
    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false; // 이미 같은 집합이면 합치기 실패

        parent[rootY] = rootX;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            islandX = new int[N];
            islandY = new int[N];

            // 섬 X, Y 좌표 입력받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) islandX[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) islandY[i] = Integer.parseInt(st.nextToken());
            
            E = Double.parseDouble(br.readLine());

            // 3. 크루스칼 알고리즘 시작
            // Step 1: 모든 간선을 생성하여 리스트에 추가
            List<Edge> edgeList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = (long)islandX[i] - islandX[j];
                    long dy = (long)islandY[i] - islandY[j];
                    long cost = dx * dx + dy * dy; // L^2 계산
                    edgeList.add(new Edge(i, j, cost));
                }
            }

            // Step 2: 간선을 비용 기준으로 오름차순 정렬
            Collections.sort(edgeList);

            // Step 3: 가장 저렴한 간선부터 순회하며 MST 생성
            makeSet(); // Union-Find 구조 초기화
            long totalCost = 0; // 총 비용 (L^2의 합)
            int edgeCount = 0;  // 선택된 간선 수

            for (Edge edge : edgeList) {
                // 두 섬이 아직 연결되지 않았다면 (사이클을 만들지 않는다면)
                if (union(edge.from, edge.to)) {
                    totalCost += edge.cost; // 비용 추가
                    edgeCount++;            // 간선 수 증가
                }

                // 모든 섬이 연결되었다면 (N-1개의 간선을 선택했다면) 종료
                if (edgeCount == N - 1) break;
            }

            // 최종 결과 계산 및 출력 (E * L^2)
            long result = Math.round(totalCost * E);
            System.out.println("#" + test_case + " " + result);
        }
    }
}