import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static boolean[] visited;
    static int ans;
    public static void main(String[] args) throws  Exception {
        input();
        solve(0, 0, 0);
        System.out.println(ans);
    }

    static void solve(int depth, int total, int coupon){
        if(depth >= N){

            if(ans > total){
                ans = total;
            }
            return ;
        }
        // 현재 최소값보다 같거나 크면 종료
        if(total >= ans){
            return ;
        }

        // 리조트를 안가는 날이면 바로 다음날로 이동
        if(visited[depth+1]) {
            solve(depth + 1, total, coupon);
            return ;
        }

        if(coupon >= 3){
            // 쿠폰 사용 O
            solve(depth + 1, total, coupon-3);
            // 쿠폰 사용 X
            // 1일
            solve(depth+1, total + 10000, coupon);
            solve(depth+3, total + 25000, coupon + 1);
            solve(depth+5, total + 37000, coupon + 2);
        } else {
            solve(depth+1, total + 10000, coupon);
            solve(depth+3, total + 25000, coupon + 1);
            solve(depth+5, total + 37000, coupon + 2);
        }
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        ans = Integer.MAX_VALUE;
        if(M == 0) return ;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int day = Integer.parseInt(st.nextToken());
            visited[day] = true;
        }
    }
}
