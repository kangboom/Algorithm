import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] order;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        order = new int[N+1];
        
        backtracking(0);
        System.out.println(sb);
    }
    static void backtracking(int index){
        if(index == M){
            for(int i=0; i<M; i++){
                sb.append(order[i]).append(" ");
            }        
            sb.append('\n');
            return;
        }
        
        for(int i=1; i<=N ; i++){
            if(visited[i]) continue;
            visited[i] = true;
            order[index] = i;
            backtracking(index+1);
            visited[i] = false;
        }
    }
}