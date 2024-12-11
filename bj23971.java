import java.util.*;

public class bj23971 {
    static int H, W, N, M;

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        H = sc.nextInt();
//        W = sc.nextInt();
//        N = sc.nextInt();3
//        M = sc.nextInt();

        int[] answer={3};
//        if(answer.length==0) -1:1;
        answer[0]=-1;
//        System.out.println(countMaxMember(H,,W,NM));
        if(answer.length==0){

        }
//        System.out.println(answer.);
         boolean[][] visited;
        visited = new boolean[5][4];
        System.out.println("visited = " + visited);

    }


    private static int countMaxMember(int h, int w, int n, int m) {
        int xMember =( (w-1) / (1+m)) +1;
        int yMember = ((h-1)/(1+n))+1;

        return xMember * yMember;
    }

}
