import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int E;
	static int S;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Node>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
		for(int i=1; i<=V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
				continue;
			}
			sb.append(dist[i]).append("\n");
		}
		System.out.print(sb);
	}
	
	static void solve() {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S,0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(dist[now.v] < now.cost) continue;
			
			for(int i=0; i<graph[now.v].size(); i++) {
				Node next = graph[now.v].get(i);
				int cost = now.cost + next.cost;
				
				if(dist[next.v] > cost ) {
					dist[next.v] = cost;
					pq.offer(new Node(next.v, cost));
				}
			}
			
		}
		
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[V+1];
		graph = new List[V+1];
		for(int i=1; i<=V; i++) {
		 graph[i] = new ArrayList<>();	
		}
		
		S = Integer.parseInt(br.readLine());
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, c));
		}
	}
	
	static class Node implements Comparable<Node>{
		int v, cost;
		
		Node(int v, int cost){
			this.v = v;
			this.cost =cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
