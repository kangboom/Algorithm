import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int[][] data = new int[6][2];
		
		for(int i=0; i<6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			data[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		int maxRow = Integer.MIN_VALUE;
		int maxRowIndex = -1;
		
		int maxCol = Integer.MIN_VALUE;
		int maxColIndex = -1;
		
		for(int i=0; i<6; i++) {
			if(data[i][0] == 1 || data[i][0] == 2) {
				if(data[i][1] > maxRow) {
					maxRow = data[i][1];
					maxRowIndex = i;
				}
			} else {
				if(data[i][1] > maxCol) {
					maxCol = data[i][1];
					maxColIndex = i;
				}
			}
		}
		
		int row;
		int col;
		if(maxRowIndex == 0) {
			row = Math.abs(data[5][1] - data[1][1]);
		} else if(maxRowIndex ==5) {
			row = Math.abs(data[4][1] - data[0][1]);
		} else {
			row = Math.abs(data[maxRowIndex-1][1] - data[maxRowIndex+1][1]);
		}
		
		if(maxColIndex == 0) {
			col = Math.abs(data[5][1] - data[1][1]);
		} else  if(maxColIndex == 5) {
			col = Math.abs(data[4][1] - data[0][1]);
		} else {
			col = Math.abs(data[maxColIndex-1][1] - data[maxColIndex+1][1]);
		}
		
		System.out.println(K* (maxRow*maxCol - row*col));
	}

}
