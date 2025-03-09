import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int ans;
	static int N;
	static int M;
	static int D;
	static int[] order;
	static int removeCnt;
	
	public static void main(String[] args) throws IOException {
		input();
		comb(0, 0);
		System.out.println(ans);
	}

	static void play() {
		// 기존 map 복사
		removeCnt = 0; // 제거한 적의 개수
		int depth = N;
		int[][] status = new int[N][M];
		while (depth>0) {
			for (int i = 0; i < 3; i++) {
				boolean[][] visited = new boolean[N][M];
				bfs(depth, order[i], visited, status); // 적 제거
			}
			--depth;
		}
		ans = Math.max(ans, removeCnt);
	}

	static void bfs(int depth, int x, boolean[][] visited, int[][] status) {
		int[] dy = { 0, -1, 0 };
		int[] dx = { -1, 0, 1 };
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { depth, x, 0 });
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int cy = now[0];
			int cx = now[1];
			int clen = now[2];

			if (clen == D)
				break;

			for (int i = 0; i < 3; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];

				if (ny < 0 || ny > depth - 1 || nx < 0 || nx > M - 1 || visited[ny][nx])
					continue;
				if(map[ny][nx] == 1 && status[ny][nx] == 0) {
					++removeCnt;
					status[ny][nx] = depth;
					return ;
				} else if(map[ny][nx] == 1 && status[ny][nx] == depth) {
					return ;
				}
				
				visited[ny][nx] = true;
				q.offer(new int[] {ny, nx, clen+1});
			}
		}
	}

	static void comb(int start, int depth) {
		if (depth == 3) {
			play();
			return;
		}
		for (int i = start; i < M; i++) {
			order[depth] = i;
			comb(i + 1, depth + 1);
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[3];
	}
}
