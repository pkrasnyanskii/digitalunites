import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Graph is a collection of nodes also called vertices which are connected between one another.
 * Each connection between two vertices is called an edge (sometimes called a branch).
 */
public class Graph<T> {
    private static final int INF = Integer.MAX_VALUE;
    private final Map<T, Vertex<T>> vertices = new HashMap<>();
    private final Map<T, List<Edge<T>>> edges = new HashMap<>();

    /**
     * Empty graph.
     */
    public Graph() {
    }

    /**
     * Graph represented as a matrix of incidence.
     *
     * @param vertexArray array of vertices
     * @param matrix      matrix of incidence
     * @param edgeCount   amount of edges
     */
    public Graph(T[] vertexArray, int[][] matrix, int edgeCount) {

        for (T keyVertex : vertexArray) {
            addVertex(keyVertex);
        }

        for (int i = 0; i < edgeCount; i++) {
            int weight = 0;
            T keyVertexFrom = null;
            T keyVertexTo = null;
            for (int j = 0; j < vertexArray.length; j++) {
                if (matrix[i][j] > 0) {
                    keyVertexTo = vertexArray[j];
                    weight = matrix[i][j];
                }
                if (matrix[i][j] < 0) {
                    keyVertexFrom = vertexArray[j];
                }
            }
            if (keyVertexFrom != null && keyVertexTo != null) {
                addEdge(vertices.get(keyVertexFrom), vertices.get(keyVertexTo), weight);
            }
        }
    }

    /**
     * Graph represented as a matrix of adjacency.
     *
     * @param vertexArray array of vertices
     * @param matrix      matrix of adjacency
     */
    public Graph(T[] vertexArray, int[][] matrix) {

        for (T keyVertex : vertexArray) {
            addVertex(keyVertex);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    addEdge(vertices.get(vertexArray[j]),
                            vertices.get(vertexArray[i]),
                            matrix[i][j]);
                }
            }
        }
    }

    /**
     * Graph represented as a list of adjacency.
     *
     * @param vertexArray array of existing vertexes
     * @param vertexList  list of adjacency
     * @param weights     list of weights
     */
    public Graph(T[] vertexArray, List<T>[] vertexList, List<Integer>[] weights) {
        int len = vertexArray.length;
        for (T keyVertex : vertexArray) {
            addVertex(keyVertex);
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < vertexList[i].size(); j++) {
                addEdge(vertices.get(vertexArray[i]),
                        vertices.get(vertexList[i].get(j)),
                        weights[i].get(j));
            }
        }
    }

    /**
     * Dijkstra algorithm.
     *
     * @param keyStartVertex key of vertex to start from
     * @return array of vertices with calculated path
     */
    public List<Vertex<T>> dijkstra(T keyStartVertex) {

        Vertex<T> startVertex = vertices.get(keyStartVertex);
        List<Vertex<T>> result = new ArrayList<>();

        vertices.forEach((k, v) -> {
            v.setDistance(INF);
            v.setVisited(false);
            result.add(v);
        });

        vertices.get(startVertex.getKey()).setDistance(0);

        Vertex<T> currentVertex;
        while ((currentVertex = vertices.get(existNotVisited(result))) != null) {

            vertices.get(currentVertex.getKey()).setVisited(true);
            List<Edge<T>> currentEdgeList = edges.get(currentVertex.getKey());

            for (var edge : currentEdgeList) {
                Vertex<T> vertexTo = vertices.get(edge.vertexTo().getKey());
                vertexTo.setDistance(
                        Math.min(
                                currentVertex.getDistance() + edge.weight(),
                                vertexTo.getDistance())
                );
            }
            result.sort(Comparator.comparingInt(Vertex::getDistance));
        }
        return result;
    }

    /**
     * Helper method to define if there are exist not visited vertices yet.
     *
     * @param result array of vertices
     * @return key of not visited vertex
     */
    private T existNotVisited(List<Vertex<T>> result) {
        for (var vertex : result) {
            if (!vertex.getVisited()) {
                return vertex.getKey();
            }
        }
        return null;
    }

    /**
     * Add new vertex to graph.
     *
     * @param key key of new vertex
     */
    public void addVertex(T key) {
        Vertex<T> vertex = new Vertex<>(key);
        vertices.put(key, vertex);
        edges.put(key, new ArrayList<>());
    }

    /**
     * Remove a vertex from graph.
     *
     * @param key key of removed vertex
     */
    public void removeVertex(T key) {
        edges.remove(key);
        vertices.remove(key);
    }

    /**
     * Add new edge to graph.
     *
     * @param vertexFrom vertex that edge exit
     * @param vertexTo   vertex that edge enter
     * @param weight     weight of edge
     */
    public void addEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo, int weight) {
        Edge<T> edge = new Edge<>(vertexFrom, vertexTo, weight);
        edges.get(vertexFrom.getKey()).add(edge);
    }

    /**
     * Remove an edge from graph.
     *
     * @param key key of removed edge
     */
    public void removeEdge(T key) {
        edges.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Graph)) {
            return false;
        }
        Graph<?> graph = (Graph<?>) o;
        return Objects.equals(vertices, graph.vertices)
                && Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, edges);
    }
}