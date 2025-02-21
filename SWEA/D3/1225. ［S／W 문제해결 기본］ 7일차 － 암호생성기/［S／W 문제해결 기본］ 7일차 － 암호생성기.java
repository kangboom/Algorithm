import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		for(int t=1; t<=10; t++) {
			q.clear();

			br.readLine();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			// 사이클
			int tmp = 0;
			while(true) {
				int now = q.poll();
				now = now - (tmp+1);
				
				if(now <=0) {
					q.offer(0);
					break;
				}
				
				q.offer(now);
				tmp = ++tmp%5;
			}
			
			sb.append("#" + t + " ");
			for(int i=0; i<8; i++) {
				sb.append(q.poll() + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
