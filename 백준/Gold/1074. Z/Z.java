import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int R;
	static int C;
	static int[] dy = { 0, 0, 1, 1 };
	static int[] dx = { 0, 1, 0, 1 };
	static boolean isFind;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		solve(0, 0, (int) Math.pow(2, N), 0);
	}

	static void solve(int y, int x, int len, int num) {

		if (len == 2) {
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny == R && nx == C) {
					System.out.println(num);
				}
				num++;
			}
			return;
		}

		// 4군데 확인
		int half = len / 2;
		
		if (R < y + half && C < x + half) { // 2사분면
			solve(y, x, half, num);
			
		} else if (R < y + half && C >= x + half) { // 1사분면
			solve(y, x + half, half, num + len*len/4);
			
		} else if (R >= y + half && C < x + half) { // 3사분면
			solve(y + half, x, half, num + len*len/2);
			
		} else { // 4사분면
			solve(y + half, x + half, half, num + len*len/4*3);
		}
	}

}
