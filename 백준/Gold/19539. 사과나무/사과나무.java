import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int twoCnt = 0;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			int num  = Integer.parseInt(st.nextToken());
			twoCnt += num/2;
			sum += num;
		}
		
		if(sum % 3 !=0) {
			System.out.println("NO");
			return ;
		}
		
		if(twoCnt >= sum/3) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
