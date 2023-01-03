import java.io.*;
import java.util.*;

// 129788kb
// 580ms
public class BJ_02573_빙산_G4 {

    static class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static int M, N;
    static int[][] map;
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        int icebergCnt = 0;
        int time = 0;
        while ((icebergCnt = countSeparate()) < 2) {
            if (icebergCnt == 0) {
                time = 0;
                break;
            }
            melt();
            time++;
        }
        return time;
    }

    private static int countSeparate() {
        boolean[][] visited = new boolean[M][N];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[j][i] > 0 && !visited[j][i]) {
                    dfs(j, i, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(int j, int i, boolean[][] visited) {
        visited[j][i] = true;

        for (int d = 0; d < 4; d++) {
            int x = j + dx[d];
            int y = i + dy[d];

            if (x >= 0 && x < M && y >= 0 && y < N && map[x][y] > 0 && !visited[x][y]) {
                dfs(x, y, visited);
            }
        }
    }

    private static void melt() {
        Queue<Loc> queue = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[j][i] > 0) {
                    queue.add(new Loc(j, i));
                    visited[j][i] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Loc loc = queue.poll();
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int x = loc.x + dx[d];
                int y = loc.y + dy[d];

                if (x >= 0 && x < M && y >= 0 && y < N && map[x][y] == 0 && !visited[x][y])
                    cnt++;
            }
            map[loc.x][loc.y] = map[loc.x][loc.y] - cnt <= 0 ? 0 : map[loc.x][loc.y] - cnt;
        }
    }
}
