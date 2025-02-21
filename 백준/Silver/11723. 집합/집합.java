import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int set = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			
			byte num = 0;
			switch(oper.charAt(0)) {
			case 'a':
				if(oper.charAt(1) == 'd') {
					num = (byte)Integer.parseInt(st.nextToken());
					set = set | 1<<num;
				} else {
					set = (int) (Math.pow(2, 21)) - 1;
				}
				break;
			case 'c':
				num = (byte)Integer.parseInt(st.nextToken());
				if((set & 1<<num) != 0) {
					sb.append(1 + "\n");
				} else {
					sb.append(0 + "\n");
				}
				break;
			case 'r':
				num = (byte)Integer.parseInt(st.nextToken());
				set = set & ~(set & 1<<num);
				break;
			case 't':
				num = (byte)Integer.parseInt(st.nextToken());
				if((set & 1<<num) != 0) {
					set = set & ~(set & 1<<num);
				} else {
					set = set | 1<<num;
				}
				break;
			case 'e':
				set = 0;
				break;
			}
		}
		System.out.println(sb);
	}
}
