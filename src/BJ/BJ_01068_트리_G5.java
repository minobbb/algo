package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 	13052kb, 88ms
public class BJ_01068_트리_G5 {

    static List<Integer>[] tree;
    static boolean[] visited;
    static int N, root, remove, result;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        visited = new boolean[N];
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        StringTokenizer st = new StringTokenizer(in.readLine());
        root = -1;
        for (int i = 0; i < N; i++) {
            int node = Integer.parseInt(st.nextToken());
            if (node == -1) {
                root = i;
            }
            else {
                tree[node].add(i);
            }
        }
        remove = Integer.parseInt(in.readLine());
        result = 0;
        // root를 지우면 0
        if (remove == root) {
            System.out.println(0);
            System.exit(0);
        }
        dfs(root);
        System.out.println(result);
    }

    private static void dfs(int cur) {
        if (visited[cur])
            return;
        visited[cur] = true;
        for(int next: tree[cur]) {
            if (next == remove) {
                // 지운 위 노드가 리프노드인 경우
                if (tree[cur].size() == 1)
                    result++;
                continue;
            }
            if (tree[next].size() == 0)
                result++;
            else
                dfs(next);
        }
    }
}
