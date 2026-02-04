import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N;
    static int ans = 0;
    static int ansS;
    static int ansT;
    static Node head;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        head = new Node();
        String[] strs = new String[N];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            strs[i] = str;
            insert(i, str);
        }
        if(ans == 0){     
            System.out.println(strs[0] + "\n" + strs[1]);
            return ;
        }
        System.out.println(strs[ansS] + "\n" + strs[ansT]);
    }

    static class Node {
        Node[] nodes = new Node[26];
        int firstVisitedIdx = -1;
    }

    static void insert(int strIdx, String str){

        Node now = head;
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            int idx = ch - 'a'; 
            if(now.nodes[idx] == null){
                now.nodes[idx] = new Node();
                now.nodes[idx].firstVisitedIdx = strIdx; 
            } else {
                int currentLen = i+1;
                if(currentLen > ans){
                    ans = currentLen;
                    ansS = now.nodes[idx].firstVisitedIdx;
                    ansT = strIdx;
                } else if(currentLen == ans){
                    if(now.nodes[idx].firstVisitedIdx < ansS){
                        ansS = now.nodes[idx].firstVisitedIdx;
                        ansT = strIdx;
                    }
                }
            }
            now = now.nodes[idx];
        }
    }

}