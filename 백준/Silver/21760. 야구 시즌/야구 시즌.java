import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

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
			
			int B = (2*D) /( k*N*M*(M-1) + M*M*N*(N-1) );
			
			if(B==0) {
				System.out.println(-1);
				continue;
			}
			
			int answer = (M*(M-1)/2)*N*k*B + M*M*(N*(N-1)/2)*B;
			System.out.println(answer);
		}
		
	}
}


