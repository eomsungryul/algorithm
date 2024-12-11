import java.util.*;

public class bj3190 {
    static int N;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    static Map<Integer, Character> directions = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int K = sc.nextInt();
        board = new int[N][N];

        for (int i = 0; i < K; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            board[x][y] = 1; // 사과 위치
        }

        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            int X = sc.nextInt();
            char C = sc.next().charAt(0);
            directions.put(X, C);
        }

        System.out.println(simulate());
    }

    public static int simulate() {
        int time = 0;
        int direction = 0; // 초기 방향: 오른쪽
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[]{0, 0}); // 초기 뱀 위치

        while (true) {
            time++;
            int[] head = snake.peekFirst();
            int nx = head[0] + dx[direction];
            int ny = head[1] + dy[direction];

            // 벽에 부딪히거나 자기 몸에 부딪히는지 확인
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || isCollision(snake, nx, ny)) {
                break;
            }

            // 머리를 다음 위치에 추가
            snake.addFirst(new int[]{nx, ny});

            if (board[nx][ny] == 1) { // 사과가 있는 경우
                board[nx][ny] = 0;
            } else { // 사과가 없으면 꼬리 제거
                snake.pollLast();
            }

            // 방향 전환
            if (directions.containsKey(time)) {
                char turn = directions.get(time);
                if (turn == 'L') {
                    direction = (direction + 3) % 4; // 왼쪽 회전
                } else if (turn == 'D') {
                    direction = (direction + 1) % 4; // 오른쪽 회전
                }
            }
        }

        return time;
    }

    public static boolean isCollision(Deque<int[]> snake, int x, int y) {
        for (int[] body : snake) {
            if (body[0] == x && body[1] == y) {
                return true;
            }
        }
        return false;
    }
}