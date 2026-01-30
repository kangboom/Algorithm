import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    static int M;
    static int W;
    static Edge[] edges;
    static int[] dist;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input();
            if (isPossible()) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    static boolean isPossible() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 2*M + W; j++) {
                Edge edge = edges[j];
                if (dist[edge.end] > dist[edge.start] + edge.dist) {
                    dist[edge.end] = dist[edge.start] + edge.dist;
                    
                    if (i == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        dist = new int[N + 1];
        edges = new Edge[2 * M + W];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            edges[i * 2] = new Edge(start, end, dist);
            edges[i * 2 + 1] = new Edge(end, start, dist);
        }
        
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            edges[2*M + i] = new Edge(start, end, -1 * dist);
        }
    }
    
    static class Edge {
        
        int start;
        int end;
        int dist;
        
        public Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
}
