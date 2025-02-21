import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int E;
	static List<Edge>[] graph;
	static int node1;
	static int node2;
	static final int INF = 200000000;
	
	public static void main(String[] args) throws IOException {
		input();
		
		int mid = dijkstra(node1, node2);
		int answer1 = dijkstra(1, node1) + mid + dijkstra(node2, N);
		int answer2 = dijkstra(1, node2) + mid + dijkstra(node1, N);
		
		if(answer1 >= INF && answer2 >= INF) {
			System.out.println(-1);
			return ;
		}
		System.out.println(Math.min(answer1, answer2));
	}
	
	static int dijkstra(int start, int target) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(now.v == target) {
				return dist[now.v];
			}

			if(dist[now.v] < now.cost) continue;
						
			for(int i=0; i<graph[now.v].size(); i++) {
				Edge next = graph[now.v].get(i);
				
				if(dist[next.v] > now.cost + next.cost) {
					dist[next.v] = now.cost + next.cost;
				
					pq.offer(new Edge(next.v, dist[next.v]));
				}
			}
		}
		return dist[target];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		node1 = Integer.parseInt(st.nextToken());
		node2 = Integer.parseInt(st.nextToken());
	}
}

class Edge implements Comparable<Edge>{
	int v;
	int cost;
	
	Edge(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}
