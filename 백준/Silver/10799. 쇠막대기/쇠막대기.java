import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		input = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		int i=0;
		while(i<input.length()-1) {
			char now = input.charAt(i);
			char next = input.charAt(i+1);
			
			if(now == '(' && next == ')') {
				answer += stack.size(); 
				i += 2;
				continue;
			}
			
			if(now == ')') {
				stack.pop();
				i++;
				continue;
			}
			
			stack.add(now);
			answer++;
			i++;
		}
		
		System.out.println(answer);
	}
}
