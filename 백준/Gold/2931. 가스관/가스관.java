import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static int N;
	static int M;
	static int[] start;
	static int[] end;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] ansL;
	static char ansOp;
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ansL[0] + " " + ansL[1] + " " + ansOp);
	}
	
	static void solve() {
		Queue<Info> q = new LinkedList<>();
		for(int i=0; i<4; i++) {
			int ny = start[0] + dy[i];
			int nx = start[1] + dx[i];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue;
			if(map[ny][nx] != '.' && map[ny][nx] != 'Z') {
				q.offer(new Info(ny, nx, i));
				break;
			}
		}
		
		while(!q.isEmpty()) {
			Info now = q.poll();
			int d = getDirec(now.preD, map[now.y][now.x]);
			
			int ny = now.y + dy[d];
			int nx = now.x + dx[d];
			
			if(map[ny][nx] == '.') {
				char[] chars = getOper(d);
				for(int i=0; i<chars.length; i++) {
					map[ny][nx] = chars[i];
					if(check(ny, nx, d)) {
						ansL[0] = ny+1;
						ansL[1] = nx+1;
						ansOp = chars[i];
						return;
					}
				}
				return;
			}
			
			q.offer(new Info(ny, nx, d));
		}
	}
	
	static char[] getOper(int d) {
		if(d==0) {
			return new char[] {'|', '+' , '1', '4'};
		}
		else if(d==1) {
			return new char[] {'|', '+' , '2', '3'};
		}
		else if(d==2) {
			return new char[] {'-', '+' , '1', '2'};
		}
		else {
			return new char[] {'-', '+' , '3', '4'};
		}
	}
	
	static boolean isPossible(int d, char ch) {
		if(d==0) {
			return ch == '|' || ch == '+' || ch == '1' || ch == '4';
		}
		else if(d==1) {
			return ch == '|' || ch ==  '+' ||  ch == '2' || ch ==  '3';
		}
		else if(d==2) {
			return ch == '-' || ch ==  '+' || ch ==  '1' || ch ==  '2';
		}
		else {
			return ch == '-' || ch ==  '+' || ch ==  '3' || ch ==  '4';
		}
	}
	
	
	static boolean check(int y, int x, int preD) {
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(y, x, preD));
		
		while(!q.isEmpty()) {
			Info now = q.poll();
			
			int d = getDirec(now.preD, map[now.y][now.x]);
			int ny = now.y + dy[d];
			int nx = now.x + dx[d];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>M-1) {
				return false;
			}
			
			if(map[ny][nx] == 'Z') return true;
			if(map[ny][nx] == '.') {
				return false;
			}
			
			if(!isPossible(d, map[ny][nx])) return false;
			
			q.offer(new Info(ny, nx, d));
		}
		return false;
	}
	
	static int getDirec(int preD, char op) {
		switch(op) {
		case '|':
			return preD;
		case '-':
			return preD;
		case '+':
			return preD;
		case '1':
			if(preD==0) {
				return 3;
			} else if(preD == 2) {
				return 1;
			}
		case '2':
			if(preD == 1) {
				return 3;
			} else if(preD == 2) {
				return 0;
			}
		case '3':
			if(preD == 1) {
				return 2;
			} else if(preD == 3) {
				return 0;
			}
		case '4':
			if(preD == 0) {
				return 2;
			} else if(preD == 3) {
				return 1;
			}
		}
		return 0;
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		start = new int[2];
		end = new int[2];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'M') {
					start[0] = i;
					start[1] = j;
				}
				if(map[i][j] == 'Z') {
					end[0] = i;
					end[1] = j;
				}
			}
		}
		
		ansL = new int[2];
	}
	
	static class Info {
		int y,x,preD;
		
		Info(int y, int x, int preD){
			this.y = y;
			this.x = x;
			this.preD = preD;
		}
		
	}
}
