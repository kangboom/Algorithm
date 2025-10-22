import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			// 아이들 만난 경우
			if(a==0) {
				if(pq.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
				continue;
			}
			
			// 선물을 채우는 경우
			for(int j=0; j<a; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		System.out.println(sb);
	}

}
