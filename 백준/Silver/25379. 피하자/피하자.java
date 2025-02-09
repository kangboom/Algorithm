import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int answer1;
	static int answer2;
	static int stand1;
	static int stand2;
	static int even;
	static int odd;
	static int[] num1;
	static int[] num2;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		input();
		
		for(int i=0; i<N; i++) {
			if(num1[i] % 2 == 0) {
				even++;
			}
		}
		odd = N - even;
		
		if(N == odd || N == even) {
			System.out.println(0);
			return ;
		}
		
		solve();
		System.out.println(Math.min(answer1, answer2));
	}

	static void solve() {
		// 기준 설정
		stand1 = 0;
		stand2 = 0;
		
		for(int i=0; i<even; i++) {
			// 짝수를 앞으로 정렬
			if(num1[i] % 2 != 0){
				int j=i;
				while(num1[++j]%2 != 0);
				swap(num1, i, j);
				answer1 += Math.abs(j-i);			
			}
			
			stand1++;
		}
		
		for(int i=0; i<odd; i++) {
			
			// 짝수를 뒤로 정렬
			if(num2[i] % 2 == 0){
				int j=i;
				while(num2[++j]%2 == 0);
				swap(num2, i, j);
				answer2 += Math.abs(j-i);			
			}
			stand2++;
		}
	}
	static void swap(int[] num, int index1, int index2) {
		int tmp = num[index1];
		num[index1] = num[index2];
		num[index2] = tmp;
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		num1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		num2 = Arrays.copyOf(num1, num1.length);
	}

}
