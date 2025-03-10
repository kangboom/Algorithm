import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int ans;
	static Graph[] graph;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static final int INF = 100;
	static List<int[]> list;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		input();
		
		// 숫자 붙이기
		int number = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				dfs(i, j, number);
				++number;
			}
		}
		
		parent = new int[number];
		for(int i=1; i<number; i++) {
			parent[i] = i;
		}
		
		// 간선 구하기
		for(int i=1; i<number; i++) {
			int[] distance = new int[number];
			Arrays.fill(distance, INF);
			for(Graph tmp = graph[i]; tmp != null ; tmp = tmp.next) {
				getEdge(tmp.y, tmp.x, i, distance);
			}
			
			for(int j=1; j<number; j++) {
				if(distance[j] != INF) {
					list.add(new int[] {i, j, distance[j]});
				}
			}
		}
		
		Collections.sort(list, (o1, o2) ->{
			return o1[2] - o2[2];
		});
		
		int cnt = 0;
		int ans = 0;
		for(int i=0; i<list.size(); i++) {
			int a = list.get(i)[0];
			int b = list.get(i)[1];
			
			if(find(a) != find(b)) {
				union(a, b);
				++cnt;
				ans += list.get(i)[2];
			}
		}
		
		if(cnt == number-2) {
			System.out.println(ans);
		} else {
			System.out.println(-1);
		}
	}

	static void getEdge(int y, int x, int start, int[] distance) {
		for(int d=0; d<4; d++) {
			int len = 0;
			int target = -1;
			int cy = y;
			int cx = x;
			while(true) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(isOut(ny, nx)) break;
				if(0< map[ny][nx] && map[ny][nx] <= start) break;
				if(map[ny][nx] > start) {
					target = map[ny][nx];
					break;
				}
				
				cy = ny;
				cx = nx;
				++len;
			}
			
			if(target != -1 && len>=2) {
				distance[target] = Math.min(distance[target], len);
			}
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		graph = new Graph[7];
		visited = new boolean[N][M];
		list = new ArrayList<>();
	}
	
	static void dfs(int y, int x, int number) {
		visited[y][x] = true;
		map[y][x] = number;
		graph[number] = new Graph(y, x, graph[number]);
		
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(isOut(ny, nx)) continue;
			if(map[ny][nx] == 0 || visited[ny][nx]) continue;
			dfs(ny, nx, number);
		}
	}
	
	static boolean isOut(int y, int x) {
		return y<0 || y>N-1 || x<0 || x>M-1;
	}
	
	static int find(int x) {
		if(parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA < parentB) {
			parent[parentB] = parentA;
		} else {
			parent[parentA] = parentB;
		}
	}
}

class Graph{
	int y, x;
	Graph next;
	
	Graph(int y, int x, Graph next){
		this.y = y;
		this.x = x;
		this.next = next;
	}
}
