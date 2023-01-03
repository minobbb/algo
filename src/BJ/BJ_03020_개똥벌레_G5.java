import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 33768kb, 252ms
public class BJ_03020_개똥벌레_G5 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] bot = new int[H + 1];
        int[] top = new int[H + 1];

        for (int i = 0; i < N / 2; i++) {
            bot[Integer.parseInt(in.readLine())]++;
            top[Integer.parseInt(in.readLine())]++;
        }

        int[] bot_sum = new int[H + 2];
        int[] top_sum = new int[H + 2];
        for (int h = H; h >= 0; h--) {
            // 높이 h일때 장애물 수
            bot_sum[h] = bot_sum[h + 1] + bot[h];
            top_sum[h] = top_sum[h + 1] + top[h];
        }

        int min = N;
        int cnt = 0;
        for (int h = 1; h <= H ; h++) {
            int crush = bot_sum[h] + top_sum[H - h + 1];

            if (min > crush) {
                min = crush;
                cnt = 1;
            }
            else if (min == crush) cnt++;
        }
        System.out.println(min + " " + cnt);
   }
}
