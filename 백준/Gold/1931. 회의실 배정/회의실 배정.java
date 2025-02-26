import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] room = new int[n][2];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			room[i][0] = Integer.parseInt(st.nextToken()); 
			room[i][1] = Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(room, (o1, o2)->{
			if(o1[1] == o2[1]) {
				return o1[0] - o2[0];
			} else {
				return o1[1] - o2[1];
			}
		});
		
		int cnt = 1;
		int preEnd = room[0][1];
		for(int i=1; i<n; i++) {
			if(room[i][0] >= preEnd) {
				++cnt;
				preEnd = room[i][1];
			}
		}
		
		System.out.println(cnt);
	}

}
