import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

class Main {

    static ArrayList<ArrayList<Integer>> answer;
    static int N;
    static int minCost = Integer.MAX_VALUE;
    static Food[] foods;
    static boolean[] visited;
    static Food standard;

    // 정렬 문제
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        int mp = Integer.parseInt(st.nextToken());
        int mf = Integer.parseInt(st.nextToken());
        int ms = Integer.parseInt(st.nextToken());
        int mv = Integer.parseInt(st.nextToken());
        
        standard = new Food(mp, mf, ms, mv, 0);

        foods = new Food[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            foods[i] = new Food(p, f, s, v, cost); 
        }

        solve(1);

        if(minCost == Integer.MAX_VALUE){
            System.out.println(-1);
            return ;
        }
        
        Collections.sort(answer, (o1, o2) -> {
            int minLen = Math.min(o1.size(), o2.size());
            for(int i=0; i<minLen; i++){
                int num1 = o1.get(i);
                int num2 = o2.get(i);

                if(num1 != num2) return num1 - num2;
            }
            return o1.size() - o2.size();
        });

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<answer.get(0).size(); i++){
            sb.append(answer.get(0).get(i)).append(" ");
        }
        System.out.println(minCost);
        System.out.println(sb);
    }

    static boolean isPossible(int p, int f, int s, int v){
        if(p>=standard.p && f>=standard.f && s>=standard.s && v>=standard.v) return true;
        return false;
    }
    
    static void solve(int depth){

        if(depth == N+1){
            int tp = 0;
            int tf = 0;
            int ts = 0;
            int tv = 0;
            int tmpCost = 0;
            ArrayList<Integer> tmpList = new ArrayList<>();
            for(int i=1; i<=N; i++){
                if(visited[i]){
                    tmpList.add(i);
                    tp += foods[i].p;
                    tf += foods[i].f;
                    ts += foods[i].s;
                    tv += foods[i].v;
                    tmpCost += foods[i].cost;
                }
                
                if(isPossible(tp, tf, ts, tv)){
                    if(minCost == tmpCost){
                       answer.add(tmpList);     
                    } else if(minCost > tmpCost){
                        minCost = tmpCost;
                        answer = new ArrayList<>();
                        answer.add(tmpList);
                    }
                }
            }
            return ;
        }
        
        // 선택한 경우
        visited[depth] = true;
        solve(depth+1);
        
        visited[depth] = false;
        // 선택안한 경우
        solve(depth+1);
    }

    static class Food {
        int p;
        int f;
        int s;
        int v;
        int cost; 
        
        Food(int p, int f, int s, int v, int cost){
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.cost = cost;
        }
    }
}