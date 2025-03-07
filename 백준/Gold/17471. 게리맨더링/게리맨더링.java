import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static boolean[] select;
	static boolean[] visited;
	static int[] population;
	static Link[] graph;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		input();
		for(int i=1; i<=N/2; i++) {
			Arrays.fill(select,  false);
			comb(1, 1, i);
		}
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
			return ;
		}
		System.out.println(answer);
	}
	
	static void dfs(int start, boolean group) { // 탐색
		visited[start] = true;
		
		for(Link tmp = graph[start]; tmp != null; tmp = tmp.next) {
			int next = tmp.to;
			
			if(select[next] != group) continue;
			if(visited[next]) continue;
			
			dfs(next, group);
		}
	}
	
	static void comb(int start, int depth, int r) { // 조합 뽑기
		if(depth == r+1) {
			if(isConnected()) {
				int sum1 = 0;
				int sum2 = 0;
				for(int i=1; i<=N; i++) {
					if(select[i]) {
						sum1 += population[i];
					} else {
						sum2 += population[i];
					}
				}
				answer = Math.min(answer, Math.abs(sum1-sum2));
			};
			return ;
		}
		
		for(int i=start; i<=N; i++) {
			select[i] = true;
			comb(i+1, depth+1, r);
			select[i] = false;
		}
		
	}
	
	static boolean isConnected() {
		int some=0;
		int other=0;
		
		for(int i=1; i<=N; i++) {
			if(select[i]) {
				some = i;
			} 
		}
		
		for(int i=1; i<=N; i++) {
			if(!select[i]) {
				other = i;
			} 
		}
		
		Arrays.fill(visited, false);
		dfs(some, true);
		dfs(other, false);
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				return false;
			}
		}
		return true;

	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		select = new boolean[N+1];
		visited = new boolean[N+1];
		population = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new Link[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				graph[i] = new Link(Integer.parseInt(st.nextToken()), graph[i]);
			}
		}
		
		answer = Integer.MAX_VALUE;
	}
}

class Link {
	int to;
	Link next;
	
	Link(int to, Link next){
		this.to = to;
		this.next = next;
	}
}
