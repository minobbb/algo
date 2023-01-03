import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 84748kb, 644ms
public class BJ_05549_행성탐사_G5 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(in.readLine());
        char[][] map = new char[M][N];
        int[][] jungle = new int[M + 1][N + 1];
        int[][] ocean = new int[M + 1][N + 1];
        int[][] ice = new int[M + 1][N + 1];

        for (int i = 0; i < M; i++) {
            map[i] = in.readLine().toCharArray();
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1];
                ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
                if (map[i - 1][j - 1] == 'J') jungle[i][j]++;
                else if (map[i - 1][j - 1] == 'O') ocean[i][j]++;
                else if (map[i - 1][j - 1] == 'I') ice[i][j]++;
            }
        }

        int a, b, c, d, j, o, i;
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(in.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            j = jungle[c][d] - jungle[a - 1][d] - jungle[c][b - 1] + jungle[a - 1][b - 1];
            o = ocean[c][d] - ocean[a - 1][d] - ocean[c][b - 1] + ocean[a - 1][b - 1];
            i = ice[c][d] - ice[a - 1][d] - ice[c][b - 1] + ice[a - 1][b - 1];

            sb.append(j).append(" ").append(o).append(" ").append(i).append(" \n");
        }

        System.out.println(sb);
    }
}
