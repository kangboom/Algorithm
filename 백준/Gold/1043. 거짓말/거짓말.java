import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Integer> trueList;
	static List<Integer>[] party;
	static int[] parent;
	static int ans;

	public static void main(String[] args) throws IOException {
		input();
		if(trueList.size() == 0) {
			System.out.println(M);
			return ;
		}
		
		getTruth();
		getCnt();
//		for(int i=1; i<=N; i++) {
//			System.out.println(i + "번 : parent[" + parent[i] + "]");
//		}
		System.out.println(ans);
	}

	// 진실 알고있는 집합 구하기
	static void getTruth() {
		for (int i = 0; i < M; i++) {
			getParent(party[i]);
		}
	}

	// 과장해서 말할 수 있는 파티의 개수 구하기
	static void getCnt() {
		for (int i = 0; i < M; i++) {
			boolean flag = true;
			for (int j = 0; j < party[i].size(); j++) {
				if (find(trueList.get(0)) == find(party[i].get(j))) {
					flag = false;
				}
			}

			if (flag) {
				++ans;
			}
		}
	}

	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void getParent(List<Integer> list) {
		if(list.size() == 0) return;
		int min = N + 1;
		for (int i = 0; i < list.size(); i++) {
			min = Math.min(min, find(list.get(i)));
		}

		for (int i = 0; i < list.size(); i++) {
			parent[find(list.get(i))] = min;
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 부모 배열 자기 자신으로 초기화
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int truth_n = Integer.parseInt(st.nextToken());
		trueList = new ArrayList<>();

		for (int i = 0; i < truth_n; i++) {
			trueList.add(Integer.parseInt(st.nextToken()));
		}
		
		getParent(trueList);

		party = new List[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p_n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < p_n; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}

	}

}
