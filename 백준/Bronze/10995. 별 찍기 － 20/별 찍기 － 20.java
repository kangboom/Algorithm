import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		star(N);
		
	}
	static void star(int n) {
		if(n==0) {
			return ;
		}
		star(n-1);
		if(n % 2 == 0) {
			System.out.print(" ");
		}
		for(int i=0; i<N; i++) {
			System.out.print("* ");
		}
		System.out.println();
	}

}
