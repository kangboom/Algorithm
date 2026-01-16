import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    static int M;
    static List<Integer>[] graph;
    static int[] visited;
    static boolean[] isCycle;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        visited = new int[N+1];
        isCycle = new boolean[N+1];
        graph = new List[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
        }
        
        for(int i=1; i<=N; i++){
            if(visited[i] == 0) {
                dfs(i);
            }
        }
        
        int ans = 0;
        for(int i=1; i<=N; i++){
            if(!isCycle[i]) ans++;
        }
        System.out.println(ans);
    }
    
    static void dfs(int now) {
        visited[now] = 1;
        
        for(int next : graph[now]){
            if(visited[next] == 1){
                isCycle[now] = true;
                break;
            } else if(visited[next] == 0) {
                dfs(next);
                if(isCycle[next]){
                    isCycle[now] = true;
                    break;
                }
            } else {
                if(isCycle[next]){
                    isCycle[now] = true;
                    break;
                }
            }
        }
        
        visited[now] = 2;
    }
}
