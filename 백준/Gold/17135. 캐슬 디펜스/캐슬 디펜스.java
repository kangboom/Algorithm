import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int ans;
	static int N;
	static int M;
	static int D;
	static int remainCnt;
	static int[] order;
	static List<int[]> removeList;

	public static void main(String[] args) throws IOException {
		input();
		comb(0, 0);
		System.out.println(ans);
	}

	static void play() {
		// 기존 map 복사
		int[][] copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyMap[i] = Arrays.copyOf(map[i], M);
		}

		int removeCnt = 0; // 제거한 적의 개수
		boolean isContinue = true; // 게임 계속 진행할지 여부
		boolean isFirst = true; 
		while (isContinue) {

			removeList.clear();
			for (int i = 0; i < 3; i++) {
				boolean[][] visited = new boolean[N][M];
				bfs(N - 1, order[i], 1, copyMap, visited);
			}

			for (int[] loc : removeList) {
				if(copyMap[loc[0]][loc[1]] == 0) continue;
				copyMap[loc[0]][loc[1]] = 0;
				++removeCnt;
			}
			
			// 적 전진
			isContinue = false;
			for(int i=N-2; i>=0; i--) {
				for(int j=0; j<M; j++) {
					copyMap[i+1][j] = copyMap[i][j];
					if(copyMap[i][j] == 1) {
						isContinue = true;
					}
				}
			}
			
			if(isFirst) {
				Arrays.fill(copyMap[0], 0);
				isFirst = false;
			}
		}
		ans = Math.max(ans, removeCnt);
	}

	static void bfs(int y, int x, int len, int[][] copyMap, boolean[][] visited) {
		if(copyMap[y][x] == 1) {
			removeList.add(new int[] {y, x});
			return ;
		}
		
		int[] dy = { 0, -1, 0 };
		int[] dx = { -1, 0, 1 };
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x, 1 });
		
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

				if (ny < 0 || ny > N - 1 || nx < 0 || nx > M - 1 || visited[ny][nx])
					continue;
				if(copyMap[ny][nx] == 1) {
					removeList.add(new int[] {ny, nx});
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
				if (map[i][j] == 1) {
					++remainCnt;
				}
			}
		}
		removeList = new ArrayList<>();
		order = new int[3];
	}
}
