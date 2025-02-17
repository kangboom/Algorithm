import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int M;
	static List<int[]> house;
	static List<int[]> chicken;
	static int answer;
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		comb(0, 0);
		System.out.println(answer);
	}
	
	static void comb(int start, int depth) {
		if(depth == M) {
			answer = Math.min(answer, solve());
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			order[depth] = i;
			comb(i+1, depth+1);
		}
	}
	
	static int solve() {
		int sum = 0;
		for(int i=0; i<house.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j=0; j<M; j++) {
				min = Math.min(min,  Math.abs(house.get(i)[0] - chicken.get(order[j])[0]) 
						+ Math.abs(house.get(i)[1] - chicken.get(order[j])[1]));
			}
			sum += min;
		}
		return sum;
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					house.add(new int[] {i, j});
				} 
				if(num == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}
		
		order = new int[M];
		answer = Integer.MAX_VALUE;
	}

}
