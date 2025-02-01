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
		stage=2;

		int now = S;
		while(true) {
			if(stage%2 == 0) {
				now += stage-1;
			} else {
				now -= stage-1;
			}
			
			if(now == L || now == R) {
				return ;
			}
			stage++;
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
	}
}
