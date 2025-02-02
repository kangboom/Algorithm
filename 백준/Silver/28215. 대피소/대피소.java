import java.util.*;
import java.io.*;

public class Main {
	
	static int K;
	static int N;
	static int[][] houses;
	static int answer = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] choose;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		input();
		if(K==N) {
			System.out.println(0);
			return ;
		}
		solve(0,0);
		System.out.println(answer);
	}
	
	static void solve(int start, int depth) {
		if(depth == K) {
			int tmpMax = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				if(visited[i]) continue; // 해당 집이 대피소면 패스
				
				int tmpMin = Integer.MAX_VALUE; // 현재 집에서 가장 가까운 대피소까지 최소값
				for(int j=0; j<K; j++) { // 대피소 순회해서 가장 최소인 거리 찾기
					tmpMin = Math.min(tmpMin, getDistance(houses[choose[j]][0], houses[choose[j]][1], houses[i][0], houses[i][1]));
				}
				
				tmpMax = Math.max(tmpMax, tmpMin); // 집에서부터 가장 가까운 대피소까지의 거리가 가장 멀 때 거리 구하기 
			}
			answer = Math.min(answer, tmpMax);
			return;
		}
		
		for(int i=start; i<N; i++) {
			choose[depth] = i;
			visited[i] = true;
			solve(i+1, depth+1);
			visited[i] = false;
		}
	}
	
	static int getDistance(int destX, int destY, int x, int y) {
		return Math.abs(destX - x) + Math.abs(destY-y);
	}
	
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		houses = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			houses[i][0] = Integer.parseInt(st.nextToken());
			houses[i][1] = Integer.parseInt(st.nextToken());
		}
		
		choose = new int[K];
		visited = new boolean[N];
	}
}
