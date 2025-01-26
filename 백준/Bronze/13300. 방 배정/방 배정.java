import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int[][] student;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		student = new int[7][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			
			student[grade][gender] += 1;
		}
		
		int answer = 0;
		for(int i=1; i<=6; i++) {
			for(int j=0; j<=1; j++) {
				answer += student[i][j] / K;
				if(student[i][j] % K != 0) answer += 1;
			}
		}
		System.out.println(answer);
	}

}
