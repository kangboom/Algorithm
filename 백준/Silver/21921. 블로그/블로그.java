import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] visitors = new int[N];
		for(int i=0; i<N; i++) {
			visitors[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N==1) {
			if(visitors[0] == 0) {				
				System.out.println("SAD");
				return ; 
			}
			System.out.println(visitors[0]);
			System.out.println(1);
			return ;
		} 
		
		int cnt = 1;
		int max = 0;
		for(int i=0; i<X; i++) {
			max += visitors[i];
		}
		
		int tmp = max;
		for(int i=X; i<N; i++) {
			tmp = tmp - visitors[i-X] + visitors[i];
			if(max == tmp) {
				cnt++;
				continue;
			} 
			if(max < tmp) {
				max = tmp;
				cnt = 1;
			}
		}
		
		if(max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max);
			System.out.println(cnt);
		}
	}

}
