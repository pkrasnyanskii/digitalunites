import java.util.Objects;

/**
 * Vertex is a basic element of a graph that stores information.
 */
public class Vertex<T> {
    private final T key;
    private boolean visited;
    private int distance;

    /**
     * Constructor for vertex.
     *
     * @param key key of vertex
     */
    public Vertex(T key) {
        this.key = key;
        this.visited = false;
        this.distance = 0;
    }

    /**
     * Get the distance from current vertex to this one.
     *
     * @return distance
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Set new distance from current vertex to this one.
     *
     * @param newDistance new distance
     */
    public void setDistance(int newDistance) {
        this.distance = newDistance;
    }

    /**
     * Get the visit status of this vertex.
     *
     * @return visit status
     */
    public boolean getVisited() {
        return this.visited;
    }

    /**
     * Set the visit status of this vertex.
     *
     * @param newStatus new visit status
     */
    public void setVisited(boolean newStatus) {
        this.visited = newStatus;
    }

    /**
     * Get the key of this vertex.
     *
     * @return key
     */
    public T getKey() {
        return this.key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex<?> vertex = (Vertex<?>) o;
        return visited == vertex.visited && Objects.equals(key, vertex.key)
                && Objects.equals(distance, vertex.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, distance, visited);
    }

}