import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] dp;
	static int N;
	static int[] data;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		dp = new int[N];
		// 순회
		int asc = 1;
		int dsc = 1;
		dp[0] = 1;
		for(int i=1; i<N; i++) {
			if(data[i] == data[i-1]) {
				asc++;
				dsc++;
			} else if(data[i] > data[i-1]) {
				asc++;
				dsc=1;
			} else {
				dsc++;
				asc=1;
			}
			int temp = Math.max(asc, dsc);
			dp[i] = Math.max(dp[i-1], temp);
		}
		System.out.println(dp[N-1]);
	}
}
