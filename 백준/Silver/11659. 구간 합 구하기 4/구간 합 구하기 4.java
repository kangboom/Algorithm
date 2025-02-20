import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static int[] nums;
	static int[][] query;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		query = new int[M][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			query[i][0] = Integer.parseInt(st.nextToken());
			query[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] sum = new int[N+1];
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i-1] + nums[i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int[] q : query) {
			sb.append((sum[q[1]] - sum[q[0]-1]) + "\n");
		}
		
		System.out.println(sb);
	} 
}