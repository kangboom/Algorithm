import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] vel = new int[N];
		for(int i=0; i<N; i++) {
			vel[i] = Integer.parseInt(st.nextToken());
		}
		int[] tmp = new int[N];
		tmp[N-1] = 1; // 마지막은 무조건 1
		
		long answer = tmp[N-1];
		for(int i=N-1; i>0; i--) {
			if(vel[i-1] <= tmp[i]){
				tmp[i-1] = vel[i-1];
				
			} else {
				tmp[i-1] = tmp[i]+1;
			}
			answer += tmp[i-1];
		}
		
		System.out.println(answer);
	}

}
