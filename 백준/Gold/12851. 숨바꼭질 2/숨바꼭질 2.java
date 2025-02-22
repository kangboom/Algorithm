import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int X;
	static int K;
	static int answerTime;
	static int answerCnt;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[100001];
		bfs();
		System.out.println(answerTime);
		System.out.println(answerCnt);

	}

	static void bfs() {
		int[] dx = {-1, 1};
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { X, 0 });
		visited[X] = -1;
		
		if(X == K) {
			++answerCnt;
			return ;
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int now = cur[0];
			int time = cur[1];

			if (now == K) {
				answerTime = time;
				return;
			}

			for (int i = 0; i < 3; i++) {
				int next = -1;
		
				if (i == 2) {
					next = now * 2;
				} else {
					next = now + dx[i];
				}

				if (!isIn(next) || (visited[next] !=0 && visited[next] < time + 1))
					continue;

				visited[next] = time + 1;
				if (next == K) {
					++answerCnt;
				}
				q.offer(new int[] { next, time + 1 });
			}
		}

	}

	static boolean isIn(int x) {
		return 0 <= x && x <= 100000;
	}

}
