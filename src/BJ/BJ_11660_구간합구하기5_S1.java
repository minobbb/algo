import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 125732kb, 1244ms
public class BJ_11660_구간합구하기5_S1 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = prefixSum[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        int x1, x2, y1, y2, answer;
        for (int tc = 0; tc < M; tc++) {
            st = new StringTokenizer(in.readLine());
            y1 = Integer.parseInt(st.nextToken());
            x1 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            answer = 0;

            for (int i = y1; i <= y2; i++) {
                answer += (prefixSum[i][x2] - prefixSum[i][x1 - 1]);
            }
            sb.append(answer).append('\n');
            
        }
        System.out.println(sb);

    }
}
