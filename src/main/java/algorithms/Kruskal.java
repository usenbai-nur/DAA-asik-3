package algorithms;

import java.util.*;

public class Kruskal {
    private static class DisjointSet {
        private final Map<String, String> parent = new HashMap<>();

        public void makeSet(List<String> vertices) {
            for (String v : vertices) parent.put(v, v);
        }

        public String find(String v) {
            if (!parent.get(v).equals(v))
                parent.put(v, find(parent.get(v)));
            return parent.get(v);
        }

        public void union(String v1, String v2) {
            parent.put(find(v1), find(v2));
        }
    }

    public static MSTResult findMST(Graph graph) {
        long start = System.nanoTime();

        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet();
        ds.makeSet(graph.nodes);

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        for (Edge e : edges) {
            operations++;
            String root1 = ds.find(e.from);
            String root2 = ds.find(e.to);

            if (!root1.equals(root2)) {
                mstEdges.add(e);
                totalCost += e.weight;
                ds.union(root1, root2);
            }

            if (mstEdges.size() == graph.nodes.size() - 1) break;
        }

        long end = System.nanoTime();
        return new MSTResult(mstEdges, totalCost, operations, (end - start) / 1_000_000.0);
    }
}
