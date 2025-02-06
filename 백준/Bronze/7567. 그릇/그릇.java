import java.io.BufferedReader;
import java.io.FileNotFoundException;import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {

			int answer = 0;
			String input = br.readLine();
			
			char pre = input.charAt(0);
			answer += 10;
			
			for(int i=1; i<input.length(); i++) {
				char cur = input.charAt(i);
				if(cur != pre) {
					answer += 10;
					pre = cur;
				} else {
					answer += 5;
				}
			}
			
			System.out.println(answer);
		
		
	}
}