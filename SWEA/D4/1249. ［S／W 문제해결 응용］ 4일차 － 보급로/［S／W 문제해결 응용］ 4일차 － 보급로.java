import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static int[][] map;
	static int[][] sum;
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			input();
			bfs();
			sb.append("#" + t+ " " + sum[N-1][N-1] + "\n");
		}
		System.out.print(sb);
	}
	
	static void bfs() {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {0, 0});
		sum[0][0] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny >N-1 || nx<0 || nx>N-1) continue;
				if(sum[ny][nx] <= sum[cy][cx] + map[ny][nx]) continue;
				
				sum[ny][nx] = sum[cy][cx] + map[ny][nx];
				q.offer(new int[] {ny, nx});
			}
		}
		
	}
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		sum = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				sum[i][j] = Integer.MAX_VALUE;
			}
		}
	}

}
