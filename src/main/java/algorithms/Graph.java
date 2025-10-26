package algorithms;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Graph {
    public List<String> nodes;
    public List<Edge> edges;

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public static List<Graph> fromJSON(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(new File(filePath).toPath()));
        JSONObject obj = new JSONObject(content);
        JSONArray graphsArray = obj.getJSONArray("graphs");

        List<Graph> graphs = new ArrayList<>();

        for (int i = 0; i < graphsArray.length(); i++) {
            JSONObject g = graphsArray.getJSONObject(i);
            JSONArray nodesJSON = g.getJSONArray("nodes");
            JSONArray edgesJSON = g.getJSONArray("edges");

            List<String> nodes = new ArrayList<>();
            for (int j = 0; j < nodesJSON.length(); j++) nodes.add(nodesJSON.getString(j));

            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < edgesJSON.length(); j++) {
                JSONObject e = edgesJSON.getJSONObject(j);
                edges.add(new Edge(e.getString("from"), e.getString("to"), e.getInt("weight")));
            }

            graphs.add(new Graph(nodes, edges));
        }
        return graphs;
    }
}
