import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] map;
	static int maxNum = 1;
	static int R;
	static int C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		boolean[] isCutR = new boolean[R+1];
		boolean[] isCutC = new boolean[C+1];
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int direc = Integer.parseInt(st.nextToken()); // 0: 가로, 1: 세로
			int location = Integer.parseInt(st.nextToken());
			
			if(direc == 0) {
				isCutC[location] = true;
			}
			else {
				isCutR[location] = true;
			}
		}
		
		int tmp = 0;
		int maxR = 0;
		for(int i=1; i<=R; i++) {
			tmp++;
			if(isCutR[i] || i==R) {
				maxR = Math.max(maxR, tmp);
				tmp = 0;
			}
		}
		
		tmp = 0;
		int maxC = 0;
		for(int i=1; i<=C; i++) {
			tmp++;
			if(isCutC[i] || i==C) {
				maxC = Math.max(maxC, tmp);
				tmp = 0;
			}
		}

		System.out.println(maxR * maxC);
	}
}
