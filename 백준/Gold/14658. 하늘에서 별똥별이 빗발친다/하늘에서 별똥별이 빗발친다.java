import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int N;
    static int M;
    static int L;
    static int K;
    static int[] select = new int[2];
    static Star[] stars;
    static int ans = 1;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new Star[K];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars[i] = new Star(x, y);
        }

        comb(0, 0);
        System.out.println(K-ans);
    }

    static int countStar(int leftX, int leftY){
        int rightX = leftX + L;
        int rightY = leftY - L;

        int cnt = 0;
        for(int i=0; i<K; i++){
            Star star = stars[i];
            if(leftX <= star.x && star.x <= rightX && rightY <= star.y && star.y <= leftY) cnt++;
        }

        return cnt;
    }

    static void comb(int start, int depth){
        if(depth == 2){
            Star star1 = stars[select[0]];
            Star star2 = stars[select[1]];

            // 왼쪽 변, 위쪽 변을 확정 지을 거임
            int minX = Math.min(star1.x, star2.x);
            int maxY = Math.max(star1.y, star2.y);

            int cnt = countStar(minX, maxY);
            ans = Math.max(ans, cnt);
            return ;
        }
        for(int i=start; i<K; i++){
            select[depth] = i;
            comb(i+1, depth+1);
        }
    }

    static class Star {
        int x;
        int y;

        public Star(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}