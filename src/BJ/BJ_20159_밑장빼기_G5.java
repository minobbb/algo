import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 22832kb, 228ms
public class BJ_20159_밑장빼기_G5 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] oddPerfixSum = new int[N / 2 + 1];
        int[] evenPerfixSum = new int[N / 2 + 1];

        for (int i = 1; i <= N / 2; i++) {
            oddPerfixSum[i] = oddPerfixSum[i - 1] + Integer.parseInt(st.nextToken());
            evenPerfixSum[i] = evenPerfixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 처음부터 밑장빼기 초기값
        int max = evenPerfixSum[N / 2];
        for (int i = 1; i <= N / 2; i++) {

            // 내 패 밑장빼기
            int a = oddPerfixSum[i] + (evenPerfixSum[N / 2] - evenPerfixSum[i]);
            max = Math.max(max, a);
            // 상대방 패 밑장빼기
            int b = oddPerfixSum[i] + (evenPerfixSum[N / 2 - 1] - evenPerfixSum[i - 1]);
            max = Math.max(max, b);
        }
        System.out.println(max);
    }
}
