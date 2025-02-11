import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] num = new int[K+1];
        int sum = 0;
        for(int i=1; i<=K; i++){
            num[i] = i;
            sum += i;
        }

        if(sum > N){
            System.out.println(-1);
            return ;
        }
        if(sum == N){
            System.out.println(K-1);
            return ;
        }
        
        int i=K;
        while(true){
            num[i] += 1;
            sum += 1;

            if(sum == N){
                break;
            }

            i--;
            if(i==0){
                i=K;
            }
        }

        System.out.println(num[K]-num[1]);
    }
}