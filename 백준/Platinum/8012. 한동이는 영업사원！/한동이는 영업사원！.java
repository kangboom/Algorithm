import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    static int M;
    static int[][] parent;
    static int[] level;
    static int maxLevel;
    static List<Integer>[] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        maxLevel = (int)Math.ceil(Math.log(N)/Math.log(2));
        parent = new int[N+1][maxLevel+1];
        level = new int[N+1];
  
        graph = new List[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        makeTree(1, 0, 1);
        M = Integer.parseInt(br.readLine());
        int now = 1;
        int ans = 0;
        for(int i=0; i<M; i++){
            int next = Integer.parseInt(br.readLine());
            ans += LCA(now, next);
            now = next;
        }
        
        System.out.println(ans);
    }
    
    static void makeTree(int node, int pnode, int depth){
        level[node] = depth;
        parent[node][0] = pnode;
        
        for(int i=1; i<=maxLevel; i++){
            parent[node][i] = parent[parent[node][i-1]][i-1];
        }
        
        for(int next : graph[node]){
            if(next == pnode) continue;
            makeTree(next, node, depth+1);
        }
    }
    
    static int LCA(int pre, int next){
        if(pre == next) return 0;
        
        int target = pre;
        int compare = next;
        
        if(level[target] < level[compare]){
            target = next;
            compare = pre;
        }
        
        // level 맞추기
        int distance = 0;
        if(level[target] != level[compare]){
            for(int i=maxLevel; i>=0; i--){
                if(level[parent[target][i]] >= level[compare]){
                    target = parent[target][i];
                    distance += (int)Math.pow(2, i);
                }
            }
        }
        // 최소 공통 조상 구하기
        if(target == compare) return distance;
        for(int i=maxLevel; i>=0; i--){
            if(parent[target][i] != parent[compare][i]){
                target = parent[target][i];
                compare = parent[compare][i];
                distance += 2*(int)Math.pow(2, i);
            }
        }
        
        return distance + 2;
    }
}

