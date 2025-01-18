
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int M;
	static char[][] map = new char[1000][1000];
	static int sy;
	static int sx;
	static int answer;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<int[]> q = new LinkedList<>();
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 실행
		for(int t=0; t<T; t++) {
			
			input();
			bfs();
			
			if(answer == 0) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(answer);
			}
		}
	}
	
	static void bfs() {
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			int time = cur[2];
			int status = cur[3]; // 사람:0 --- 불:1 
			
			if(status == 0) {	
				if(cy == 0 || cy == N-1 || cx == 0 || cx == M-1) { // 탈출 현재 위치가 가장 자리일 경우
					answer = time+1;
					return;
				}
			}
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1) continue; // map 벗어난 경우 pass
				if(map[ny][nx] == '#') continue; // 다음 위치가 벽인 경우 pass
				
				// 불인 경우 == 1
				if(status == 1) {
					if(map[ny][nx] == '.') { // 빈 공간일 경우 불 지르고 q에 추가
						map[ny][nx] = '*';
						q.offer(new int[] {ny, nx, time+1, 1});
					}
				} else { // 사람인 경우 == 0
					if(!visited[ny][nx] && map[ny][nx] == '.') { // 빈 공간일 경우 이동
						q.offer(new int[] {ny, nx, time+1, 0});
						visited[ny][nx] = true; // 중복해서 방문할 수 없게 체크
					}
				}
			}
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		q.clear(); // q 초기화 시켜주기
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '@') { // 시작 위치 기억
					sy = i;
					sx = j;
				}
				if(map[i][j] == '*') { // 불일 경우 큐에 추가
					q.offer(new int[] {i, j, 0, 1}); // { y위치, x위치, 시간, 사람(0) or 불(1)}
				}
			}
		}
		q.offer(new int[] {sy, sx, 0, 0}); // 사람 시작 위치 추가
		
		visited = new boolean[N][M];
		visited[sy][sx] = true;
		
		answer =0; // 결과 값 초기화
	}
	
	

}

/*
	1. 불은 매초마다 동서남북 방향으로 옮겨 붙는다.
	2. 불이 있는 칸 or 이제 불이 붙으려는 칸에는 이동할 수 없다.
	3. 현재 위치에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
	4. 출구도 찾아야 되네(가장 자리가 .가 출구)[0][?] == '.' or [?][0] == '.' 이면 출구 -> 시간 계산 
	
	불이랑 사람이랑 같이 움직여야 돼
	
	불을 먼저 이동( 불을 먼저 큐에 채움)
 	그 다음 사람 이동  ( 그다음 사람 위치 큐)
	
	결론: 얼마나 빨리 탈출할 수 있는지 구하기, 없으면 IMPOSSILBE 출력
*/