import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int max;
	static int cnt;
	static int tmp;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		input();
		cnt = 0;
		max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) continue;
				if(map[i][j] == 1) {
					tmp = 0;
					dfs(i, j);
					++cnt;
					max = Math.max(max, tmp);
				}
			}
		}
		System.out.println(cnt);
		System.out.println(max);
	}
	
	static void dfs(int y, int x) {
		visited[y][x] = true;
		++tmp;
		
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
			if(visited[ny][nx]) continue;
			if(map[ny][nx] == 0) continue;
			
			dfs(ny, nx);
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
