package algorithms;

import java.util.List;

public class GraphMetrics {
    private final int vertexCount;
    private final int edgeCount;
    private final int minWeight;
    private final int maxWeight;
    private final double avgWeight;
    private final double density;

    public GraphMetrics(int vertexCount, int edgeCount, int minWeight, int maxWeight, double avgWeight) {
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.avgWeight = avgWeight;

        int maxPossibleEdges = vertexCount * (vertexCount - 1) / 2;
        this.density = maxPossibleEdges > 0 ? (double) edgeCount / maxPossibleEdges : 0.0;
    }

    public static GraphMetrics fromGraph(Graph g) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        double sum = 0.0;

        for (Edge e : g.edges) {
            sum += e.weight;
            min = Math.min(min, e.weight);
            max = Math.max(max, e.weight);
        }

        double avg = g.edges.isEmpty() ? 0.0 : sum / g.edges.size();
        return new GraphMetrics(g.nodes.size(), g.edges.size(), min, max, avg);
    }

    public int getVertexCount() { return vertexCount; }
    public int getEdgeCount() { return edgeCount; }
    public int getMinWeight() { return minWeight; }
    public int getMaxWeight() { return maxWeight; }
    public double getAvgWeight() { return avgWeight; }
    public double getDensity() { return density; }

    public boolean isDense() { return density > 0.5; }
    public boolean isSparse() { return density < 0.3; }

    @Override
    public String toString() {
        return String.format(
                "GraphMetrics{vertices=%d, edges=%d, density=%.2f, weights[min=%d, max=%d, avg=%.2f]}",
                vertexCount, edgeCount, density, minWeight, maxWeight, avgWeight
        );
    }
}
