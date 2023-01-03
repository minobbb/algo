import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21152kb, 316ms
public class BJ_20002_사과나무_G5 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int[][] map = new int[N + 1][N + 1];
        int[][] prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + map[i][j];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                int len = Math.min(i, j);
                for (int l = 1; l <= len; l++) {
                    int profit = prefixSum[i][j] - prefixSum[i - l][j] - prefixSum[i][j - l] + prefixSum[i - l][j - l];
                    max = Math.max(max, profit);
                }
            }
        }
        System.out.println(max);
    }
}
