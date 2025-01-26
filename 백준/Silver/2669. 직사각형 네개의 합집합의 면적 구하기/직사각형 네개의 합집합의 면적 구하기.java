import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[][] map = new int[101][101];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		int sum = 0;
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				if(map[i][j] == 1) sum++;
			}
		}
		System.out.println(sum);
	}
	
	static void input() throws IOException {
		for(int t=0; t<4; t++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int x1 = input[0];
			int y1 = input[1];
			int x2 = input[2];
			int y2 = input[3];
			
			for(int i =y1; i<y2; i++) {
				for(int j=x1; j<x2; j++) {
					map[i][j] = 1;
				}
			}
		}
	}

}
