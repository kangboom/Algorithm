import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[] arr;
	static int ans;
	static int maxSize;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxSize += arr[i];
		}
		ans = Integer.MAX_VALUE;
		solve();
		System.out.println(ans);
	}
	
	static boolean check(int size) {
		
		int sum = 0;
		int cnt = 1;
		for(int i=0; i<N; i++) {
			if(arr[i] > size) return false;
			if(sum + arr[i] > size) {
				++cnt;
				sum = arr[i];
			} else {
				sum += arr[i];
			}
		}
		
		return cnt <= M;
	}
	static void solve() {
		int left = 1;
		int right = maxSize;
		
		while(left<=right) {
			int mid = (left + right) / 2;
			if(check(mid)) {
				right = mid-1;
				ans = Math.min(ans, mid);
			} else {
				left = mid+1;
			}
		}
	}
}
