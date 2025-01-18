import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int H;
	static int[][][] map;
	static Queue<int[]> q;
	static int answer = 0;
	
	// 상 하 좌 우 위 아래
	static int[] dy = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	
	public static void main(String[] args)throws IOException{
		
		input();
		bfs();
		
		// 탐색을 하고 안 익은 토마토가 있는 지 검색
		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[h][i][j] == 0) { // 안 익은 토마토가 있으면 -1출력하고 바로 종료
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		System.out.println(answer); // 답 출력
	}

	static void bfs() { // bfs의 깊이 == day
		while(!q.isEmpty()) { // 큐가 빌 때까지 탐색
			
			// 현재 값
			int[] cur = q.poll();
			int cz = cur[0];
			int cy = cur[1];
			int cx = cur[2];
			int day = cur[3];
			answer = day; // 너비 우선 탐색이기 때문에 어짜피 마지막으로 받은 값이 최종 day이기 때문에 꺼낼 때 마다 초기화 되도록한다. 
			
			for(int i=0; i<6; i++) { // 현재 위치를 기준으로 6방향 탐색
				int nz = cz + dz[i];
				int ny = cy + dy[i];
				int nx = cx + dx[i]; 
					
				if( nz<0 || nz>H-1 || ny<0 || ny>N-1 || nx<0 || nx>M-1) continue; // map을 넘어가는 경우 패스
				if(map[nz][ny][nx] == 0) {
					map[nz][ny][nx] = 1; // 방문 체크
					q.offer(new int[] {nz, ny, nx, day+1});
				}
			}				
		}
		
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		
		map = new int[H][N][M];
		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					int value = Integer.parseInt(st.nextToken());
					
					// 입력 받을 때, 익은 토마토 큐에 집어 넣기(0일차)
					if(value == 1) {
						q.offer(new int[] {h,i,j,0}); // 마지막 파라미터는 day를 의미
					}
					map[h][i][j] = value;
				}
			}
		}
	}
}
