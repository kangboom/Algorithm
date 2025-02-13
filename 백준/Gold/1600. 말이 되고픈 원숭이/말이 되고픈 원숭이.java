import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;
	static int K;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] hx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		input();
		answer = bfs(0, 0, 0, 0);

		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
			return ;
		}
		System.out.println(answer);
	}
	
	static int bfs(int y, int x, int dist, int use) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {y, x, 0, 0});
		map[y][x] = 0;
		visited[y][x][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			int cdist = cur[2];
			int cuse = cur[3];
			
			if(cy == N-1 && cx == M-1) {
				return cdist;
			}
			
			if(cuse < K) { // 말 탐색
				for(int i=0; i<8; i++) {
					int ny = cy + hy[i];
					int nx = cx + hx[i];
					int ndist = cdist + 1;
					int nuse = cuse + 1;
					
	
					if(isOut(ny, nx)) continue;
					if(visited[ny][nx][nuse]) continue;
					
					visited[ny][nx][nuse] = true;
					q.offer(new int[] {ny, nx, ndist, nuse});
				}
				
			}
			
			// 일반 탐색
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				int ndist = cdist + 1;
				int nuse = cuse;
				
				if(isOut(ny, nx)) continue;
				if(visited[ny][nx][nuse]) continue;
				
				visited[ny][nx][nuse] = true;
				q.offer(new int[] {ny, nx, ndist, nuse});
			}
			
		}
		return answer;
	}
	static boolean isOut(int ny, int nx) {
		return ny<0 || ny>N-1 || nx<0 || nx>M-1 || map[ny][nx] == 1;
	}
	
	static void input() throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M][K+1];
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
	}
}
