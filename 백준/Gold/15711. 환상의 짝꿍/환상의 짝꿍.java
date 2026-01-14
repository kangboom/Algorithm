import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static final int MAX = 2000000;
    static boolean[] isPrime = new boolean[MAX + 1];
    static List<Integer> primes = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long s = a + b;
            
            // 짝수
            if((s & 1) == 0){
                if(s == 2) sb.append("NO");
                else sb.append("YES");
            } else {
                if(s-2 <= MAX){
                    if(isPrime[(int)s-2]) sb.append("YES");
                    else sb.append("NO");
                } else {
                    boolean isPossible = true;
                    for(int prime : primes){
                        if((long)prime *prime > s-2) break;
                        if((s-2)%prime == 0) {
                            isPossible = false;
                            break;
                        }
                    }
                    if(isPossible) sb.append("YES");
                    else sb.append("NO");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
