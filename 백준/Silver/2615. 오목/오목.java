import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] arr;
    static int answerY = 0;
    static int answerX = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        arr = new int[20][20];
        for(int i=1; i<20; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<20; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        fiveMock();
    }
    
    static void fiveMock(){
        for(int i=1; i<20; i++){
            for(int j=1; j<20; j++){
                if(arr[i][j] == 0) continue;
                search(i, j, -1 , 1); // 오른쪽 위 (y, x, dy, dx)
                search(i, j, 0 , 1); // 오른쪽 
                search(i, j, 1 , 1); // 오른쪽 아래
                search(i, j, 1 , 0); // 아래
            }
        }
        if(answerY == 0 &&  answerX == 0){
            System.out.println(0);    
        }
        else{
            System.out.println(arr[answerY][answerX]);
            System.out.println(answerY + " " + answerX);
        }
        
    }
    static void search(int ny, int nx, int dy, int dx){

        int cnt = 1;
        int now = arr[ny][nx];
        int y = ny + dy;
        int x = nx + dx;
        
        while(true){
            if(y<1 || y>19 || x<1 || x>19) break;
            if(cnt > 5) break;
            if(arr[y][x] != now) break;
            
            cnt += 1;
            y = y + dy;
            x = x + dx;
        }

        int reverseCnt = 1;
        y = y - 2* dy;
        x = x - 2* dx;
        while(true){ // 반대 뱡향으로 체크
            if(y<1 || y>19 || x<1 || x>19) break;
            if(reverseCnt > 5) break;
            if(arr[y][x] != now) break;
            
            reverseCnt += 1;
            y = y - dy;
            x = x - dx;
        }

        if(cnt == 5 && reverseCnt == 5){
            answerY = ny;
            answerX = nx;
        }
        
    }
}
