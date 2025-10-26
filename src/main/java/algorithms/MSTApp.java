package algorithms;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

public class MSTApp {
    public static void main(String[] args) throws Exception {

        List<Graph> graphs = Graph.fromJSON("src/main/resources/ass_3_input.json");

        JSONArray resultsArray = new JSONArray();

        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);

            MSTResult primResult = Prim.findMST(g);
            MSTResult kruskalResult = Kruskal.findMST(g);

            JSONObject result = new JSONObject();
            result.put("graph_id", i + 1);

            // graph metrics
            GraphMetrics metrics = GraphMetrics.fromGraph(g);
            JSONObject inputStats = new JSONObject();
            inputStats.put("vertices", metrics.getVertexCount());
            inputStats.put("edges", metrics.getEdgeCount());
            inputStats.put("min_weight", metrics.getMinWeight());
            inputStats.put("max_weight", metrics.getMaxWeight());
            inputStats.put("avg_weight", metrics.getAvgWeight());
            inputStats.put("density", metrics.getDensity());
            inputStats.put("type", metrics.isDense() ? "dense" : "sparse");
            result.put("input_stats", inputStats);

            // prim edges
            JSONArray primEdges = new JSONArray();
            for (Edge e : primResult.mstEdges) {
                JSONObject edge = new JSONObject();
                edge.put("from", e.from);
                edge.put("to", e.to);
                edge.put("weight", e.weight);
                primEdges.put(edge);
            }

            // kruskal edges
            JSONArray kruskalEdges = new JSONArray();
            for (Edge e : kruskalResult.mstEdges) {
                JSONObject edge = new JSONObject();
                edge.put("from", e.from);
                edge.put("to", e.to);
                edge.put("weight", e.weight);
                kruskalEdges.put(edge);
            }

            // format execution times
            String primTime = String.format(Locale.US, "%.2f", primResult.executionTimeMs);
            String kruskalTime = String.format(Locale.US, "%.2f", kruskalResult.executionTimeMs);

            // prim json section
            JSONObject primJSON = new JSONObject();
            primJSON.put("mst_edges", primEdges);
            primJSON.put("total_cost", primResult.totalCost);
            primJSON.put("operations_count", primResult.operationsCount);
            primJSON.put("execution_time_ms", Double.valueOf(primTime));

            // kruskal json section
            JSONObject kruskalJSON = new JSONObject();
            kruskalJSON.put("mst_edges", kruskalEdges);
            kruskalJSON.put("total_cost", kruskalResult.totalCost);
            kruskalJSON.put("operations_count", kruskalResult.operationsCount);
            kruskalJSON.put("execution_time_ms", Double.valueOf(kruskalTime));

            result.put("prim", primJSON);
            result.put("kruskal", kruskalJSON);

            resultsArray.put(result);
        }

        JSONObject output = new JSONObject();
        output.put("results", resultsArray);

        // result
        String outputPath = "src/main/resources/ass_3_output.json";
        try (FileWriter file = new FileWriter(outputPath)) {
            file.write(output.toString(4));
        }

        System.out.println("MST results written to: " + outputPath);
    }
}
