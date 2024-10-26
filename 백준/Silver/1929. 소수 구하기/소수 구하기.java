import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int result[] = new int[N + 1];
		
		for (int i = M; i <= N; i++) {
			for (int j = 2; j*j <= i; j++) {
				if (i % j == 0) {
					result[i]++;
					break;
				}
			}
		}
        
        result[1]++;
		result[2] = 0;
		
		for (int i = M; i <= N; i++) {
			if(result[i] == 0)
				System.out.println(i);
		}
				
	}

}