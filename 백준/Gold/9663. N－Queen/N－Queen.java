import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static boolean[][] visited;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			visited[0][i] = true;
			solve(1);
			visited[0][i] = false;
		}
		System.out.println(answer);
	}
	
	static void solve(int depth) {
		if(depth == N) {
			++answer;
			return ;
		}
		
		for(int i=0; i<N; i++) {
			if(!isPossilbe(depth, i)) continue;
			
			visited[depth][i] = true;
			solve(depth+1);
			visited[depth][i] = false;
		}
	}
	
	static boolean isPossilbe(int y, int x) {
		
		// 행 탐색
		int i = y-1;
		while(i>=0) {
			if(visited[i--][x]) return false;
		}

		// 왼쪽 대각선
		i = y-1;
		int l = x-1;
		while(i>=0 && l>=0) {
			if(visited[i--][l--]) return false;
		}
		
		// 오른쪽 대각선
		i = y-1;
		int r = x+1;
		while(i>=0 && r<=N-1) {
			if(visited[i--][r++]) return false;
		}
		
		return true;
	}

}
