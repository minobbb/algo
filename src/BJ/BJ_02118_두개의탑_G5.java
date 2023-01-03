import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 18648ks, 1684ms
public class BJ_02118_두개의탑_G5 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] clockwise = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            clockwise[i] = clockwise[i - 1] + Integer.parseInt(in.readLine());
        }

        int max = 0;
        int sumA, sumB;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                sumA = clockwise[j - 1] - clockwise[i - 1];
                sumB = clockwise[N] - sumA;
                // 두 값이 같다면 최대값.
                if (sumA == sumB) {
                    System.out.println(sumA);
                    System.exit(0);
                }
                max = Math.max(max, Math.min(sumA, sumB));
                if (sumA > sumB) break;
            }
        }
        System.out.println(max);
    }
}
