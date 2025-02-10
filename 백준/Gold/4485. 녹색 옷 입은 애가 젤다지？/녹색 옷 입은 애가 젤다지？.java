import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[][] costMap;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int N;
	static boolean isEnd;
	
	public static void main(String[] args) throws IOException{
		int i=1;
		while(true) {
			input();
			if(isEnd) return;
			
			bfs();
			System.out.println("Problem " + (i++) + ": " + costMap[N-1][N-1]);
		}
	}

	static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2)-> {
			return o1[2] - o2[2];
		});
		
		int startY = 0;
		int startX = 0;
		int cost = map[startY][startX];
		
		costMap[startY][startX] = cost;
		q.offer(new int[] {startY, startX, cost});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			int ccost = cur[2];
			
			if(costMap[cy][cx] < ccost) continue;
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;

				int ncost = ccost + map[ny][nx];
				if(costMap[ny][nx] <= ncost) continue;
				
				costMap[ny][nx] = ncost;
				
				if(ny == N-1 && nx == N-1) return ;
				q.offer(new int[] {ny, nx, ncost});
			}
		}
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		if(N==0) {
			isEnd = true;
			return ;
		}
		
		map = new int[N][N];
		costMap = new int[N][N];
	
		for(int i=0; i<N; i++) {
			Arrays.fill(costMap[i], Integer.MAX_VALUE);
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
