import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		for(int i = 1; i <= N; i++)
			arr[i] = i;
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			rev(a, b);
		}
		
		for(int i = 1; i <= N; i++)
			System.out.print(arr[i] + " ");
	}
	
	static void rev(int s, int e) {
		int[] copy = new int[e - s + 1];
		
		int idx = 0;
		for(int i = e; i >= s; i--) 
			copy[idx++] = arr[i];
		
		idx = s;
		for(int i : copy)
			arr[s++] = i;
	}

}
