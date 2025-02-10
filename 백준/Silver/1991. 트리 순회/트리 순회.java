import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static Map<Character, Node> graph;
	static StringBuilder sb;

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		
		sb = new StringBuilder();
		preorder('A');
		
		sb.append('\n');
		inorder('A');
		
		sb.append('\n');
		postorder('A');
		
		System.out.println(sb);
	}
	
	static void preorder(char node) {
		if(node == '.') return ;
		
		sb.append(node);
		preorder(graph.get(node).left);
		preorder(graph.get(node).right);
	}
	
	static void inorder(char node) {
		if(node == '.') return ;
		
		inorder(graph.get(node).left);
		sb.append(node);
		inorder(graph.get(node).right);
	}
	
	static void postorder(char node) {
		if(node == '.') return ;
		
		postorder(graph.get(node).left);
		postorder(graph.get(node).right);
		sb.append(node);
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new HashMap<>();
		
		for(int i=0; i<N ;i++) {
			st = new StringTokenizer(br.readLine());
			graph.put(st.nextToken().charAt(0), new Node(st.nextToken().charAt(0), st.nextToken().charAt(0)));
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
