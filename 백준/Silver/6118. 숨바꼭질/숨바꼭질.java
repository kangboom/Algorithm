import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
		StringBuilder sb = new StringBuilder();
		sb.append(answerNum).append(" ").append(max).append(" ").append(answerCnt);
		System.out.println(sb);
	}
	
	static void bfs() {
		boolean[] visited = new boolean[N+1];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {1, 0});
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(max < now[1]) {
				max = now[1];
				answerNum = now[0];
				answerCnt =1;
			} else if(max == now[1]) {
				answerNum = Math.min(answerNum, now[0]);
				++answerCnt;
			}
			
			for(int next : graph[now[0]]) {
				if(visited[next]) continue;
				visited[next] = true;
				q.offer(new int[] {next, now[1]+1});
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