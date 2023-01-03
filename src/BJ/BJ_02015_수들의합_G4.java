package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_02015_수들의합_G4 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] prefixSum = new int[N + 1];
        Map<Integer, Long> map = new HashMap<>();

        long cnt = 0;
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
            if (prefixSum[i] == K) {
                cnt++;
            }

            if (map.containsKey(prefixSum[i] - K)) {
                cnt += map.get(prefixSum[i] - K);
            }

            if (map.containsKey(prefixSum[i])) {
                map.put(prefixSum[i], map.get(prefixSum[i]) + 1L);
            }
            else {
                map.put(prefixSum[i], 1L);
            }
        }

        System.out.println(cnt);
    }
}
