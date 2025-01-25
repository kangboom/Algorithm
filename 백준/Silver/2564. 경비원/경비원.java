import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int C;
	static int R;
	static int N;
	static int[][] locations;
	static int userDirec;
	static int userDist;
	public static void main(String[] args) throws IOException {

		input();
		int answer = 0;
		if(userDirec == 1) {
				for(int[] market : locations) {
					int direction = market[0];
					int distance = market[1];
					
					if(direction == 1) {
						answer += Math.abs(userDist - distance);
					} else if(direction == 2) {
						int left = userDist + C + distance;
						int right = R-userDist + C + R-distance;
						answer += Math.min(left, right);
					} else if(direction == 3) {
						answer += userDist + distance;
					} else {
						answer += R-userDist + distance;
					}
				
			}
		} else if(userDirec == 2) {
				for(int[] market : locations) {
					int direction = market[0];
					int distance = market[1];
					
					if(direction == 1) {
						int left = userDist + C + distance;
						int right = R-userDist + C + R-distance;
						answer += Math.min(left, right);
					} else if(direction == 2) {
						answer += Math.abs(userDist-distance);
					} else if(direction == 3) {
						answer += userDist + C-distance;
					} else {
						answer += R-userDist + C-distance;
					}
				}
		} else if(userDirec == 3) {
			for(int[] market : locations) {
				int direction = market[0];
				int distance = market[1];
				
				if(direction == 1) {
					answer += userDist + distance;
				} else if(direction == 2) {
					answer += C-userDist + distance;
				} else if(direction == 3) {
					answer += Math.abs(userDist-distance);
				} else {
					int left = userDist + R + distance;
					int right = C-userDist + R + C-distance;
					answer += Math.min(left, right);
				}
			}
		} else {
			for(int[] market : locations) {
				int direction = market[0];
				int distance = market[1];
				
				if(direction == 1) {
					answer += userDist + R-distance;
				} else if(direction == 2) {
					answer += C-userDist + R-distance;
				} else if(direction == 3) {
					int left = userDist + R + distance;
					int right = C-userDist + R + C-distance;
					answer += Math.min(left, right); 
				} else {
					answer += Math.abs(userDist-distance);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void input() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(br.readLine());
		
		locations = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			locations[i][0] = Integer.parseInt(st.nextToken());
			locations[i][1] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		userDirec = Integer.parseInt(st.nextToken());
		userDist = Integer.parseInt(st.nextToken());
		
	}

}
