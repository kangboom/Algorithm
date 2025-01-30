import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] schedual;
	static StringTokenizer st;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			if(i + schedual[i][0] -1 <= N) {
				dp[i+schedual[i][0]-1] = Math.max(dp[i-1] + schedual[i][1], dp[i+schedual[i][0]-1]);
			}
			dp[i] = Math.max(dp[i-1], dp[i]); 
		}
		
		System.out.println(dp[N]);
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		schedual = new int[N+1][2];
		for(int i=1; i<=N ; i++) {
			st = new StringTokenizer(br.readLine());
			schedual[i][0] = Integer.parseInt(st.nextToken());
			schedual[i][1] = Integer.parseInt(st.nextToken());
		}
		
	}

}
