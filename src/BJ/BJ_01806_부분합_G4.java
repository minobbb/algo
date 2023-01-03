import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 22508kb, 236ms
public class BJ_01806_부분합_G4 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] num = new int[N + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int result = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (start <= N && end <= N) {
            if (sum >= S && result > end - start) {
                result = end - start;
            }
            if (sum < S)
                sum += num[end++];
            else
                sum -= num[start++];
        }
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }
}
