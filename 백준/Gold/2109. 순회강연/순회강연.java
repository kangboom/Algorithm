import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Schedule> pq = new PriorityQueue<>();
		int max = 0;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			pq.add(new Schedule(day, price));
			max = Math.max(max, day);
		}
		
		boolean[] checked = new boolean[max+1];
		int ans = 0;
		while(!pq.isEmpty()) {
			Schedule now = pq.poll();
			for(int i=now.day; i>=1 ; i--) {
				if(checked[i]) continue;
				
				checked[i] = true;
				ans += now.price;
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static class Schedule implements Comparable<Schedule>{
		int day;
		int price;
		
		Schedule(int day, int price){
			this.day = day;
			this.price = price;
		}
		
		@Override
		public int compareTo(Schedule o) {
			if(this.price == o.price) {
				return this.day - o.day;
			} else {
				return o.price - this.price;
			}
		}
	}
}
