import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int[][] map;
    static int N;
    static int M;
    static int ans = Integer.MAX_VALUE;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static ArrayList<int[]> combArray = new ArrayList<>();
    static ArrayList<Pos> virousLoc = new ArrayList<>();
    static int[] order;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        order = new int[M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    virousLoc.add(new Pos(i, j));
                }
            }
        }
        comb(0, 0);
        simul();
        if(ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }
    
    static void simul(){
        for(int[] combination : combArray){
            int[][] copyMap = new int[N][N];
            copy(copyMap);
            boolean[][] visited = new boolean[N][N];
            int tmp = 0;
            
            Queue<Pos> q = new ArrayDeque<>();
            for(int i=0; i<M; i++){
                int idx = combination[i];
                Pos pos = virousLoc.get(idx);
                q.offer(pos);
                visited[pos.y][pos.x] = true;
            }
            
            while(!q.isEmpty()){
                Pos now = q.poll();
                tmp = Math.max(tmp, copyMap[now.y][now.x]);
                
                for(int i=0; i<4; i++){
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];
                    
                    if(ny<0 || ny>N-1 || nx<0 || nx>N-1 || visited[ny][nx]) continue;
                    if(copyMap[ny][nx] == -1) continue;
                    
                    visited[ny][nx] = true;
                    copyMap[ny][nx] = copyMap[now.y][now.x] + 1;
                    q.offer(new Pos(ny, nx));
                }
            }
            
            if(isPossible(visited, copyMap)){
                ans = Math.min(ans, tmp);
            }
        }
    }
    
    static boolean isPossible(boolean[][] visited, int[][] copyMap){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(copyMap[i][j] == -1) continue;
                if(!visited[i][j]) return false;
            }
        }
        return true;
    }
    static void comb(int start, int depth){
        if(depth == M){
            int[] newOrder = new int[M];
            for(int i=0; i<M; i++){
                newOrder[i] = order[i];
            }
            combArray.add(newOrder);
            return;
        }
        for(int i=start; i<virousLoc.size(); i++){
            order[depth] = i;
            comb(i+1, depth+1);
        }
    }
    
    static void copy(int[][] copyMap){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 1){
                    copyMap[i][j] = -1;
                } else copyMap[i][j] = 0;
            }
        }
    }
    static class Pos {
        int y;
        int x;
        
        public Pos (int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
