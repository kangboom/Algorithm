import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {

		input();
		int sum = 0;
		for(int i=0; i<K; i++) {
			sum += arr[i];
		}

		int maxSum = sum;
		for(int i=K; i<N; i++) {
			sum = sum - arr[i-K] + arr[i];
			maxSum = Math.max(maxSum, sum);
		}
		System.out.println(maxSum);
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}
