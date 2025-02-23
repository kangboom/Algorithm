import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer>[] graph;
	static int max;
	static int answerNum;
	static int answerCnt;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		input();
		bfs();
		System.out.println(answerNum + " " + max + " " + answerCnt);
	}
	
	static void bfs() {
		boolean[] visited = new boolean[N+1];
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(1, 0));
		visited[1] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(max < now.dist) {
				max = now.dist;
				answerNum = now.v;
				answerCnt =1;
			} else if(max == now.dist) {
				answerNum = Math.min(answerNum, now.v);
				++answerCnt;
			}
			
			for(int i=0; i<graph[now.v].size(); i++) {
				int next = graph[now.v].get(i);
				
				if(visited[next]) continue;
				
				visited[next] = true;
				q.offer(new Node(next, now.dist+1));
			}
		}
		
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
	}
}

class Node {
	int v;
	int dist;
	
	Node(int v, int dist) {
		this.v = v;
		this.dist = dist;
	}
}
