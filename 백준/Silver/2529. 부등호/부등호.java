import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static char[] oper;
	static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static long max;
	static long min;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		input();
		for (int i = 0; i <= 9; i++) {
			boolean[] visited = new boolean[10];
			visited[i] = true;
			int[] order = new int[N+1];
			order[0] = i;
			select(1, order, visited);
			visited[i] = false;
		}
		
		System.out.printf("%0" + String.format("%s", N+1) + "d", max);
		System.out.println();
		System.out.printf("%0" + String.format("%s", N+1) + "d", min);
	}

	static void select(int depth, int[] order, boolean[] visited){
		if(depth == N+1) {
			
			long tmp = order[0];
			for(int i=1; i<N+1; i++) {
				tmp = tmp*10 + order[i];
			}
			max = Math.max(max, tmp);
			min = Math.min(min, tmp);
			return ;
		}
		
		for(int i=0; i<=9; i++) {
			if(visited[i]) continue;
			if(!isPossible(oper[depth-1], order[depth-1], i)) continue;
			
			visited[i] = true;
			order[depth] = i;
			select(depth+1, order, visited);
			visited[i] = false;
		}
	}

	static boolean isPossible(char op, int num1, int num2) {
		if (op == '<') {
			return num1 < num2;
		} else {
			return num1 > num2;
		}
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		oper = new char[N];
		for (int i = 0; i < N; i++) {
			oper[i] = st.nextToken().charAt(0);
		}

		max = 0;
		min = Long.MAX_VALUE;
	}

}