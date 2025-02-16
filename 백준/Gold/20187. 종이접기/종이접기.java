import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<int[]>[][] map;
	static int[][] stand;
	static int K;
	static int H;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[] order;
	static int size;
	static boolean[][] answer;

	public static void main(String[] args) throws IOException {
		input();
		solve();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i += 2) {
			for (int j = 0; j < size; j += 2) {
				if(answer[i][j]) sb.append(0 + " ");
				if(answer[i][j+1]) sb.append(1 + " ");
				if(answer[i+1][j]) sb.append(2 + " ");
				if(answer[i+1][j+1]) sb.append(3 + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void solve() {
		for (int i = 0; i < 2 * K; i++) {
			fold(order[i]);
		}

		int y = stand[H][0];
		int x = stand[H][1];

		answer[y][x] = true;
		for (int i = 0; i < map[y][x].size(); i++) {
			int[] location = map[y][x].get(i);
			answer[location[0]][location[1]] = true;
		}

	}

	static void fold(char ch) {
		int width = stand[1][1] - stand[0][1] + 1;
		int height = stand[2][0] - stand[0][0] + 1;

		
		if (ch == 'D') {
			stand[0][0] = stand[0][0] + height / 2;
			stand[1][0] = stand[1][0] + height / 2;
			int cnt = 0;
			for (int i = stand[0][0]; i <= stand[3][0]; i++) {
				for (int j = stand[0][1]; j <= stand[3][1]; j++) {
					map[i][j].addAll(map[i - (2 * cnt + 1)][j]);
					map[i][j].add(new int[] { i - (2 * cnt + 1), j });
				}
				cnt++;
			}
		}
		if (ch == 'U') {
			stand[2][0] = stand[2][0] - height / 2;
			stand[3][0] = stand[3][0] - height / 2;

			int cnt = 0;
			for (int i = stand[2][0]; i >= stand[0][0]; i--) {
				for (int j = stand[0][1]; j <= stand[3][1]; j++) {
					map[i][j].addAll(map[i + (2 * cnt + 1)][j]);
					map[i][j].add(new int[] { i + (2 * cnt + 1), j });
				}
				cnt++;
			}

		}
		if (ch == 'R') {
			stand[0][1] = stand[0][1] + width / 2;
			stand[2][1] = stand[2][1] + width / 2;

			for (int i = stand[0][0]; i <= stand[2][0]; i++) {
				int cnt = 0;
				for (int j = stand[0][1]; j <= stand[1][1]; j++) {
					map[i][j].addAll(map[i][j - (2 * cnt + 1)]);
					map[i][j].add(new int[] { i, j - (2 * cnt + 1)});
					cnt++;
				}
			}
		}
		if (ch == 'L') {
			stand[1][1] = stand[1][1] - width / 2;
			stand[3][1] = stand[3][1] - width / 2;

			for (int i = stand[0][0]; i <= stand[3][0]; i++) {
				int cnt = 0;
				for (int j = stand[1][1]; j >= stand[0][1]; j--) {
					map[i][j].addAll(map[i][j + (2 * cnt + 1)]);
					map[i][j].add(new int[] { i, j + (2 * cnt + 1) });
					cnt++;
				}
			}
		}
	}

	static void input() throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		order = new char[2 * K];
		for (int i = 0; i < 2 * K; i++) {
			order[i] = st.nextToken().charAt(0);
		}

		H = Integer.parseInt(br.readLine());

		size = (int) Math.pow(2, K + 1);
		map = new List[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		stand = new int[4][2];
		stand[0] = new int[] { 0, 0 };
		stand[1] = new int[] { 0, size - 1 };
		stand[2] = new int[] { size - 1, 0 };
		stand[3] = new int[] { size - 1, size - 1 };

		answer = new boolean[size][size];
	}

}
