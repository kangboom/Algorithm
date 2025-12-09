import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static long[] arr;
    static long[] tree;
    static int N;
    static int M;
    static int K;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        int maxLevel = (int)Math.ceil(Math.log(N) / Math.log(2));
        arr = new long[N+1];
        tree = new long[(int)Math.pow(2,maxLevel+1)];
        
        for(int i=1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        
        setTree(1, 1, N);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if(a == 1){
                arr[b] = c;
                updateTree(1, 1, N, b);
            } else {
                sb.append(getSum(1, b, (int)c, 1, N)).append("\n");
            }
        }
        System.out.println(sb);
    }
    
    static long getSum(int idx, int left, int right, int start, int end){
        if(left > end || right < start){
            return 0;
        }
        
        // 포함되는 경우
        if(left<=start && end <=right){
            return tree[idx];
        }
        
        // 부분만 포함
        int mid = (start + end)/2;
        long leftSum = getSum(idx*2, left, right, start, mid);
        long rightSum = getSum(idx*2+1, left, right, mid+1, end);
        
        return leftSum + rightSum;
    }
    
    static long updateTree(int idx, int left, int right, int targetIdx){
        // 종료 조건
        if(left == right){
            return tree[idx] = arr[left];
        }
        
        int mid = (left + right)/2;
        long leftSum = tree[2*idx];
        long rightSum = tree[2*idx+1];
        if(targetIdx <= mid){
            leftSum = updateTree(idx*2, left, mid, targetIdx);
        } else {
            rightSum = updateTree(idx*2+1, mid+1, right, targetIdx);
        }
        return tree[idx]= leftSum + rightSum;
    }
    
    static long setTree(int idx, int left, int right){
        // leaf 노드
        if(left == right){
            return tree[idx] = arr[left];
        }
        
        int mid = (left+right)/2;
        
        long leftSum = setTree(idx*2, left, mid);
        long rightSum = setTree(idx*2+1, mid+1, right);
        
        return tree[idx] = leftSum + rightSum;
    }
}
