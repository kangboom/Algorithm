import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] board = new int[5][5];
	static int[][] answer = new int[5][5];
	static StringTokenizer st;
	static int result; 
	static boolean[][] visited = new boolean[5][5];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				result++;
				int target = answer[i][j];
				checkTarget(target);
				if(isBingo()) {
					System.out.println(result);
					return ;
				}
			}
		}
	}

	static void checkTarget(int target) {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(board[i][j] == target) {
					visited[i][j] = true;
				}
			}
		}
	}
	
	static boolean isBingo() {
		int cnt = 0;
		
		// 가로
		for(int i=0; i<5; i++) {
			int tmp = 0;
			for(int j=0; j<5; j++) {
				if(visited[i][j]) tmp++;
			}
			if(tmp == 5) cnt++;
		}
		
		// 세로
		for(int i=0; i<5; i++) {
			int tmp = 0;
			for(int j=0; j<5; j++) {
				if(visited[j][i]) tmp++;
			}
			if(tmp == 5) cnt++;
		}
		
		// 대각
		int tmp = 0;
		for(int i=0; i<5; i++) {
			if(visited[i][i]) tmp++; 
		}
		if(tmp == 5) cnt++;

		tmp = 0;
		for(int i=0; i<5; i++) {
			if(visited[i][4-i]) tmp++; 
		}
		if(tmp == 5) cnt++;
		
		if(cnt >= 3) {
			return true;
		}
		
		return false;
	}
	
	static void input() throws IOException {
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				answer[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
}
