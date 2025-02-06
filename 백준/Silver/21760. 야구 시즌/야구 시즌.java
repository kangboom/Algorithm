import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			int B = D / (comb(M,2)*N*k + comb(N, 2)*M*M);
			int answer = (comb(M,2)*N*k + comb(N, 2)*M*M)*B;
			
			if(B==0) {
				System.out.println(-1);
				return ;
			}
			System.out.println(answer);
		}
	}
	
	public static int comb(int n, int r) {
		if(r == 1) {
			return n;
		}
		if(n == r) {
			return 1;
		}

		return comb(n-1, r) + comb(n-1, r-1);
	}
}
