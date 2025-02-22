import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static StringBuilder sb = new StringBuilder();
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		order = new int[N];
		int[] start = {2, 3, 5, 7};
		for(int n : start) {
			order[0] = n;
			solve(1);
		}
		System.out.println(sb);
	}
	
	static void solve(int depth) {
		if(depth == N) {
			for(int i=0; i<N; i++) {
				sb.append(order[i]);
			}
			sb.append("\n");
			return ;
		}
		
		for(int i=1; i<=9; i+=2){
			if(!isPossible(i, depth)) continue; // 소수가 아니면 pass 
			order[depth] = i;
			solve(depth+1);
		}
	}
	
	static boolean isPossible(int n, int depth) {
		int num = 0;
		for(int i=0; i<depth; i++) {
			num = num*10 + order[i];
		}
		num = num*10 + n;
		for(int i=2; i<=(int)Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false; 
			}
		}
		return true;
	}
	
}
