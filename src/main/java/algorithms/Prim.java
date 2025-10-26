package algorithms;

import java.util.*;

public class Prim {
    public static MSTResult findMST(Graph graph) {
        long start = System.nanoTime();

        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String startNode = graph.nodes.get(0);
        visited.add(startNode);

        for (Edge e : graph.edges)
            if (e.from.equals(startNode) || e.to.equals(startNode)) pq.add(e);

        while (!pq.isEmpty() && visited.size() < graph.nodes.size()) {
            Edge e = pq.poll();
            operations++;

            String nextNode = visited.contains(e.from) ? e.to : e.from;
            if (visited.contains(nextNode)) continue;

            mstEdges.add(e);
            totalCost += e.weight;
            visited.add(nextNode);

            for (Edge edge : graph.edges)
                if ((edge.from.equals(nextNode) && !visited.contains(edge.to)) ||
                        (edge.to.equals(nextNode) && !visited.contains(edge.from)))
                    pq.add(edge);
        }

        long end = System.nanoTime();
        return new MSTResult(mstEdges, totalCost, operations, (end - start) / 1_000_000.0);
    }
}
