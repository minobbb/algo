import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 29492kb
// 1652ms
public class BJ_01967_트리의지름_G4 {

    static class Node {
        int dest, weight;

        Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

    }

    static ArrayList<Node>[] tree;
    static int n, result;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        int start = 1;
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(st.nextToken()); // 부모 노드 번호
            int child = Integer.parseInt(st.nextToken()); // 자식 노드 번호
            int weight = Integer.parseInt(st.nextToken()); // 가중치

            tree[parent].add(new Node(child, weight));
            tree[child].add(new Node(parent, weight));

            if (i == n - 2) { // 마지막 입력
                // 리프 노드 시작점을 찾아 저장
                start = parent + 1;
            }
        }


        boolean[] visited = new boolean[n + 1];
        result = 0;
        for (int i = start; i <= n; i++) {
            visited[i] = true;
            dfs(i, 0, visited);
            visited[i] = false;
        }
        System.out.println(result);
    }

    private static void dfs(int start, int length, boolean[] visited) {
        if (result < length) {
            result = length;
        }

        for (Node node: tree[start]) {
            if (!visited[node.dest]) {
                visited[node.dest] = true;
                dfs(node.dest, length + node.weight, visited);
                visited[node.dest] = false;
            }
        }
    }
}