import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int L;
	static int R;
	static int X;
	
	static int[] level;
	static boolean[] visited;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        level = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[N];
        select(0,0);
        
        System.out.println(answer);
	}
	
	static void select(int index, int cnt) {
		if(index == N ) {
			if(cnt < 2) return;
			
			int sum = 0;
			int min_level = Integer.MAX_VALUE;
			int max_level = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				if(visited[i]) {
					sum += level[i];
					min_level = Math.min(min_level, level[i]);
					max_level = Math.max(max_level, level[i]);
				}
			}
			
			if(L <= sum && sum <= R && max_level - min_level >= X) {
				answer++;
			}
			return ;
		}
		
		// 문제 선택 O
		visited[index] = true;
		select(index+1, cnt+1);
		visited[index] = false;
		
		// 문제 선택 X
		select(index+1, cnt);
	}

}
/*
 * 문제 N개 
 * i번째 문제 난이도 Ai
 * 1) L <= 난이도의 합 <= R
 * 2) 가장 어려운 문제 - 가장 쉬운 문제 >= X
 * 3) 2 <= 문제 갯수 
 */
