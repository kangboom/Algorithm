import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static StringTokenizer st;
	static int maxNum;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxNum = Math.max(maxNum, map[i][j]);
			}
		}
		
		int answer = 0;
		for(int s=0; s<=maxNum; s++) {
			visited = new boolean[N][N];
			int tmp = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] <= s ) continue;
					if(visited[i][j]) continue;
					++tmp;
					dfs(i,j, s);
				}
			}
			answer = Math.max(answer, tmp);
			
		}
		System.out.println(answer);
	}

	static void dfs(int y, int x, int s) {
	
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1 || visited[ny][nx]) continue;
			if(map[ny][nx] <= s) continue;
			visited[ny][nx] = true;
			dfs(ny, nx, s);
		}
	}
}
