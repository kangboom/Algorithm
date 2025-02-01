import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int L;
	static int R;
	static int S;
	static StringTokenizer st;
	static int stage;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			input();
			if(S == L || S == R) {
				System.out.println(1);
				return ;
			}
			solve();
			System.out.println(stage);
		}
		
	}
	static void solve() {
		if(R-S <= S-L) {
			stage = 2*(R-S);
		} else {
			stage = 2*(S-L)+1;
		}
		
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
	}
}
