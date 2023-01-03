import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 48804kb, 416ms
public class BJ_17245_약수의합_G4 {

    final static int MAX_SIZE = 1000001;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] num = new long[MAX_SIZE];
        long[] prefixSum = new long[MAX_SIZE];

        Arrays.fill(num, 1);

        for (int i = 2; i < MAX_SIZE; i++) {
            for (int j = 1; i * j < MAX_SIZE; j++) {
                num[i * j] += i;
            }
        }

        for (int i = 1; i < MAX_SIZE; i++) {
            prefixSum[i] = prefixSum[i - 1] + num[i];
        }

        int T = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < T; tc++) {
            sb.append(prefixSum[Integer.parseInt(in.readLine())]).append('\n');
        }
        System.out.println(sb);
    }
}
