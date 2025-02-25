import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] S;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int answer;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input();
			solve(0, 0, 0);
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.print(sb);
	}

	static void solve(int depth, int visited, int cnt) {
		if (cnt > N / 2 || depth-cnt > N/2)
			return;
		
		if (depth == N) {
			int A = 0;
			int B = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((visited & 1 << i) != 0) {// A인 경우
						if ((visited & 1 << j) != 0) {
							A += S[i][j];
						}
					} else {// B인 경우
						if ((visited & 1 << j) == 0) {
							B += S[i][j];
						}
					}
				}
			}
			answer = Math.min(answer, Math.abs(A - B));
			return;
		}

			// A로 선택
			solve(depth + 1, visited | 1 << depth, cnt + 1);

			// B로 선택
			solve(depth + 1, visited, cnt);
		
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
	}
}