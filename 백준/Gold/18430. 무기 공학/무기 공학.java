import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    static int M;
    static int[][] tree;
    static boolean[][] visited;
    static int ans = 0;
 
    static int[][] dy = {
            {-1, 0},
            {-1, 0},
            {1, 0},
            {1,0}
    };
    static int[][] dx = {
            {0, -1},
            {0, 1},
            {0, 1},
            {0, -1}
    };
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        backtracking(0, 0, 0);
        System.out.println(ans);
    }
    
    static void backtracking(int y, int x, int sum){
        if(y>=N) {
            ans = Math.max(ans, sum);
            return ;
        }
        
        // 다음 탐색 지점
        int ny = y;
        int nx = x+1;
        if(nx >= M){
            ny += 1;
            nx = 0;
        }
        
        // 현재 위치를 중심으로 부메랑 안만들기
        backtracking(ny, nx, sum);
        
        if(!visited[y][x]) {
            // 현재 위치를 중심으로 부메랑 만들기
            for (int i = 0; i < 4; i++) {
                int ny1 = y + dy[i][0];
                int nx1 = x + dx[i][0];
                
                int ny2 = y + dy[i][1];
                int nx2 = x + dx[i][1];
                
                if (ny1 < 0 || ny1 > N - 1 || nx1 < 0 || nx1 > M - 1 || ny2 < 0 || ny2 > N - 1 || nx2 < 0 || nx2 > M - 1 || visited[ny1][nx1] || visited[ny2][nx2])
                    continue;
                visited[ny1][nx1] = true;
                visited[ny2][nx2] = true;
                visited[y][x] = true;
                backtracking(ny, nx, sum + tree[ny1][nx1] + tree[ny2][nx2] + tree[y][x]*2);
                visited[ny1][nx1] = false;
                visited[ny2][nx2] = false;
                visited[y][x] = false;
                
            }
        }
    }
}
