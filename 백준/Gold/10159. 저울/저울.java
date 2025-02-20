import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static StringTokenizer st;
	static int[][] d;
	static int[] cnt;
	public static void main(String[] args) throws IOException {
		input();

		for(int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					if(d[i][j] != 0 ) continue;
					
					if(d[i][k] == -1 && d[k][j] == -1) {
						d[i][j] = -1;
					}
					
					if(d[i][k] == 1 && d[k][j] == 1) {
						d[i][j] = 1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			int tmp = 0;
			for(int j=1; j<=N; j++) {
				if(d[i][j] == 0) {
					++tmp;
				}
			}
			sb.append(tmp-1 + "\n");
		}
		System.out.println(sb);
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		cnt = new int[N+1];
		d = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			d[a][b] = -1;
			d[b][a] = 1;
		}
		
	}
	
	

}
