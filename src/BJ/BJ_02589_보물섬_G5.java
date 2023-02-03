package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 292700kb, 452ms
public class BJ_02589_보물섬_G5 {

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static int[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static int r, c, result;


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new int[r][c];
        result = 0;

        for (int i = 0; i < r; i++) {
            map[i] = in.readLine().toCharArray();
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'L') {
                    visited = new int[r][c];
                    bfs(j, i);
                }
            }
        }
        System.out.println(result);
    }


    private static void bfs(int startX, int startY) {
        Queue<Loc> queue = new LinkedList<>();

        queue.add(new Loc(startX, startY));
        visited[startY][startX] = 1;

        while (!queue.isEmpty()) {
            Loc loc = queue.poll();
            int x = loc.x;
            int y = loc.y;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= c || ny >= r || map[ny][nx] == 'W' || visited[ny][nx] != 0)
                    continue;
                visited[ny][nx] = visited[y][x] + 1;
                queue.add(new Loc(nx, ny));
                result = Math.max(result, visited[ny][nx] - 1);
            }
        }
    }
}
