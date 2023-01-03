import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 15048kb
// 208ms
public class BJ_02638_치즈_G3 {

    static int N, M;
    static int[][] cheese;
    static boolean[][] air;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while (true) {
            // 치즈 다 녹았는지 체크
            if (checkCheese())
                break;
            // 외부 공기 체크
            air = new boolean[N][M];
            outsideAir(0, 0);
            // 치즈 녹이기
            melt();
            // 시간 1 증가
            time++;
        }

        System.out.println(time);
    }

    private static void outsideAir(int x, int y) {
        int nx, ny;
        for (int d = 0; d < 4; d++) {
            nx = x + dx[d];
            ny = y + dy[d];
            // 범위를 벗어나지 않고, 공기 이며, 외부 공기 배열에 체크되지 않은 경우
            if (isValid(nx, ny) && cheese[ny][nx] == 0 && !air[ny][nx]) {
                air[ny][nx] = true;
                outsideAir(nx, ny);
            }
        }
    }


    private static void melt() {
        int nx, ny, cnt;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) {
                    cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        nx = j + dx[d];
                        ny = i + dy[d];
                        // 외부 공기에 접촉
                        if (air[ny][nx])
                            cnt++;
                        // 치즈가 녹음
                        if (cnt >= 2) {
                            cheese[i][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean checkCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1)
                    return false;
            }
        }

        return true;
    }

    private static boolean isValid(int nx, int ny) {
        if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
            return false;
        }
        return true;
    }
}

