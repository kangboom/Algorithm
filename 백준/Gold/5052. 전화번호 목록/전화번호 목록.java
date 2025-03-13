import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static TrieNode root;
	static StringBuilder sb = new StringBuilder();
	static String[] numbers;
	static int N;
	static boolean isPossible;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input();
			for(int i=0; i<N; i++) {
				insert(numbers[i]);
			}
			check(root);
			if (isPossible) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}
		}
		System.out.println(sb);
	}

	static void check(TrieNode t) {
		if(t.isEnd) {
			if(t.cnt >= 2) {
				isPossible = false;
				return ;
			}
		}
		
		for(int i=0; i<10; i++) {
			if(t.children[i] == null) continue;
			check(t.children[i]);
		}
	}
	
	static void input() throws Exception {
		root = new TrieNode();

		N = Integer.parseInt(br.readLine());
		numbers = new String[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = br.readLine();
		}
		
		isPossible = true;
	}

	static void insert(String s) {
		TrieNode t = root;
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - '0'; // 인덱스는 문자를 표현, 문자 '0'은 배열 0번에 들어가고 '1'은 배열 1번에 들어간다.

			if (t.children[idx] == null) {
				t.children[idx] = new TrieNode();
			}

			t = t.children[idx];
			++t.cnt;
		}
		t.isEnd = true;
	}

	static class TrieNode {
		TrieNode[] children = new TrieNode[10];
		boolean isEnd;
		int cnt;
	}
}
