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
	static List<Integer>[] graph;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		input();
		for(int i=1; i<=N/2; i++) {
			Arrays.fill(select,  false);
			Arrays.fill(visited, false);
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
		
		for(int i=0; i<graph[start].size(); i++) {
			int next = graph[start].get(i);
			
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
		int someCnt = 0;
		int other=0;
		int otherCnt = 0;
		for(int i=1; i<=N; i++) {
			if(select[i]) {
				some = i;
				someCnt++;
			} else {
				other = i;
				otherCnt++;
			}
		}
		Arrays.fill(visited, false);
		dfs(some, true);

		int tmp = 0;
		for(int i=1; i<=N; i++) {
			if(visited[i]) {
				tmp++;
			}
		}
		if(someCnt!=tmp) return false;
		
		Arrays.fill(visited, false);
		dfs(other, false);
		
		tmp = 0;
		for(int i=1; i<=N; i++) {
			if(visited[i]) {
				tmp++;
			}
		}
		
		if(otherCnt != tmp) return false;
		
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
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
			for(int j=0; j<cnt; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		answer = Integer.MAX_VALUE;
	}
}
