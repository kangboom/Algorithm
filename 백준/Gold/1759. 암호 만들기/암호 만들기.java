import java.util.*;
import java.io.*;

public class Main {
	static int L;
	static int C;
	static char[] data;
	static char[] order;
	static String check = "aeiou";
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		input();
		select(0, 0);
		System.out.println(sb);
	}

	static void select(int start, int index) {
		if(index == L) {
			int cnt = 0;
			for(int i=0; i<L; i++) {
				if(check.contains(String.valueOf(order[i]))){
					cnt++;
				}
			}
			
			if(1 <= cnt && (L-cnt) >= 2) {
				for(int i=0; i<L; i++) {
					sb.append(order[i]);
				}
				sb.append('\n');
			}
			return ;
		}
		
		for(int i=start; i<C; i++) {
			order[index] = data[i];
			select(i+1, index+1);
		}
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		data = new char[C];
		order = new char[L];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			data[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(data);
	}
}
