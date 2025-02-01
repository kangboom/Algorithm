import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static int stick;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		input = br.readLine();
		
		for(int i=0; i<input.length()-1; i++) {
			char now = input.charAt(i);
			char next = input.charAt(i+1);
			
			if(now == '(') {
				if(next == ')') {
					answer += stick; 
					i++;
					continue;
				}
				stick++;
				answer++;
			}
			else {
				stick--;
			}
		}
		System.out.println(answer);
	}
}
