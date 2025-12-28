import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            HashMap<Integer, Integer> cnt = new HashMap<>();
            PriorityQueue<Integer> minpq = new PriorityQueue<>();
            PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
            
            int K = Integer.parseInt(br.readLine());
            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char op = st.nextToken()
                        .charAt(0);
                int opn = Integer.parseInt(st.nextToken());
                
                // 인풋 명령
                if (op == 'I') {
                    minpq.offer(opn);
                    maxpq.offer(opn);
                    cnt.put(opn, cnt.getOrDefault(opn, 0) + 1);
                } else {
                    // 최대값
                    if (opn == 1) {
                        while (!maxpq.isEmpty()) {
                            int max = maxpq.poll();
                            if (cnt.get(max) == 0) {
                                continue;
                            }
                            cnt.put(max, cnt.get(max) - 1);
                            break;
                        }
                    } else {
                        while (!minpq.isEmpty()) {
                            int min = minpq.poll();
                            if (cnt.get(min) == 0) {
                                continue;
                            }
                            cnt.put(min, cnt.get(min) - 1);
                            break;
                        }
                    }
                }
            }
            boolean isMax = false;
            boolean isMin = false;
            int max = 0;
            int min = 0;
            // 정답 출력
            while (!maxpq.isEmpty()) {
                max = maxpq.poll();
                if (cnt.get(max) == 0) {
                    continue;
                }
                isMax = true;
                break;
            }
            while (!minpq.isEmpty()) {
                min = minpq.poll();
                if (cnt.get(min) == 0) {
                    continue;
                }
                isMin = true;
                break;
            }
            
            if (isMax && isMin) {
                System.out.println(max + " " + min);
            } else if (isMax) {
                System.out.println(max + " " + max);
            } else {
                System.out.println("EMPTY");
            }
        }
    }
}
