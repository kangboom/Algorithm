import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static List<Integer>[] graph;
	static int[] degree;
	static int[] time;
	static int[] clear;
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
		int answer = 0;
		for(int i=1; i<=N; i++) {
			answer = Math.max(answer, clear[i]);
		}
		System.out.println(answer);
	}
	
	static void solve() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(degree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cv = q.poll();
			
			for(int i=0; i<graph[cv].size(); i++) {
				int nv = graph[cv].get(i);
				
				--degree[nv];
				clear[nv] = Math.max(clear[nv], clear[cv]+time[nv]);
				
				if(degree[nv]==0) {
					q.offer(nv);
				}
			}
		}
		
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		degree = new int[N+1];
		time = new int[N+1];
		clear = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;
			clear[i] = t;
			
			int cnt = Integer.parseInt(st.nextToken());
			degree[i] = cnt;
			
			for(int j=0; j<cnt; j++) {
				int pre = Integer.parseInt(st.nextToken());
				graph[pre].add(i);
			}
		}
	}

}
