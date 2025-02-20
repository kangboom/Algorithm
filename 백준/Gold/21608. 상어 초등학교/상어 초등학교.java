import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static Set<Integer>[] likes;
	static int N;
	static int[] order;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		input();
		for(int i=0; i<N*N; i++) {
			search(order[i]);
		}
		// 합 검사
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				int likeCnt = getCnt(i, j);
				
				if(likeCnt == 0) {
					answer += 0;
				} else if(likeCnt == 1) {
					answer += 1;
				} else if(likeCnt == 2) {
					answer += 10;
				} else if(likeCnt == 3) {
					answer += 100;
				} else {
					answer += 1000;
				}
			}
		}

		System.out.println(answer);
	}

	static int getCnt(int y, int x) {
		
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
			if (likes[map[y][x]].contains(map[ny][nx])) {
				++cnt;
			}
		}
		return cnt;
	}
	
	static void search(int stu) {
		int targetY = -1;
		int targetX = -1;
		int maxLike = -1;
		int maxEmpty = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) continue; // 자리 정해진 곳 패스
				
				int likeTmp = 0;
				int beanTmp = 0;
				
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];

					if (ny < 0 || ny > N - 1 || nx < 0 || nx > N - 1) continue;
					if (likes[stu].contains(map[ny][nx])) {
						++likeTmp;
					}
					if (map[ny][nx]== 0) {
						++beanTmp;
					}
				}

				// 인접칸 탐색 종료
				if (maxLike == likeTmp) { // 같을 경우 빈칸 많은 곳 배정
					if (maxEmpty < beanTmp) {
						maxEmpty = beanTmp;
						
						targetY = i;
						targetX = j;
					}
				}

				if (maxLike < likeTmp) {
					
					maxLike = likeTmp;
					maxEmpty = beanTmp;

					targetY = i;
					targetX = j;
				}
			}
		}
		
		map[targetY][targetX] = stu;
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		likes = new HashSet[N*N+1];
		order = new int[N*N];

		for(int i=1; i<N*N+1; i++) {
			likes[i] = new HashSet<>();
		}
		
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());

			order[i] = student;
			for (int j = 0; j < 4; j++) {
				likes[student].add(Integer.parseInt(st.nextToken()));
			}
		}
	}
}
