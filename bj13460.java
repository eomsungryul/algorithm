import java.util.*;

public class bj13460 {
    static int N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        // 보드 상태 입력
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        // BFS 시작
        System.out.println(bfs(rx, ry, bx, by));
    }

    static int bfs(int rx, int ry, int bx, int by) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{rx, ry, bx, by, 0});
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int crx = cur[0];
            int cry = cur[1];
            int cbx = cur[2];
            int cby = cur[3];
            int depth = cur[4];

            if (depth >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int[] nextRed = move(crx, cry, dx[i], dy[i]);
                int[] nextBlue = move(cbx, cby, dx[i], dy[i]);

                int nrx = nextRed[0];
                int nry = nextRed[1];
                int nbx = nextBlue[0];
                int nby = nextBlue[1];

                if (board[nbx][nby] == 'O') {
                    continue; // 파란 공이 구멍에 빠지면 무조건 실패
                }

                if (board[nrx][nry] == 'O') {
                    return depth + 1; // 빨간 공이 구멍에 빠지면 성공
                }

                // 둘 다 같은 위치에 있을 수 없으므로, 겹쳤다면 더 많이 이동한 공을 한 칸 뒤로
                if (nrx == nbx && nry == nby) {
                    if (Math.abs(nrx - crx) + Math.abs(nry - cry) > Math.abs(nbx - cbx) + Math.abs(nby - cby)) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new int[]{nrx, nry, nbx, nby, depth + 1});
                }
            }
        }

        return -1; // 10번 이내에 성공하지 못하면 -1 반환
    }

    static int[] move(int x, int y, int dx, int dy) {
        while (board[x + dx][y + dy] != '#' && board[x][y] != 'O') {
            x += dx;
            y += dy;
        }
        return new int[]{x, y};
    }
}
