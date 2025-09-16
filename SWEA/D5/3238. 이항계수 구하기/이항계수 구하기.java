import java.io.*;
import java.util.*;

public class Solution {

    static long N, R, P;
    static long[] fact;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++)
        {
            st = new StringTokenizer(br.readLine());

            N = Long.parseLong(st.nextToken());
            R = Long.parseLong(st.nextToken());
            P = Long.parseLong(st.nextToken());
            
            fact = new long[(int) P];
            fact[0] = 1;
            for(int i = 1; i < P; i++)
                fact[i] = (fact[i - 1] * i) % P;
            
            long result = lucas(N, R);
            System.out.println("#" + tc + " " + result);
        }
    }

    // 3단계: 뤼카의 정리
    static long lucas(long n, long r) {
        if (r == 0) 
            return 1;
        
        // n과 r을 P진법으로 표현했을 때의 마지막 자리수
        long ni = n % P;
        long ri = r % P;

        // n < r 이면 조합의 결과는 0
        if (ni < ri) 
            return 0;

        // 재귀 호출: C(n/p, r/p) * C(n%p, r%p)
        return (combination((int) ni, (int) ri) * lucas(n / P, r / P)) % P;
    }

    // 작은 조합 계산: nCr mod p (단, n < p)
    static long combination(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (n < r) return 0;

        // n! / (r! * (n-r)!) mod p
        // = fact[n] * (fact[r] * fact[n-r])^(p-2) mod p

        long denominator = (fact[r] * fact[n - r]) % P;
        long inverse = power(denominator, P - 2);

        return (fact[n] * inverse) % P;
    }

    // 1단계: 분할 정복을 이용한 거듭제곱 (모듈러 역원 계산에 사용)
    static long power(long base, long exp) {
        long res = 1;
        base %= P;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % P;
            base = (base * base) % P;
            exp /= 2;
        }
        return res;
    }
}