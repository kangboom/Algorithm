import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long ans;
	static int[] lines;
	static long max;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		max = 0;
		lines = new int[N];
		for (int i = 0; i < N; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lines[i]);
		}

		search();
		System.out.println(ans);
	}

//	static void search(long low, long high) {
//		if(low > high) {
//			return ;
//		}
//		
//		long mid = (low + high) / 2;
//		
//		int cnt = 0;
//		for(int line : lines) {
//			cnt += line/mid;
//		}
//		if(cnt >= K) {
//			ans = Math.max(ans, mid);
//			search(mid+1, high);
//		} else {
//			search(low, mid-1);
//		}
//		
//	}
	static void search() {
		long low = 1;
		long high = max;
		while (low <= high) {
			long mid = (low + high) / 2;

			int cnt = 0;
			for (int line : lines) {
				cnt += line / mid;
			}
			
			if(cnt >= K) {
				ans = Math.max(ans, mid);
				low = mid+1;
			} else {
				high = mid-1;
			}
		}
	}

}
