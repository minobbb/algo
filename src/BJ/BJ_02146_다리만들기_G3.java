package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//135516kb, 244ms
public class BJ_02146_다리만들기_G3 {

    static class Loc {
        int x, y, cnt;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Loc(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int N, result;
    static int[][] map, sepMap;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        sepMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 분리
        separateIsland();
        // 최단거리 찾기
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sepMap[i][j] != 0) {
                    visited = new boolean[N][N];
                    bfs(j, i);
                }
            }
        }

        System.out.println(result);
    }

    private static void bfs(int x, int y) {
        int curIsland = sepMap[y][x];
        Queue<Loc> queue = new LinkedList<>();
        queue.add(new Loc(x, y, 0));
        visited[y][x] = true;
        while (!queue.isEmpty()) {
            Loc loc = queue.poll();

            if (loc.cnt > result)
                return;

            for (int d = 0; d < 4; d++) {
                int nx = loc.x + dx[d];
                int ny = loc.y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || sepMap[ny][nx] == curIsland)
                    continue;

                if (sepMap[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    queue.add(new Loc(nx, ny, loc.cnt + 1));
                }
                else { // 다른 섬 도착
                    result = Math.min(result, loc.cnt);
                }
            }
        }
    }

    private static void separateIsland() {
        int idx = 1; // 섬을 구분하기 위한 인덱스
        visited = new boolean[N][N];
        Queue<Loc> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    queue.add(new Loc(j, i));
                    visited[i][j] = true;
                    sepMap[i][j] = idx;
                    while (!queue.isEmpty()) {
                        Loc loc = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = loc.x + dx[d];
                            int ny = loc.y + dy[d];
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] == 0 || visited[ny][nx])
                                continue;

                            sepMap[ny][nx] = idx;
                            visited[ny][nx] = true;
                            queue.add(new Loc(nx, ny));
                        }
                    }
                    idx++;
                }
            }
        }
    }

}
