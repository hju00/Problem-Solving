import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        boolean flag[] = new boolean[N + 1];
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int t = Integer.parseInt(st.nextToken());
            s1.addLast(t);
        }

        boolean success = true;
        for(int next = 1; next <= N; next++) {
            if(!flag[next]) {
                while(s1.peekFirst() != next) {
                    int temp = s1.pollFirst();
                    s2.addFirst(temp);
                    flag[temp] = true;
                }

                s1.pollFirst();
            }
            else {
                if(s2.peekFirst() != next) {
                    success = false;
                    break;
                }
                
                s2.pollFirst();
            }
        }

        if(success)
            System.out.println("Nice");
        else
            System.out.println("Sad");
    }
}