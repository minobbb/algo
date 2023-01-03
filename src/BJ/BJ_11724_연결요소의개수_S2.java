import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 115232kb
// 456ms
public class BJ_11724_연결요소의개수_S2 {

    static int N, M;
    static boolean[][] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u][v] = true;
            adj[v][u] = true;
        }

        visited = new boolean[N + 1];
        int result = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i);
                dfs(i);
                result++;
            }
        }
        System.out.println(result);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        // 방문 처리
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int i = 1; i <= N; i++) {
                if (adj[v][i] && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    static void dfs(int start) {
        visited[start] = true;

        for (int i = 1; i <= N; i++) {
            if (adj[start][i] && !visited[i])
                dfs(i);
        }

    }
}
