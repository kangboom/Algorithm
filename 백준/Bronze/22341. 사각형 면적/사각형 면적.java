import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int cnt;
	static int[][] location;
	static int startY = 0;
	static int endY;
	static int startX = 0;
	static int endX;
	
	public static void main(String[] args) throws IOException{
		input();
		for(int i=0; i<cnt; i++) {
			cut(location[i][0], location[i][1]);
		}
		System.out.println((endY-startY) * (endX-startX));
	}
	
	static void cut(int y, int x) {
		
		if(y<startY || y>endY || x<startX || x>endX) {
			return;
		}

		int row = (y-startY) * (endX-startX);
		int col = (endY-startY) * (x-startX);
		
		if(row >= col) {
			endY = y;
		} else {
			endX = x;
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		endY = N;
		endX = N;
		
		location = new int[cnt][2];
		for(int i=0; i<cnt; i++) {
			st = new StringTokenizer(br.readLine());
			location[i][0] = Integer.parseInt(st.nextToken());
			location[i][1] = Integer.parseInt(st.nextToken());
		}
	}

}
