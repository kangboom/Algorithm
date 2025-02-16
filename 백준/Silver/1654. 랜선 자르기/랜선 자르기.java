import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] lines;
	static int N;
	static int K;
	static long answer;
	static int max;
	
	public static void main(String[] args) throws IOException {
		input();
		binarySearch(1, max);
		System.out.println(answer);
	}
	
	static void binarySearch(long low, long high) {
		if(low>high) return;
		
		long mid = (low + high) / 2;
		
		if(validate(mid)) {
			answer = Math.max(mid,  answer);
			binarySearch(mid+1, high);
		} else {
			binarySearch(low, mid-1);
		}
	}
	
	static boolean validate(long length) {
		int cnt = 0;
		for(int i=0; i<K; i++) {
			cnt += lines[i] / length;
			if(cnt >= N) return true;
		}
		return false;
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		max = 0;
		lines = new int[K];
		for(int i=0; i<K ;i++) {
			lines[i] = Integer.parseInt(br.readLine());
			max = Math.max(lines[i], max);
		}
		
		answer = 0;
	}

}
