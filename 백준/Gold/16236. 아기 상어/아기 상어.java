import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int time;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static StringTokenizer st;
	static int[][] map;
	static int startY;
	static int startX;

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.print(time);
	}

	static void solve() {
		int[] dy = {-1, 0, 0, 1};
		int[] dx = {0, -1, 1, 0};
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(startY, startX, 2, 0, 0));
		visited[startY][startX] = true;
		
		while(!pq.isEmpty()) {
			Info now = pq.poll();
			
			if(map[now.y][now.x] != 0 && map[now.y][now.x] < now.size) { // 먹기
				map[now.y][now.x] = 0; 
				if(++now.eat == now.size) {
					++now.size;
					now.eat = 0;
				}
				// 시간 올리기
				time += now.dist;
				pq.clear();
				visited = new boolean[N][N]; // 방문 배열 초기화
				pq.offer(new Info(now.y, now.x, now.size, now.eat, 0));
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1 || visited[ny][nx]) continue;
				if(map[ny][nx] > now.size) continue;
				
				visited[ny][nx] = true;
				pq.offer(new Info(ny, nx, now.size, now.eat, now.dist+1));
			}
		}
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					startY = i;
					startX = j;
					map[i][j] = 0;
				}
			}
		}
	}
}

class Info implements Comparable<Info> {
	int y, x, size, eat, dist;

	Info(int y, int x, int size, int eat, int dist) {
		this.y = y;
		this.x = x;
		this.size = size;
		this.eat = eat;
		this.dist = dist;
	}

	@Override
	public int compareTo(Info o) {
		if (this.dist == o.dist) {
			if (this.y == o.y) {
				return this.x - o.x;
			} else {
				return this.y - o.y;
			}
		} else {
			return this.dist - o.dist;
		}
	}
}
