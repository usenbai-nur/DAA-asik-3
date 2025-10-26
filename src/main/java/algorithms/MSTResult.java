package algorithms;

import java.util.List;

public class MSTResult {
    public List<Edge> mstEdges;
    public int totalCost;
    public int operationsCount;
    public double executionTimeMs;

    public MSTResult(List<Edge> edges, int cost, int ops, double time) {
        this.mstEdges = edges;
        this.totalCost = cost;
        this.operationsCount = ops;
        this.executionTimeMs = time;
    }
}
