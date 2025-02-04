import java.util.*;
import java.io.*;

public class Main {
	static int M;
	static int N;
	static int R;
	static int[][] arr;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] count ;
	public static void main(String[] args) throws IOException {
		input();
		getCount();
		
		int depth = Math.min(M, N) / 2;
		for(int i=0; i<depth; i++) {
			rotate(i, R % count[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void getCount() {
		int depth = Math.min(M, N) / 2; 
		for(int d=0; d<depth; d++) {
			int cnt = 0;
			for(int i=d; i<M-d; i++) {
				cnt++;
			}
			for(int i=d+1; i<N-1-d; i++) {
				cnt++;
			}
			count[d] = cnt*2; 
		}
		
	}
	public static void rotate(int depth, int rotate) {
		
		for(int r=0; r<rotate; r++) {
			
			int tmp = arr[depth][depth]; // 첫 번째 인자 저장
			
			// 위
			for(int i=depth+1; i<M-depth; i++) {
				arr[depth][i-1] = arr[depth][i];
			}
			
			// 오른쪽
			for(int i=depth+1; i<N-depth; i++) {
				arr[i-1][M-1-depth] = arr[i][M-1-depth];
			}
			
			// 아래
			for(int i=M-2-depth; i>=depth; i--) {
				arr[N-1-depth][i+1] = arr[N-1-depth][i];
			}
			// 왼쪽
			for(int i=N-2-depth; i>depth; i--) {
				arr[i+1][depth] = arr[i][depth];
			}
			arr[depth+1][depth] = tmp;
		}
	}

	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = new int[Math.min(N, M)/ 2];
	}
}
