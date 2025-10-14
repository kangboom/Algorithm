import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Pos[] info;
    static List<Integer>[] graph;
    static int[] order = new int[2];
    static String ans;
    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            input();
            comb(0,0);
            solve();
            System.out.println(ans);
        }
    }

    // dfs 탐색 코드
    static void solve(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] visited = new boolean[N+2];
        visited[0] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            if(now == N+1){
                ans="happy";
                return;
            }

            for(int i=0; i<graph[now].size(); i++){
                int next = graph[now].get(i);
                if(visited[next]) continue;
                if(isNext(info[now], info[next])){
                	visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }

    // 탐색할 다음 노드 체크, 현재애서 목적지까지의 거리보다 멀어지는 거리는 제외
    static boolean isNext(Pos now, Pos next){
        Pos dest = info[N+1];
        int standard = Math.abs(dest.y-now.y) + Math.abs(dest.x - now.x);
        int tmp = Math.abs(next.y - now.y) + Math.abs(next.x - now.x);
        if(standard >= tmp){
            return true;
        }
        return false;
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        graph = new List[N+2];
        info = new Pos[N+2];
        for(int i=0; i<N+2; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            info[i] = new Pos(y, x);
        }
        ans = "sad";
    }

    /*
    그래프 그리기
     */
    static void comb(int start, int depth){
        if(depth == 2){
            int index1 = order[0];
            int index2 = order[1];
            Pos p1 = info[index1];
            Pos p2 = info[index2];

            // 위치 계산: 1000m 안으로 갈 수 있는 것만 그래프에 추가
            int distance = Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);
            if(distance <= 1000){
                graph[index1].add(index2);
                graph[index2].add(index1);
            }
            return ;
        }

        for(int i=start; i<N+2; i++){
            order[depth] = i;
            comb(i+1, depth+1);
        }
    }

    static class Pos {
        int y;
        int x;

        Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}