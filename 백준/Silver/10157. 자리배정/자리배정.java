import java.util.*;
import java.io.*;

public class Main {
	static int R;
	static int C;
	static int K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		input();
		if(K > R*C) {
			System.out.println(0);
			return ;
		}
		
		solve(1, 1);
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j] == K) {
					System.out.println(j + " " + i);
					return ;
				}
			}
		}
	}

	static void solve(int y, int x) {
		int cy = y;
		int cx = x;
		map[cy][cx] = 1;
		visited[cy][cx] = true;

		int direc = 0;
		int i =1;
		while(i<R*C) {
			int ny = cy + dy[direc];
			int nx = cx + dx[direc];
			
			if(ny<1 || ny>R || ny<1 || nx>C) {
				direc = (direc+1) % 4;
				continue;
			}
			if(visited[ny][nx]) {
				direc = (direc+1) % 4;
				continue;
			}
			
			map[ny][nx] = ++i;
			visited[ny][nx] = true;
			cy = ny;
			cx = nx;
			
		}
	}
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		map = new int[R+1][C+1];
		visited = new boolean[R+1][C+1];
	}

}
