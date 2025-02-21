import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int answer;
	static int N;
	static int[][] info;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		info = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());	
			info[i][1] = Integer.parseInt(st.nextToken());	
		}
		
		// 로직
		answer = Integer.MAX_VALUE;
		solve(0, 0);
		System.out.println(answer);
	}
	
	static void solve(int depth, int visited) {
		if(depth == N) {
			if(visited == 0) return ;
			
			int sin = 1;
			int ssn = 0;
			for(int i=0; i<N; i++) {
				if((visited & 1<<i) != 0){ // 뽑은 경우
					sin *= info[i][0];
					ssn += info[i][1];
				}
			}
			answer = Math.min(answer, Math.abs(sin-ssn));
			return ;
		}
		
		solve(depth+1, visited | 1<<depth);
		solve(depth+1, visited);
	}
		
}
