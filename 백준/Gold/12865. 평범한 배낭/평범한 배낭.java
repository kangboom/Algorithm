import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int K;
	static int[][] things;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(dp[N][K]);
	}
	
	static void solve() {
		for(int i=1; i<=N; i++) {
			for(int w=1; w<=K; w++) {
				dp[i][w] = Math.max(dp[i][w], Math.max(dp[i-1][w], dp[i][w-1]));
				if(w-things[i][0] >=0) {
					dp[i][w] = Math.max(dp[i][w], dp[i-1][w-things[i][0]] + things[i][1]);
				}
			}
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		things = new int[N+1][2];
		dp = new int[N+1][K+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(things, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
	}

}
