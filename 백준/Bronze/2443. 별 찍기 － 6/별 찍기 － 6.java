import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		star(n, 0);
	}
	
	static void star(int n, int k) {
		if(n==0) return ;
		
		for(int i=0; i<k; i++) {
			System.out.print(" ");
		}
		for(int i=0; i<2*n-1; i++) {
			System.out.print("*");
		}
		System.out.println();
		star(n-1, k+1);
	}

}
