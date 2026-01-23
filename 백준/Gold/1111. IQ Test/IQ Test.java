import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        if (N == 1) {
            System.out.println("A");
            return;
        }
        if (N == 2 && nums[1] != nums[2]) {
            System.out.println("A");
            return;
        }
        
        int a, b;
        if (nums[1] == nums[2]) {
            // 앞의 두 수가 같으면 a=1, b=0으로 가정
            a = 1;
            b = 0;
        } else {
            // 앞의 두 수가 다르면 공식을 사용 (N은 무조건 3 이상임이 보장됨)
            // 정수로 나누어떨어지는지 확인
            if ((nums[3] - nums[2]) % (nums[2] - nums[1]) != 0) {
                System.out.println("B");
                return;
            }
            a = (nums[3] - nums[2]) / (nums[2] - nums[1]);
            b = nums[2] - (nums[1] * a);
        }
        
        // 모든 경우 check
        boolean isPossible = true;
        for(int i=2; i<=N; i++){
            if(nums[i] != nums[i-1]*a + b){
                isPossible = false;
                break;
            }
        }
        
        if(isPossible){
            System.out.println(nums[N]*a + b);
        } else {
            System.out.println("B");
        }
    }
}
