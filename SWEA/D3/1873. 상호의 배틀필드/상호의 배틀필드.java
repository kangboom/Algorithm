import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int direction;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int M;
	static char[][] map;
	static String oper;
	static int P;
	static StringBuilder sb = new StringBuilder();
	static int[] location;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input();
			play();
			sb.append("#").append(t).append(" ");
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}

	static void play() {
		for (int i = 0; i < oper.length(); i++) {
			char op = oper.charAt(i);
			
			if(op == 'S') {
				shoot();
			} else { // 이동 명령어
				move(op);
			}
		}
	}

	static void shoot() {
		int y = location[0];
		int x = location[1];
		while(0<=y && y <=N-1 && 0<=x && x<=M-1) {
			int ny = y + dy[direction];
			int nx = x + dx[direction];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>M-1) return ;
			if(map[ny][nx] == '*') {
				map[ny][nx] = '.';
				return ;
			} else if(map[ny][nx] == '#') {
				return ;
			}
			
			y = ny;
			x = nx;
			
		}
	}
	static void move(char oper) {
		direction = getDirection(oper);
		
		int ny;
		int nx;
		
		ny = location[0] + dy[direction];
		nx = location[1] + dx[direction];
		
		char tmp;
		if(direction == 0) {
			tmp = '^';
		} else if(direction == 1) {
			tmp = 'v';
		} else if(direction == 2) {
			tmp = '<';
		} else {
			tmp = '>';
		}
		
		map[location[0]][location[1]] = tmp;
		if(ny<0 || ny>N-1 || nx<0 || nx>M-1) return ;
		if(map[ny][nx] == '.') { // 평지만 이동 가능			
			map[location[0]][location[1]] = '.';
			map[ny][nx] = tmp;
			location[0] = ny;
			location[1] = nx;
			
		}
	}
	
	static int getDirection(char oper) {
		switch(oper) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'L':
			return 2;
		case 'R':
			return 3;
		default:
			return -1;
		}
	}

	static int initialSetting(char direct) {
		switch (direct) {
		case '^':
			return 0;
		case 'v':
			return 1;
		case '<':
			return 2;
		case '>':
			return 3;
		default:
			return -1;
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		location = new int[2];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
					location[0] = i;
					location[1] = j;
					direction = initialSetting(map[i][j]);
				}
			}
		}

		P = Integer.parseInt(br.readLine());
		oper = br.readLine();
	}

}
