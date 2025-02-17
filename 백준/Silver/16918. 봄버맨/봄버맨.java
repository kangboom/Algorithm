import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static char[][] map;
    static int R;
    static int C;
    static int N;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'O'){
                    q.offer(new int[]{i, j});
                }
            }
        }

        for(int t=2; t<= N; t++){
            if(t%2 ==0){
                setBomb();   
            }
                
            else{
                bfs();
                for(int i=0; i<R; i++){
                    for(int j=0; j<C; j++){
                        if(map[i][j] == 'O'){
                            q.offer(new int[]{i, j});
                        }  
                    }
                }
            }
        }

            
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        
    }

    static void setBomb(){
        for(char[] temp : map){
            Arrays.fill(temp, 'O');
        } 
    }

    static void bfs(){
        while(!(q.isEmpty())){
            int[] now = q.poll();
            map[now[0]][now[1]] = '.';
            for(int i=0; i<4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if( nx > C-1 || nx < 0 || ny > R-1 || ny < 0) continue;
                map[ny][nx] = '.';
            }
        }
    }
}