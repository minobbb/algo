import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 25900kb
// 248ms
public class BJ_16724_피리부는사나이_G3 {

    static int N, M, cnt;
    static final int visiting = -1;
    static char[][] map;
    static int[][] dp;
    static int[] dx = {0, 0, -1, 1}; // U, D, L, R
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = in.readLine().toCharArray();
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] == 0)
                    dfs(i, j);
            }
        }
        System.out.println(cnt);
    }

    private static int dfs(int y, int x) {
        // 방문중인 곳(루프 만남)
        if (dp[y][x] == visiting) {
            return ++cnt;
        }
        // 이미 방문한 곳
        else if (dp[y][x] != 0) {
            return dp[y][x];
        }
        dp[y][x] = visiting;

        int d = move(map[y][x]);
        int nx = x + dx[d];
        int ny = y + dy[d];

        dp[y][x] = dfs(ny, nx);

        return 0; // 이 리턴값은 의미가 없음.
    }

    private static int move(char arrow) {
        switch (arrow) {
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'L':
                return 2;
            case 'R':
                return 3;
            default:
                return 4;
        }
    }
}
