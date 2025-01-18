import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		input();
		solve(0,0);
		
		System.out.println(map[N-1][M-1]);
	}
	
	static void solve(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{y, x});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue; // map을 벗어난 경우 패스
				if(map[ny][nx] == 0 || map[ny][nx] != 1) continue; // 갈 수 없거나 방문한 경우는 패스
				
				map[ny][nx] = map[cy][cx] + 1;
				q.add(new int[] {ny, nx});
				
			
			}
		}
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
	}

}
