import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static ArrayList<int[]> input;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		input();
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		while(!stack.isEmpty()){
			int cur = stack.pop();
			int next = search(cur);
			
			if(next < N-1) {
				stack.push(next);
			}
			
			if(input.get(next)[1] > input.get(cur)[1]) {
				answer += input.get(cur)[1] * (input.get(next)[0] - input.get(cur)[0]);
			} else {
				answer += input.get(cur)[1];
				answer += input.get(next)[1] * (input.get(next)[0] - input.get(cur)[0]-1);
			}
			// 현재 인덱스를 기준으로 우측 최대값 찾기
		}
		answer += input.get(N-1)[1];
		System.out.println(answer);
	}
	static int search(int index) {
		
		int curH = input.get(index)[1];
		int maxH = 0;
		int maxIndex = 0;
		for(int i=index+1 ; i<N ;i++) {
			if(input.get(i)[1] > maxH) {
				maxH = input.get(i)[1];
				maxIndex = i;
			}
			if(maxH>curH) {
				return maxIndex;
			}
		}

		return maxIndex;
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		input = new ArrayList<>();
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			input.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		// input 정렬
		Collections.sort(input, (o1, o2) ->{
			return o1[0] - o2[0];
		});
	}
}
