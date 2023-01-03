package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 19028kb, 992ms
public class BJ_01749_점수따먹기_G4 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] prefixSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = i; k <= N; k++) {
                    for (int l = j; l <= M; l++) {
                        int sum = prefixSum[k][l] - prefixSum[k - i][l] - prefixSum[k][l - j] + prefixSum[k - i][l - j];
                        result = Math.max(result, sum);
                    }
                }
            }
        }

        System.out.println(result);

    }
}
