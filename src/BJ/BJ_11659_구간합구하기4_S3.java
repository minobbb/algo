import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 57336kb, 564ms
public class BJ_11659_구간합구하기4_S3 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] prefixSum = new int[N + 1];

        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int answer;
        for (int tc = 0; tc < M; tc++) {
            st = new StringTokenizer(in.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            answer = prefixSum[j] - prefixSum[i - 1];
            sb.append(answer).append('\n');
        }
        System.out.println(sb);

    }
}
