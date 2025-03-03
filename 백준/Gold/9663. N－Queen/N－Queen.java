import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static boolean[] col;
	static boolean[] slash;
	static boolean[] bslash;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N+1];
		slash = new boolean[2*N+1];
		bslash = new boolean[2*N];
		
		solve(1);
		System.out.println(answer);
	}
	
	static void solve(int r) {
		if(r == N+1) {
			++answer;
			return ;
		}
		
		for(int c=1; c<=N; c++) {
			if(isImpossilbe(r, c)) continue;
			
			col[c] = slash[r+c] = bslash[(r-c)+N] = true;
			solve(r+1);
			col[c] = slash[r+c] = bslash[(r-c)+N] = false;
		}
	}
	
	static boolean isImpossilbe(int r, int c) {
		return col[c] || slash[r+c] || bslash[(r-c)+N]; 
	}

}
