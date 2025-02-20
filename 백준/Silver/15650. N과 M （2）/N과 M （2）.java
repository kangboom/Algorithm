import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int M;
    static int[] order;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        order = new int[N+1];
        backtracking(1);
        System.out.println(sb);
    }

    static void backtracking(int index){
        if(index == M+1){
            for(int i=1; i<=M; i++){
                sb.append(order[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i=1; i<=N; i++){
            if(i <= order[index-1]) continue;
            order[index] = i;
            backtracking(index+1);
        }
    }
}