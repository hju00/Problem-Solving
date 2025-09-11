import java.io.*;
import java.util.*;

class Main {
    static ArrayList<Integer> primes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        primes = new ArrayList<>();

        for(int i = 2; i <= N; i++) 
            if(isPrime(i))
                primes.add(i);
        
        long sum[] = new long[primes.size() + 1];
        for(int i = 1; i < primes.size() + 1; i++) 
            sum[i] = sum[i - 1] + primes.get(i - 1);

        int s = 1, e = 1;
        int ans = 0;
        while (s < sum.length && e < sum.length) { 
            if(sum[e] - sum[s - 1] == N) {
                ans++;
                s++;
            }
            else if(sum[e] - sum[s - 1] < N) {
                if(e == sum.length - 1) s++;
                else                    e++;
            }
            else s++;
        }

        System.out.println(ans);
    }

    static boolean isPrime(int n) {
        for(int i = 2; i * i <= n; i++) 
            if(n % i == 0)
                return false;
        return true;
    }

}