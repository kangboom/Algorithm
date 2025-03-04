import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] pre;
	static List<int[]> bong;
	static int N;
	static int ans1;
	static int ans2;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		bong = new ArrayList<>();
		pre = new int[2];
		st = new StringTokenizer(br.readLine());

		pre[0] = Integer.parseInt(st.nextToken());
		pre[1] = Integer.parseInt(st.nextToken());

		Stack<Integer> stack = new Stack<>();
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (pre[1] < 0 && y > 0) { // +로 바뀐 경우
				stack.push(x);
			} else if (pre[1] > 0 && y < 0) { // -로 바뀐 경우
				if (stack.isEmpty()) {
					stack.push(pre[0]);
				} else {
					int tmp = stack.pop();
					if (tmp > pre[0]) {
						bong.add(new int[] { pre[0], tmp });
					} else {
						bong.add(new int[] { tmp, pre[0] });
					}
				}
			}
			pre[0] = x;
			pre[1] = y;
		}
		if (stack.size() == 1) {
			int tmp = stack.pop();
			int x1 = tmp < pre[0] ? tmp : pre[0];
			int x2 = tmp < pre[0] ? pre[0] : tmp;
			bong.add(new int[] { x1, x2 });
		} else if (stack.size() == 2) {
			int tmp1 = stack.pop();
			int tmp2 = stack.pop();
			int x1 = tmp1 < tmp2 ? tmp1 : tmp2;
			int x2 = tmp1 < tmp2 ? tmp2 : tmp1;
			bong.add(new int[] { x1, x2 });
		}

		// 첫번 째 좌표를 기준으로 정렬
		Collections.sort(bong, (o1, o2) -> {
			return Integer.compare(o1[0], o2[0]);
		});

		// 답 구하기

		if (bong.size() == 1) {
			ans1 = 1;
			ans2 = 1;
		} else {
			ans1 = bong.size();
			for (int i = 0; i < bong.size();) {
				i = getCnt(i);
			}
		}
		System.out.println(ans1 + " " + ans2);
	}

	static int getCnt(int i) {
		boolean flag = true;

		int j;
		for (j = i + 1; j < bong.size();) {
			if (bong.get(j)[0] < bong.get(i)[1]) { // 기준선의 끝점 보다 타겟선의 시작점이 작은 경우 포함
				flag = false;
				--ans1;
				j = getCnt(j);
			} else {
				break;
			}
		}

		if (flag)
			++ans2;
		return j;
	}
}
