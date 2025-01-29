import java.util.*;
import java.io.*;

public class Main {

	static int W;
	static int H;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int p;
	static int q;
	static int t;
	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(p + " " + q);
	}
	
	static void solve() {
		if(((p+t)/W) % 2 == 0) {
			p = (p+t)%W;
		} else {
			p = W-((p+t)%W);
		}
		
		if(((q+t)/H) % 2 == 0) {
			q = (q+t)%H;
		} else {
			q = H-((q+t)%H);
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		t = Integer.parseInt(br.readLine());
	}
}
