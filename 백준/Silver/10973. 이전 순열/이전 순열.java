import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		if(pp()) {
			for(int i=0; i<N; i++) {
				sb.append(arr[i]).append(" ");
			}
		} else {
			sb.append(-1);
		}
		System.out.println(sb);
	}
	
	static boolean pp() {
		
		int i=N-1;
		while(i>0 && arr[i-1] <= arr[i]) --i;
		if(i==0) return false;
		
		int j = N-1;
		while(arr[j] >= arr[i-1]) --j;
		
		swap(i-1, j);
		
		j = N-1;
		while(i<j) swap(i++, j--);
		
		return true;
	}
	
	static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
