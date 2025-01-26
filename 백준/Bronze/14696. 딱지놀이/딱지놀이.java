import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] A;
	static int[] B;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int t=0; t<N; t++) {
			input();
			char winner = 'D';
			
			for(int i=4; i>0 ;i--) {
				if(A[i] > B[i]) {
					winner = 'A';
					break;
				} else if(A[i] < B[i]) {
					winner = 'B';
					break;
 				}
			}
			System.out.println(winner);
		}
		
	}
	
	static void input() throws IOException {
		A = new int[5];
		B = new int[5];
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		for(int i=0; i<a; i++) {
			int paint = Integer.parseInt(st.nextToken());
			A[paint] += 1;
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		for(int i=0; i<b; i++) {
			int paint = Integer.parseInt(st.nextToken());
			B[paint] += 1;
		}
		
	}
}

/*
	별 > 동그라미 > 네모 > 세모
*/