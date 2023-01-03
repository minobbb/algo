import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 40946kb
// 412ms
public class BJ_01937_욕심쟁이판다_G3 {

    static int n;
    static int[][] map, dp;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        map = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0)
                    result = Math.max(result, dfs(i, j));
            }
        }
        System.out.println(result);
    }

    private static int dfs(int y, int x) {
        if (dp[y][x] != 0) {
            return dp[y][x];
        }
        dp[y][x] = 1;

        int nx, ny;
        for (int d = 0; d < 4; d++) {
            nx = x + dx[d];
            ny = y + dy[d];

            if (isValid(nx, ny) && canMove(x, y, nx, ny)) {
                dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
            }
        }
        return dp[y][x];
    }

    private static boolean isValid(int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
            return false;
        }
        return true;
    }

    private static boolean canMove(int x, int y, int nx, int ny) {
        if (map[y][x] < map[ny][nx])
            return true;
        return false;
    }
}
