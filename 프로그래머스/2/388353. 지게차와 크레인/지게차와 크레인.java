import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int N = storage.length;
        int M = storage[0].length();
        int answer = N*M;
        
        char[][] map = new char[N+2][M+2];
        for(int i=0; i<N+2; i++){
            Arrays.fill(map[i], '0');
        }        
        
        // map 초기화
        for(int i=0; i<N; i++){
            String str = storage[i];
            for(int j=0; j<M; j++){
                map[i+1][j+1] = str.charAt(j);
            }
        }
        
        for(String oper : requests){
            char ch = oper.charAt(0);
            if(oper.length() == 1){
                answer -= bfs(ch, map, N, M);
            } else {
                answer -= useCrain(ch, map, N, M);
            }
        }
        
        return answer;
    }
    
    static int bfs(char ch, char[][] map, int N, int M){
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        Queue<int[]> q = new ArrayDeque();
        boolean[][] visited = new boolean[N+2][M+2];
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int cnt = 0;
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int i=0; i<4; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                
                if(ny<0 || ny>N+1 || nx<0 || nx>M+1 || visited[ny][nx]) continue;
                if(map[ny][nx] != '0' && map[ny][nx] != ch) continue;
                
                visited[ny][nx] = true;
                
                if(map[ny][nx] == ch){
                    map[ny][nx] = '0';
                    cnt++;
                    continue;
                }
                q.offer(new int[]{ny, nx});
                
            }
        }
        return cnt;
    }
    
    static int useCrain(char ch, char[][] map, int N, int M){
        int cnt = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(map[i][j] == ch){
                    cnt++;
                    map[i][j] = '0';
                }
            }
        }
        return cnt;
    }
}