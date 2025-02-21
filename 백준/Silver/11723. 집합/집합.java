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
			if(!(oper.equals("all") || oper.equals("empty"))) {
				num = (byte)Integer.parseInt(st.nextToken());
			}
			
			switch(oper) {
			case "add":
				set = set | 1<<num;
				break;
			case "check":
				if((set & 1<<num) != 0) {
					sb.append(1 + "\n");
				} else {
					sb.append(0 + "\n");
				}
				break;
			case "remove":
				set = set & ~(set & 1<<num);
				break;
			case "toggle":
				if((set & 1<<num) != 0) {
					set = set & ~(set & 1<<num);
				} else {
					set = set | 1<<num;
				}
				break;
			case "all":
				set = (int) (Math.pow(2, 21)) - 1;
				break;
			case "empty":
				set = 0;
				break;
			}
		}
		System.out.println(sb);
	}
}
