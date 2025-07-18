import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static long factorial(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean check[] = new boolean[N + 1];

        if (N == 1) {
            System.out.println(1);
            return;
        }

        int c = Integer.parseInt(st.nextToken());

        switch (c) {
            case 1:
                long k = Long.parseLong(st.nextToken());

                int result[] = new int[N];

                for (int i = 0; i < N; i++) {

                    long fact = factorial(N - i - 1);

                    for (int j = 1; j <= N; j++) {

                        if (check[j]) continue;

                        if (k > fact) k -= fact;
                        else {
                            result[i] = j;
                            check[j] = true;
                            break;
                        }
                    }
                }

                for (int r : result) System.out.print(r + " ");
                break;

            case 2:
                int arr[] = new int[N];
                for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

                long ans = 1;

                for (int i = 0; i < N; i++) {

                    int smaller = 0;

                    for (int j = 1; j < arr[i]; j++) {
                        if (!check[j]) smaller++;
                    }

                    ans += smaller * factorial(N - 1 - i);
                    check[arr[i]] = true;
                }

                System.out.println(ans);
                break;
        }
    }
}
