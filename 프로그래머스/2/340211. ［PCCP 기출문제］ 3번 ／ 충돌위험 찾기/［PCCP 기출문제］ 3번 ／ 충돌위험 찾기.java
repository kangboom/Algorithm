import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        answer = simulation(points, routes);
        return answer;
    }
    
    static int simulation(int[][] points, int[][] routes){
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        int tmp = 0;
        int robot_n = routes.length;
        int routes_n = routes[0].length;
        // 로봇의 현재 위치를 담을 배열 
        int[][] robot_location = new int[robot_n][2];
        
        // 시작점 초기화
        for(int i=0; i<robot_n; i++){
            robot_location[i][0] = points[routes[i][0] -1][0];
            robot_location[i][1] = points[routes[i][0] -1][1];
        }
                // 종료 여부 표시 
        int[] finish = new int[robot_n];
        Arrays.fill(finish, 1);
        int[] goal = new int[robot_n];
        Arrays.fill(goal, 1);
        
        // 충돌 여부 검사
        tmp = getCrushNum(robot_location, finish, routes_n);
        
        
        while(true){
            // 종료 검증
            int cnt = 0;
            for(int i=0; i<robot_n; i++){
                if(finish[i] >= routes[0].length) {
                    finish[i]++;
                    cnt++;
                }
            }
            if(cnt >= robot_n) break; // 모두 끝났으면 종료
            
            // 끝나지 않은 경우
            // 로봇 차례대로 이동
            for(int i=0; i<robot_n; i++){
                if(finish[i] >= routes[0].length) continue;
                int[] cur = robot_location[i];
                int[] target = points[routes[i][goal[i]]-1];
                
                int d = getDirect(cur, target);
                robot_location[i][0] += dy[d];
                robot_location[i][1] += dx[d];
                
                
                if(robot_location[i][0] == target[0] && robot_location[i][1] == target[1]){
                    // 목표 도착
                    goal[i]++;
                    finish[i]++;
                }
            }
            
            tmp += getCrushNum(robot_location, finish, routes_n);
            
        }
        return tmp;
    }
    
    static int getCrushNum(int[][] location, int[] finish, int routes_n){
        int[][] crushMap = new int[101][101];
        int cnt = 0;
        
        for(int i=0; i<location.length; i++){
            if(finish[i] > routes_n) continue;
            int r = location[i][0];
            int c = location[i][1];
            crushMap[r][c]++;
        }
        
        for(int i=0; i<=100; i++){
            for(int j=0; j<=100; j++){
                if(crushMap[i][j] >= 2) cnt++;
            }
        }
        return cnt;
    }
    
    static int getDirect(int[] cur, int[] target){
        int cr = cur[0];
        int cc = cur[1];
        int tr = target[0];
        int tc = target[1];
        
        if(tr != cr){
            if(tr < cr) return 0;
            else return 1;
        } else {
            if(tc < cc) return 2;
            else return 3;
        }
    }
}