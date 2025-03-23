import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] cnt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<Pair> pq;
	static int idx = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		solve();
		sb.append(idx).append("\n");
		for (int i = 0; i <= N; i++) {
			if (cnt[i] != 0) {
				sb.append(cnt[i]).append(" ");
			}
		}
		
		System.out.print(sb);
	}

	static void solve() {
		Queue<Computer> cq = new PriorityQueue<>();
		Queue<Integer> tmpq = new PriorityQueue<>((o1, o2) -> {
			return o1 - o2;
		});

		while (!pq.isEmpty()) {
			Pair now = pq.poll();

			while (!cq.isEmpty()) {
				if (cq.peek().time > now.start) {
					break;
				}
				tmpq.offer(cq.poll().num);
			}

			if (tmpq.isEmpty()) {
				cq.offer(new Computer(++idx, now.end));
				++cnt[idx];
			} else {
				int cIdx = tmpq.poll();
				++cnt[cIdx];
				cq.add(new Computer(cIdx, now.end));
			}
		}
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		cnt = new int[N+1];
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.offer(new Pair(start, end));
		}
	}

	static class Pair implements Comparable<Pair> {
		int start;
		int end;

		Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}

	static class Computer implements Comparable<Computer> {
		int num;
		int time;

		Computer(int num, int time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Computer o) {

			return Integer.compare(this.time, o.time);

		}
	}
}
