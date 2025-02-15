import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] cards;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		cards = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] targets = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cards);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			if(binarySearch(targets[i], 0, N-1)) {
				sb.append(1 + " ");
			} else {
				sb.append(0 + " ");
			}
		}
		System.out.println(sb);
	}
	
	static boolean binarySearch(int target, int start, int end) {

		if(start>end) return false;
		
		int mid = (start+end)/2;
		
		if(cards[mid] == target) {
			return true;
		} else if(cards[mid] < target) {
			return binarySearch(target, mid+1, end);
		} else {
			return binarySearch(target, start, mid-1);
		}
	}
}
