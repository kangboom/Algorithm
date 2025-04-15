import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 
		 int N = 300;
		 
		 int T = Integer.parseInt(br.readLine());
		 for (int t = 1; t <= T; t++) {
			 String inst = br.readLine();
			 
			 int left = 1;
			 int right = 1;
			 for (int i = 0; i < inst.length(); i++) {
				 char op = inst.charAt(i);
				 
				 if (op == 'L') {
					 right = left + right;
				 } else if (op == 'R') {
					 left = left + right;
				 }
			 }
			 
			 sb.append("#").append(t).append(" ").append(left).append(" ").append(right).append("\n");
		 }
		 System.out.println(sb);
	}

}
