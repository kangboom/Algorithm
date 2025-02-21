import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 
 *	다익스트라 응용 문제이다.
 *	두 개의 노드를 필수 적으로 거쳐가야되므로
 *  (1) 1 -> node1 -> node2 -> N 인 경우와 (2) 1 -> node2 -> node1 -> N
 *  두 가지 경우 중 최솟 값을 찾아주면 된다.
 *  
 *  거리 배열을 초기화 할 때는 INF 값을 2억으로 정해주었다.
 *  사실 INF 값은 dist 배열에 들어갈 수 있는 값이 799 * 1000이므로 INF 값은 800 * 1000이 될 수 있지만, 
 *  경로가 불가능한지 확인 할 때 3지점을 더해서 INF과 비교하므로 800 * 1000 * 3만 넘으면 된다.
 *  
 *  처음에 로직 흐름이 헷갈려서 다익스트라 알고리즘에서 dist배열을 갱신할 때
 *  next 노드가 target이면 바로 return하게 해줬는데
 *  dist를 갱신할 때가 아니라 q에서 나올 때 확인해줘야된다. 
 *  (먼저 갱신된게 최솟값이라는 건 보장 못함, 하지만 먼저 나오는 건 우선순위 큐에 의해 보장) 
 *  
 */
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
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(now.v == target) {
				return dist[now.v];
			}

			if(visited[now.v]) continue;
			visited[now.v] = true;
						
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
