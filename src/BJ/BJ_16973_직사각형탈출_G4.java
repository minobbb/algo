package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 118836kb, 932ms
public class BJ_16973_직사각형탈출_G4 {
    static class Loc {
        int x, y, cnt;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Loc(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int N, M, H, W, Sr, Sc, Fr, Fc, result;
    static int[][] map;
    static boolean[][] visited;
    static List<Loc> walls;
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        walls = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    walls.add(new Loc(j, i));
            }
        }

        st = new StringTokenizer(in.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken()); // 시작좌표
        Sc = Integer.parseInt(st.nextToken());
        Fr = Integer.parseInt(st.nextToken()); // 도착좌표
        Fc = Integer.parseInt(st.nextToken());
        result = -1;

        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        Queue<Loc> queue = new LinkedList<>();
        queue.add(new Loc(Sc, Sr, 0));
        visited[Sr][Sc] = true;

        while (!queue.isEmpty()) {
            Loc loc = queue.poll();
            if (loc.x == Fc && loc.y == Fr) {
                result = loc.cnt;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = loc.x + dx[d];
                int ny = loc.y + dy[d];

                if (!isValid(nx, ny) || visited[ny][nx])
                    continue;

                if (canMove(nx, ny)) {
                    queue.add(new Loc(nx, ny, loc.cnt + 1));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    private static boolean canMove(int x, int y) {
        for(Loc wall: walls) {
            if ((x <= wall.x && x + W - 1 >= wall.x) && (y <= wall.y && y + H - 1 >= wall.y))
                return false;
        }
        return true;
    }

    private static boolean isValid(int x, int y) {
        if (x < 1 || x + W - 1 > M || y < 1 || y + H - 1 > N)
            return false;
        return true;
    }
}
