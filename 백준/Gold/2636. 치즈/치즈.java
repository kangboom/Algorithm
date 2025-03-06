import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int ansT;
	static int num;
	static int ansCnt;
	static Queue<int[]> q;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ansT);
		System.out.println(ansCnt);
	}

	static void solve() {
		int t = 1;
		while (true) {

			visited = new boolean[N][M];
			findDelete(0, 0);
			int dCnt = q.size();

			if (num == dCnt) {
				ansT = t;
				ansCnt = dCnt;
				return;
			}

			// 갱신
			while (!q.isEmpty()) {
				int[] now = q.poll();
				map[now[0]][now[1]] = 0;
			}
			
			num -= dCnt;
			++t;
		}
	}

	static void findDelete(int y, int x) {
		visited[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || ny > N - 1 || nx < 0 || nx > M - 1 || visited[ny][nx])
				continue;

			if (map[ny][nx] == 1) {
				q.add(new int[] { ny, nx });
				visited[ny][nx] = true;
			} else {
				findDelete(ny, nx);
			}
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					++num;
				}
			}
		}

	}
}
