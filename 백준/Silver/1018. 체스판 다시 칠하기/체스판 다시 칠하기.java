import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int mn[][] = new int[a][b];
		
		for (int i = 0; i < a; i++) {
			String wb = br.readLine();
			for (int j = 0; j < b; j++) {
				if (wb.charAt(j) == 'W')
					mn[i][j] = 1;
				else	mn[i][j] = 0;
			}
		}
		
		int count = 0, min = 64;
		int check = 1;
		
		for (int c = 0; c <= a - 8; c++) {
			for (int d = 0; d <= b - 8; d++) {
				
				count = 0;
				
				for (int i = c; i < c + 8; i++) {
					for (int j = d; j < d + 8; j++) {
						if (check == 1) {
							if (mn[i][j] == 0)	count++;
						}
						else {
							if (mn[i][j] == 1) 	count++;
						}
						check *= (-1);
					}
					check *= (-1);
				}
				
				if (count > 64 - count) count = 64 - count;
				if (min > count)	min = count;
			}
		}

		System.out.println(min);
			

	}

}