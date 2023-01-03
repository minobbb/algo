import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// subtask : 100점
// 26544kb, 240ms
public class BJ_21758_꿀따기_G5 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] honey = new int[N + 1];
        int[] left = new int[N + 1];
        int[] right = new int[N + 2];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            left[i] = left[i - 1] + honey[i];
            right[N - i + 1] = right[N - i + 2] + honey[N - i + 1];
        }

        long bee1, bee2;
        long max = 0;
        // 꿀통 맨 왼쪽
        for (int i = N - 1; i >= 2; i--) {
            bee1 = right[1] - honey[N] - honey[i];
            bee2 = right[1] - right[i];
            max = Math.max(max, bee1 + bee2);
        }

        // 꿀통 맨 오른쪽
        for (int i = 2; i <= N - 1; i++) {
            bee1 = left[N] - honey[1] - honey[i];
            bee2 = left[N] - left[i];
            max = Math.max(max, bee1 + bee2);
        }

        // 꿀통 가운데
        for (int i = 2; i <= N - 1; i++) {
            bee1 = left[i] - honey[1];
            bee2 = right[i] - honey[N];
            max = Math.max(max, bee1 + bee2);
        }

        System.out.println(max);
    }
}
