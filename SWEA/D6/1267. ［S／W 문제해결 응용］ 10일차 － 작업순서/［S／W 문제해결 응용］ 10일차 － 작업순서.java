import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int V;
	static int E;
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] graph;
	static int[] degree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		for (int t = 1; t <= 10; t++) {
			input();
			sb.append("#").append(t).append(" ");
			solve();
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static void solve() {
		Queue<Integer> q = new LinkedList<>(); 
		for(int i=1; i<=V; i++) {
			if(degree[i] == 0) {
				sb.append(i).append(" ");
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : graph[now]) {
				if(--degree[next] == 0) {
					sb.append(next).append(" ");
					q.offer(next);
				}
			}
		}
		
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
//		System.out.println("V " + V + ", E " + E);
		graph = new List[V+1];
		degree = new int[V+1];
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=E; i++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			++degree[to];
		}
	}
}
