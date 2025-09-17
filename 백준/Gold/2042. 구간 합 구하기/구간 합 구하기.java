import java.io.*;
import java.util.*;

public class Main {

    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리의 크기는 보통 N * 4 로 넉넉하게 잡음
        tree = new long[N * 4];
        init(1, 0, N - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // 값 변경
                update(1, 0, N - 1, b - 1, c);
            } else { // 구간 합 쿼리
                sb.append(query(1, 0, N - 1, b - 1, (int) c - 1)).append("\n");
            }
        }
        System.out.print(sb);
    }

    // 1. 트리 생성
    public static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    // 2. 구간 합 쿼리
    public static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
    }

    // 3. 값 변경
    public static void update(int node, int start, int end, int index, long newValue) {
        if (index < start || index > end) {
            return;
        }
        if (start == end) {
            tree[node] = newValue;
            arr[index] = newValue; // 원본 배열도 갱신
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, newValue);
        update(node * 2 + 1, mid + 1, end, index, newValue);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}