import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main {
	static int n;
	static ArrayList<Pair>[] graph;
	static int[] visited;
	static Queue<Sair> queue = new LinkedList<>();
	
	static boolean bfs() {
		while (!queue.isEmpty()) {
			Sair current = queue.poll();
			
			for (Pair node : graph[current.idx]) {
				
				// int num = (int)Math.ceil((double)node.dist / 50);
				if (visited[node.idx] == -1 && node.dist <= current.cnt * 50) {
					//visited[node.idx] = current.cnt - num + 20;
					visited[node.idx] = 20;
					
					queue.offer(new Sair(node.idx, 20));
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
        	n = Integer.parseInt(br.readLine());
        	
        	ArrayList<int[]> list = new ArrayList<>();
        	st = new StringTokenizer(br.readLine());
        	int hx = Integer.parseInt(st.nextToken());
        	int hy = Integer.parseInt(st.nextToken());
        	
        	list.add(new int[] {hx, hy});
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        	
        		list.add(new int[] {x, y});
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	int rx = Integer.parseInt(st.nextToken());
        	int ry = Integer.parseInt(st.nextToken());
        	
        	list.add(new int[] {rx, ry});
        	
        	graph = new ArrayList[n + 2];
        	visited = new int[n + 2];
        	
        	Arrays.fill(visited, -1);
        	for (int i = 0; i < n + 2; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	for (int i = 0; i < n + 2; i++) {
        		int[] first = list.get(i);
        		for (int j = i + 1; j < n + 2; j++) {
        			int[] second = list.get(j);
        			
        			int dist = getDist(first, second);
        			graph[i].add(new Pair(j, dist));
        			graph[j].add(new Pair(i, dist));
        		}
        	}
        	
        	visited[0] = Integer.MAX_VALUE;
        	queue.add(new Sair(0, 20));
        	
        	bfs();
        	
        	//System.out.println(Arrays.toString(visited));
        	
        	if (visited[n + 1] != -1) {
        		System.out.println("happy");
        	} else {
        		System.out.println("sad");
        	}
        }
        
        
    }
	
	static int getDist(int[] first, int[] second) {
		return Math.abs(first[0] - second[0]) + Math.abs(first[1] - second[1]);
	}
	
	static class Pair {
		int idx;
		int dist;
		
		public Pair(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
	
	static class Sair {
		int idx;
		int cnt;
		
		public Sair(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

}