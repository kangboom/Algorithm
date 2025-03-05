import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static char oper;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input();
			solve();
			
			sb.append('#').append(t).append('\n');
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
	
	static void solve() {
		int d = getDirection(oper);
		if(d==0) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					move(i, j, d);
				}
			}
		} else if(d==1) {
			for(int i=N-1; i>=0; i--) {
				for(int j=0; j<N; j++) {
					move(i, j, d);
				}
			}
		} else if(d==2) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					move(i, j, d);
				}
			}
		} else {
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					move(i, j, d);
				}
			}
		}
	}
	
	static void move(int y, int x, int d) {
		while(true) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) return;
			if(map[ny][nx] == 0) {
				map[ny][nx] = map[y][x];
				map[y][x] = 0;
				
				// 초기화
				y = ny;
				x = nx;
			} else {
				// 합치기
				if(map[ny][nx] == map[y][x]) {
					if(visited[ny][nx]) return ; // 한번 합친 경우
					
					map[ny][nx] += map[y][x];
					map[y][x] = 0;
					visited[ny][nx] = true;
					return ;
				} else { // 다른 경우 그냥 종료
					return ; 
				}
			}
		}
	}
	
	static int getDirection(char op) {
		switch(op) {
		case 'u':
			return 0;
		case 'd':
			return 1;
		case 'l':
			return 2;
		case 'r':
			return 3;
		default:
			return -1;
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		oper = st.nextToken().charAt(0);
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}

}
