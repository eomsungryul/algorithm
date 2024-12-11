import java.util.*;

public class Solution {
    private static final int[] dx = {0, 0, 1, -1}; // x축 이동: 우, 좌, 하, 상
    private static final int[] dy = {1, -1, 0, 0}; // y축 이동: 우, 좌, 하, 상
    private int rows, cols;
    private boolean[][] visited;

    public int[] solution(String[] maps) {
        rows = maps.length;
        cols = maps[0].length();
        visited = new boolean[rows][cols];

        List<Integer> islands = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    int days = dfs(maps, i, j);
                    islands.add(days);
                }
            }
        }



        if (islands.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(islands);

        return islands.stream().mapToInt(i -> i).toArray();
    }

    private int dfs(String[] maps, int x, int y) {
        visited[x][y] = true;
        int totalDays = Character.getNumericValue(maps[x].charAt(y));

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols) {
                if (!visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    totalDays += dfs(maps, nx, ny);
                }
            }
        }




        return totalDays;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Math.sqrt(25));;

        // 예제 입력 1
        String[] maps1 = {"X591X", "X1X5X", "X231X", "1XXX1"};
        System.out.println("Result 1: " + Arrays.toString(sol.solution(maps1)));

        // 예제 입력 2
        String[] maps2 = {"XXX", "XXX", "XXX"};
        System.out.println("Result 2: " + Arrays.toString(sol.solution(maps2)));

        // 추가 테스트 케이스
        String[] maps3 = {"123", "456", "789"};
        System.out.println("Result 3: " + Arrays.toString(sol.solution(maps3)));

        String[] maps4 = {"1X2", "X9X", "2X1"};
        System.out.println("Result 4: " + Arrays.toString(sol.solution(maps4)));
    }
}
