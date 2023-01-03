import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 130072kb
// 656ms
public class BJ_07569_토마토_G5 {

    static class Loc {
        int x, y, z, cnt;

        public Loc(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    static int M, N, H;
    static int[][][] map;
    static int[] dx = {0, 0, 1, -1, 0, 0}; // 위, 아래, 오른쪽, 왼쪽, 앞, 뒤
    static int[] dy = {0, 0, 0, 0, 1, -1};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static Queue<Loc> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[M + 1][N + 1][H + 1];

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k = 1; k <= M; k++) {
                    map[k][j][i] = Integer.parseInt(st.nextToken());
                    if (map[k][j][i] == 1)
                        queue.add(new Loc(k, j, i, 0));
                }
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        int cnt = 0;

        while (!queue.isEmpty()) {
            Loc loc = queue.poll();
            cnt = loc.cnt;
            for (int d = 0; d < 6; d++) {
                int x = loc.x + dx[d];
                int y = loc.y + dy[d];
                int z = loc.z + dz[d];
                if (rangeCheck(x, y, z) && map[x][y][z] == 0) {
                    // 토마토 익히기
                    map[x][y][z] = 1;
                    queue.add(new Loc(x, y, z, cnt + 1));
                }
            }
        }

        // 안익은 토마토 있는지 체크
        return checkTomato() ? cnt : -1;
    }

    private static boolean rangeCheck(int x, int y, int z) {
        if (x > 0 && x <= M && y > 0 && y <= N && z > 0 && z <= H)
            return true;
        return false;
    }

    private static boolean checkTomato() {
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= H; k++) {
                    if (map[i][j][k] == 0)
                        return false;
                }
            }
        }
        return true;
    }
}
