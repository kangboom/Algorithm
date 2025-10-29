import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int N;
	static int M;
	static int[] next = new int[100_0001];
	static int[] prev = new int[100_0001];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		input();
		System.out.println(sb);
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 입력 받아서 연결리스트 만들기
		st = new StringTokenizer(br.readLine());
		int headId = Integer.parseInt(st.nextToken());
		next[headId] = headId;
		prev[headId] = headId;

		int prevId = headId;
		for (int i = 1; i < N; i++) {
		    int curId = Integer.parseInt(st.nextToken());
		    prev[curId] = prevId;
		    next[prevId] = curId;
		    prevId = curId;
		}
		prev[headId] = prevId;
		next[prevId] = headId;
		
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			String operation = st.nextToken();
			int curId = Integer.parseInt(st.nextToken());
			
			switch (operation) {
			    case "BN":
			    	sb.append(next[curId]).append("\n");
			        int newId = Integer.parseInt(st.nextToken());
			        prev[newId] = curId;
			        next[newId] = next[curId];
			        prev[next[curId]] = newId;
			        next[curId] = newId;
			        break;
			        
			    case "BP":
			    	sb.append(prev[curId]).append("\n");
			        newId = Integer.parseInt(st.nextToken());
			        prev[newId] = prev[curId];
			        next[newId] = curId;
			        next[prev[curId]] = newId;
			        prev[curId] = newId;
			        break;
			        
			    case "CN":
			    	sb.append(next[curId]).append("\n");
			        next[curId] = next[next[curId]];
			        prev[next[curId]] = curId;
			        break;
			        
			    case "CP":
			    	sb.append(prev[curId]).append("\n");
			        prev[curId] = prev[prev[curId]];
			        next[prev[curId]] = curId;
			        break;
			}
		}
	}
}
