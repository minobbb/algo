package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 37456kb, 180ms
public class BJ_17090_미로_G3 {

    static int N, M, result;
    static char[][] map;
    static boolean[][] visited, canExit;
    static int[] dx = {0, 0, 1, -1}; // U, D, R, L
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        canExit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = in.readLine().toCharArray();
        }
        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    dfs(j, i);
                }
            }

        }
        System.out.println(result);

    }

    private static void dfs(int x, int y) {
        visited[y][x] = true;
        int d = move(map[y][x]);
        int nx = x + dx[d];
        int ny = y + dy[d];


        if (nx < 0 || nx >= M || ny < 0 || ny >= N || canExit[ny][nx]) {
            canExit[y][x] = true;
            result++;
        }
        else {
            if (visited[ny][nx])
                return;
            dfs(nx, ny);
            if (canExit[ny][nx]) {
                canExit[y][x] = true;
                result++;
            }
        }

    }

    private static int move(char d) {
        switch (d) {
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'R':
                return 2;
            case 'L':
                return 3;
            default:
                return -1;
        }
    }
}
