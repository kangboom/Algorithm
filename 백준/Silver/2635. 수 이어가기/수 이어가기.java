import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int a1;
	static ArrayList<Integer> list = new ArrayList<>();
	static int[] answer;
	public static void main(String[] args) throws IOException {
		input();
		
		int maxCnt = 0;
		for(int a2=1; a2<= a1; a2++) {
			list.clear();
			list.add(a1);
			list.add(a2);
			
			int cnt = 2;
			int ppn = a1;
			int pn = a2;
			while(true) {
				int n = ppn - pn;
				if(n<0) {
					break;
				}
				list.add(n);
				cnt++;
				ppn = pn;
				pn = n;
			}
			if(cnt > maxCnt) {
				maxCnt = cnt;
				answer = new int[maxCnt];
				for(int i=0; i<maxCnt; i++) {
					answer[i] = list.get(i);
				}
			}
		}
		System.out.println(maxCnt);
		for(int i=0; i<maxCnt; i++) {
			System.out.print(answer[i] + " ");
		}
	}
	
	static void input() throws IOException {
		a1 = Integer.parseInt(br.readLine());
	}

}
