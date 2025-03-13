import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static TrieNode root;
	static StringBuilder sb = new StringBuilder();
	static boolean isPossilbe;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input();
			if (isPossilbe) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}
		}
		System.out.println(sb);
	}

	static void input() throws Exception {
		root = new TrieNode();
		isPossilbe = true;

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			insert(br.readLine());
		}
	}

	static void insert(String s) {
		if (!isPossilbe)
			return;

		TrieNode t = root;
		for (int i = 0; i < s.length(); i++) {
			
			// 중간에 끝점을 민나면 false
			if (t.isEnd) {
				isPossilbe = false;
				return;
			}

			int idx = s.charAt(i) - '0'; // 인덱스는 문자를 표현, 문자 '0'은 배열 0번에 들어가고 '1'은 배열 1번에 들어간다.

			if (t.children[idx] == null) {
				t.children[idx] = new TrieNode();
			}

			t = t.children[idx];
			++t.cnt;

		}
		t.isEnd = true;
		if (t.cnt > 1) {
			isPossilbe = false;
		}
	}

	static class TrieNode {
		TrieNode[] children = new TrieNode[10];
		boolean isEnd;
		int cnt;
	}
}
