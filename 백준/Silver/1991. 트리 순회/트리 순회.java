import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] graph;
	static StringBuilder sb;

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		
		sb = new StringBuilder();
		preorder(0);
		
		sb.append('\n');
		inorder(0);
		
		sb.append('\n');
		postorder(0);
		
		System.out.println(sb);
	}
	
	static void preorder(int node) {
		if(node == '.'-'A') return ;
		
		sb.append((char)(node+'A'));
		preorder(graph[node][0]);
		preorder(graph[node][1]);
	}
	
	static void inorder(int node) {
		if(node == '.'-'A') return ;
		
		inorder(graph[node][0]);
		sb.append((char)(node+'A'));
		inorder(graph[node][1]);
	}
	
	static void postorder(int node) {
		if(node == '.'-'A') return ;
		
		postorder(graph[node][0]);
		postorder(graph[node][1]);
		sb.append((char)(node+'A'));
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new int[N][2];
		
		for(int i=0; i<N ;i++) {
			st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0)-'A';
			graph[node][0] = st.nextToken().charAt(0)-'A';
			graph[node][1] = st.nextToken().charAt(0)-'A';
		}
	}
}

class Node {
	char left;
	char right;
	
	public Node(char left, char right) {
		this.left = left;
		this.right = right;
	}
}
