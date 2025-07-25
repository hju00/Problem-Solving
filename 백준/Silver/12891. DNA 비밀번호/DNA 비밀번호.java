import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int S;
    static int P;
    static String dna;
    static int[] required = new int[4];
    static int[] current = new int[4];
    static char[] dnaChar = {'A', 'C', 'G', 'T'};

    public static int charNum(char c)  {
        switch (c) {
            case 'A':   return 0;
            case 'C':   return 1;
            case 'G':   return 2;
            case 'T':   return 3;
        }
        return -1;
    }

    public static boolean isValid() {
        for(int i = 0; i < 4; i++)  if(current[i] < required[i])    return false;
        return true;        
    }

    public static void main(String[] args) throws IOException {
        // 코드 작성
        //  (1 ≤ |P| ≤ |S| ≤ 1,000,000)
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st1.nextToken());
        P = Integer.parseInt(st1.nextToken());

        dna = br.readLine();
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)     required[i] = Integer.parseInt(st2.nextToken());

        for(int i = 0; i < P; i++)  current[charNum(dna.charAt(i))]++;
        int cnt = 0;
        if(isValid())   cnt++;

        for(int i = P; i < S; i++)  {
            current[charNum(dna.charAt(i - P))]--;
            current[charNum(dna.charAt(i))]++;
            if(isValid()) cnt++;
        }
        
        System.out.println(cnt);
    }   
}