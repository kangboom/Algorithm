import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] houses;
	static int N;
	static int C;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		input();
		binarySearch(1, (houses[N-1]- houses[0])/(C-1));
		System.out.println(answer);
	}
	
	static void binarySearch(int low, int high) {
		if(low > high) return ;
		
		int mid = (low+high)/2;
		
		if(validate(mid)) { // 가능하면 
			answer = Math.max(answer,  mid);
			binarySearch(mid+1, high);
		} else { // 불가능
			binarySearch(low, mid-1);
		}
	}
	
	static boolean validate(int distance) {
		int pre = houses[0];
		int cnt = 1;
		
		for(int i=1; i<N; i++) {
			if(houses[i]-pre >= distance) {
				++cnt;
				pre = houses[i];
			}
			if(cnt==C) return true;
		}
		return false;
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		houses = new int[N];
		for(int i=0; i<N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houses);
	}

}
