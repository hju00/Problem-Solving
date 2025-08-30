import java.io.*;
import java.util.*;

class Solution {
    static int V, E;
    static Node[] nodes;

    static class Node {
        int id;
        Node parent;
        ArrayList<Integer> childList = new ArrayList<>();

        Node(int id, Node parent) {
            this.id = id;
            this.parent = parent;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            nodes = new Node[V + 1];
            for (int i = 1; i <= V; i++)
                nodes[i] = new Node(i, null);

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            while (E-- > 0) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                nodes[parent].childList.add(child);
                nodes[child].parent = nodes[parent];
            }

            int common_parent = 0;
            Set<Integer> set = new HashSet<>();

            Node c = nodes[a];
            while (c != null) {
                set.add(c.id);
                c = c.parent;
            }

            c = nodes[b];
            while (c != null) {
                if (set.contains(c.id)) {
                    common_parent = c.id;
                    break;
                }
                c = c.parent;
            }

            // 자기 자신 포함
            int subTree_size = 0;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(common_parent);

            while (!q.isEmpty()) {
                int ci = q.poll();
                subTree_size++;

                for (int nx : nodes[ci].childList)
                    q.add(nx);
            }

            System.out.println("#" + test_case + " " + common_parent + " " + subTree_size);
        }
    }
}