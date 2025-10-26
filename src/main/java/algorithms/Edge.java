package algorithms;

public class Edge implements Comparable<Edge> {
    public String from, to;
    public int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("{\"from\":\"%s\", \"to\":\"%s\", \"weight\":%d}", from, to, weight);
    }
}
