import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static boolean[] select;
	static int[][] nums;
	static char[] op;
	static int answer;
	static int numCnt;
	public static void main(String[] args) throws IOException {
		input();
		if(N==1) {
			return ;
		}
		for(int i=1; i<=numCnt/2; i++) {
			comb(0, 0, i);
		}
		System.out.println(answer);
	}
	
	static int operation(char oper, int a, int b) {
		if(oper == '*') {
			return a * b;
		} 
		
		if(oper == '+') {
			return a + b;
		}
		
		if(oper == '-') {
			return a - b;
		}
		
		return 0;
	}
	
	static void comb(int start, int depth, int r) {
		if(depth == r) {
			int sum = nums[0][0];
			for(int i=0; i<numCnt-1; i++) {
				if(i<numCnt-2 && select[i+1]) {
					sum = operation(op[i], sum, operation(op[i+1], nums[i+1][0], nums[i+1][1]));
					++i;
				} else {
					sum = operation(op[i], sum, nums[i][1]); 
				}
			}
			answer = Math.max(answer, sum);
			return ;
		}
		
		for(int i=start; i<numCnt-1; i++) {
			if(i>0 && select[i-1]) continue;
			
			select[i] = true;
			comb(i+1, depth+1, r);
			select[i] = false;
		}
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		if(N==1) {
			System.out.println(input.charAt(0)-'0');
			return ;
		}
		numCnt = input.length()/2 +1;
		select = new boolean[numCnt-1];
		nums = new int[numCnt-1][2];
		op = new char[numCnt-1];
		
		int idx = 0;
		for(int i=1; i<input.length(); i+=2) {
			nums[idx][0] = input.charAt(i-1)-'0';
			nums[idx][1] = input.charAt(i+1)-'0';
			op[idx] = input.charAt(i);
			idx++;
		}

		answer = Integer.MIN_VALUE;
	}

}
