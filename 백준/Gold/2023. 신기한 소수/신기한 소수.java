import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		N = System.in.read() + 1 - '1';
		int[] start = {2, 3, 5, 7};
		for(int n : start) {
			solve(1, n);
		}
		System.out.print(sb);
	}
	
	static void solve(int depth, int num) {
		if(depth == N) {
			sb.append(num).append("\n");;
			return ;
		}
		
		for(int i=1; i<=9; i+=2){
			int tmp = num*10 + i;
			if(!isPossible(tmp)) continue; // 소수가 아니면 pass 
			solve(depth+1, tmp);
		}
	}
	
	static boolean isPossible(int n) {
		for(int i=2; i<=(int)Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false; 
			}
		}
		return true;
	}
}
