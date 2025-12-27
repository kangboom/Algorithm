import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int[] dice = {0, 0, 0, 0, 0, 0, 0};
    static int[] info = {1, 2, 5, 4, 3};
    static int[] dy = {0, 0, 0, -1, 1};
    static int[] dx = {0, 1, -1, 0, 0};
    
    static int[][] map;
    static int N;
    static int M;
    
    static Pos loc;
    
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        loc = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        int K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int d = Integer.parseInt(st.nextToken());
            int nr = loc.r + dy[d];
            int nc = loc.c + dx[d];
            
            if(nr <0 || nr >N-1 || nc<0 || nc>M-1) continue;
            loc.r = nr;
            loc.c = nc;
            roll(d);
        }
        
        System.out.println(sb);
    }
    
    static void roll(int d) {
        int now = info[0]; // 현재 밑면
        info[0] = info[d];
        sb.append(dice[7-info[0]]).append("\n");
        copy();
        
        if(d==1){
            info[1] = 7-now;
            info[2] = now;
        } else if(d==2){
            info[1] = now;
            info[2] = 7-now;
        } else if(d==3){
            info[3] = 7-now;
            info[4] = now;
        } else {
            info[3] = now;
            info[4] = 7-now;
        }
    }
    
    static void copy(){
        int num = map[loc.r][loc.c];
        if(num == 0){
            map[loc.r][loc.c] = dice[info[0]];
        } else {
            dice[info[0]] = map[loc.r][loc.c];
            map[loc.r][loc.c] = 0;
        }
    }
    
    static class Pos {
        
        int r;
        int c;
        
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
