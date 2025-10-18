import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dist;
	static int v;
	static int e;
	static int INF = 1000000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		dist = new int[v+1][v+1]; // dist 배열 생성
		
		for(int i=1; i<=v; i++) {
			Arrays.fill(dist[i], INF);
		}
		for(int i=1; i<=v; i++) {
			dist[i][i] = 0; // 자기 자신으로 가는 거리는 0
		}
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			dist[from][to] = cost;
		}
		
		// 플로이드-워셜 알고리즘 수행 
		for(int k=1; k<=v; k++) {
			for(int i=1; i<=v; i++) {
				for(int j=1; j<=v; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		// 사이클 확인 a -> b , b -> a 가능하면 사이클
		int ans = INF;
		for(int i=1; i<=v; i++) {
			for(int j=i+1; j<=v; j++) {
					if(dist[i][j] != INF && dist[j][i] != INF) {
						ans = Math.min(ans, dist[i][j] + dist[j][i]);
					}
			}
		}
		
		if(ans == INF) {
			ans = -1;
		}
		System.out.println(ans);
		
	}
}
