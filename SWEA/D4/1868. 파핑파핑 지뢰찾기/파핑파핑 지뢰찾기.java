import java.io.*;
import java.util.*;

public class Solution {

	// 12시부터 시계방향 
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int answer;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			
			input();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '.') { 
						if(isClear(i,j)) {
							click(i, j);
							answer++;
						}
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '.') answer++;
				}
			}
			
			System.out.println("#" + (t+1) + " " + answer);
		}
		
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		answer = 0;
	}
	
	static void click(int y, int x) {
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {y, x});
		map[y][x] = 'c'; // 클릭 표시하기
		
		while(!stack.isEmpty()) {
			int[] cur = stack.pop();
			int cy = cur[0];
			int cx = cur[1];
			
			for(int i=0; i<8; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue; // map 벗어난 경우 pass
				if(map[ny][nx] == 'c') continue; // 이미 클린 된경우 건너 뛰기
				
				map[ny][nx] = 'c'; // 클릭 표시하기
				if(isClear(ny, nx)) {
					stack.push(new int[] {ny, nx});
				}
				
			}
		}
	}
	
	static boolean isClear(int y, int x) {
		
		for(int i=0; i<8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
			if(map[ny][nx] == '*') return false;
		}
		return true;
	}
	
}