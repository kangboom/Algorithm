import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int M;
	static int K;
	static Info[][] map;
	static Shark[] sharks;
	static Map<Integer, Map<Integer, int[]>> priority;
	static final int INF = Integer.MAX_VALUE;
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

	static void solve() {
		Queue<Shark> nextQ = new LinkedList<>();
		
		// 큐에 상어 넣기
		for(int i=1; i<=M; i++) {
			nextQ.offer(sharks[i]);
		}
		
		int t = 0;
		while(t<=1000) {

			Queue<Shark> q = new LinkedList<>();
			while(!nextQ.isEmpty()) {
				Shark shark = nextQ.poll();
				q.offer(shark);
			}
			
			if(q.size() == 1) {
				break;
			}
			
			Queue<int[]> tmpq = new LinkedList<>();
			while(!q.isEmpty()) {
				
				Shark now = q.poll();
				
				// 상어 이동, 1번부터 넣어줬기 때문에 항상 번호가 낮은 순부터 움직인다.
				// 4방 탐색, 상어 + 현재 방향 별 우선 순위 고려
				boolean isEmpty = false;
				boolean isFirst = true;
				int[] mySmell = {-1, -1, -1}; // y, x, direct
				
				for(int i=0; i<4; i++) {
					int d = priority.get(now.num).get(now.direct)[i];
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];
					
					// 범위 넘어가면 건너뛰기
					if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
					
					// 빈공간이 있는 지 확인 -> 있으면 이동, 없으면 자기 냄새가 있는 곳으로 이동
					if(!map[ny][nx].isSmell) {
						
						// 같은 곳에 방문할 경우 죽이기
						if(map[ny][nx].num != 0) {
							isEmpty = true;
							break;
						}
						
						// 빈공간이면 내 냄새 남기기
						map[ny][nx].num = now.num;
						map[ny][nx].life = K+1;
						tmpq.offer(new int[] {ny, nx});
						isEmpty = true;
						nextQ.add(new Shark(now.num, ny, nx, d));
						break;
					}
					
					// 같은  곳에 방문 한 경우 죽이기
//					else if(map[ny][nx].life == K+1 && map[ny][nx].isSmell == false) {
//						isEmpty = true;
//						break;
//					}
					
					if(isFirst && map[ny][nx].num == now.num) {
						isFirst = false;
						mySmell[0] = ny;
						mySmell[1] = nx;
						mySmell[2] = d;
					}
					
				}
				// 주변에 빈공간 X, 자기 냄새로 이동 
				if(!isEmpty) {
					int ny = mySmell[0];
					int nx = mySmell[1];
					int d = mySmell[2];
					
					map[ny][nx].num = now.num;
					map[ny][nx].life = K+1; // 재방문 하는 경우 라이프 갱신
					nextQ.offer(new Shark(now.num, ny, nx, d));
				}
			}
			// 빈공간에 새롭게 채운 냄새 체크해주기
			while(!tmpq.isEmpty()) {
				int[] pos = tmpq.poll();
				map[pos[0]][pos[1]].isSmell = true;
			}
			
			// life 감소 시키기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j].life !=0 ) {
						--map[i][j].life;
						if(map[i][j].life == 0) {
							map[i][j].num = 0;
							map[i][j].isSmell = false;
						}
					}
				}
			}
			t++;
			
			// map 정보 입력 확인
//			System.out.println("[DEBUG] t = " + t);
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print("[" + map[i][j].num + " " + map[i][j].life + "]" + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		if(t == 1001) {
			ans = -1;
		} else {
			ans = t;
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 초기 상어 정보를 담기 위한 배열 생성
		sharks = new Shark[M + 1];

		// 맵 정보 받기
		map = new Info[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int data = Integer.parseInt(st.nextToken());
				if (data == 0) {
					map[i][j] = new Info(0, 0, false);
				} else {
					map[i][j] = new Info(data, K, true);
					sharks[data] = new Shark(data, i, j);
				}
			}
		}

		// 상어 초기 방향 정보 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].direct = Integer.parseInt(st.nextToken());
		}

		// key: 상어 번호, value: map<Integer, int[]>
		priority = new HashMap<>();

		// 상어마다 우선순위 정보 받기
		for (int i = 1; i <= M; i++) {

			// key: 방향 정보(1~4), value: 방향별 우선순위 int[4]
			Map<Integer, int[]> tmp = new HashMap<>();
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());

				int[] arr = new int[4];
				for (int k = 0; k < 4; k++) {
					arr[k] = Integer.parseInt(st.nextToken());
				}
				tmp.put(j, arr);
			}
			priority.put(i, tmp);
		}

		// 우선순위 입력 확인
//		for(int i=1; i<=M; i++) {
//			System.out.println("[DEBUG] " + i + "번 상어 정보");
//			for(int j=1; j<=4; j++) {
//				System.out.println(j + "번 방향 : " + Arrays.toString(priority.get(i).get(j)));
//			}
//			System.out.println();
//		}
//		
//		// map 정보 입력 확인
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print("[" + map[i][j].num + " " + map[i][j].life + "]" + " ");
//			}
//			System.out.println();
//		}
//		
//		// 상어 정보 확인
//		for(int i=1; i<=M; i++) {
//			System.out.println(sharks[i]);
//		}
//		

	}

	static class Info {
		int num;
		int life;
		boolean isSmell;
		Info(int num, int life, boolean isSmell) {
			this.num = num;
			this.life = life;
			this.isSmell = isSmell;
		}
	}

	static class Shark {
		int num;
		int y, x;
		int direct;

		Shark(int num, int y, int x) {
			this.num = num;
			this.y = y;
			this.x = x;
		}

		Shark(int num, int y, int x, int direct) {
			this.num = num;
			this.y = y;
			this.x = x;
			this.direct = direct;
		}

		@Override
		public String toString() {
			return "Shark [num=" + num + ", y=" + y + ", x=" + x + ", direct=" + direct + "]";
		}

	}
}