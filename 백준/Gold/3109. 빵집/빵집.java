import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R;
	static int C;
	static char[][] map;
	static int answer;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1};
	static int[] dx = {1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		input();
		for(int i=0; i<R; i++) {
			if(solve(i, 0)) ++answer;
		}
		System.out.println(answer);
	}
	
	static boolean solve(int y, int x) {
		if(x == C-1) {
			return true;
		}
		
		for(int i=0; i<3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0 || ny>R-1 || nx<0 || nx>C-1 || visited[ny][nx]) continue;
			if(map[ny][nx] == 'x') continue;
			
			visited[ny][nx] = true;
			if(solve(ny, nx)) {
				return true;
			}
		}
		
		return false;
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
	
		visited = new boolean[R][C];
		answer = 0;
	}
}
