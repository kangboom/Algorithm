import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[n];
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = n-1;
		while(i>0 && num[i-1] > num[i]) {
			i--;
		}
		
		if(i == 0) {
			System.out.println(-1);
			return ;
		}
		
		int j = n-1;
		while(num[i-1] > num[j]) {
			j--;
		}
		
		swap(i-1, j);
		j = n-1;
		// 뒤에 정렬
		while(i<j) {
			swap(i, j);
			i++;
			j--;
		}

		StringBuilder sb = new StringBuilder();
		for(int k=0; k<n; k++) {
			sb.append(num[k]).append(" ");
		}
		System.out.print(sb);
	}
	
	static void swap(int idx1, int idx2) {
		int tmp = num[idx1];
		num[idx1] = num[idx2];
		num[idx2] = tmp;
	}
}
