/**
 * Edge is an element which connect 2 vertexes
 */
public record Edge<T>(Vertex<T> vertexFrom, Vertex<T> vertexTo, int weight) {

}
