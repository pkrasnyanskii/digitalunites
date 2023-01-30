import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    @Test
    void getVertexToTest() {
        Vertex<Integer> actualVertexFrom = new Vertex<>(1);
        Vertex<Integer> actualVertexTo = new Vertex<>(2);
        Edge<Integer> actualEdge = new Edge<>(actualVertexFrom, actualVertexTo, 1);

        Vertex<Integer> expectedVertexTo = new Vertex<>(2);

        Assertions.assertEquals(expectedVertexTo, actualEdge.vertexTo());
    }

    @Test
    void getVertexFromTest() {
        Vertex<Integer> actualVertexFrom = new Vertex<>(1);
        Vertex<Integer> actualVertexTo = new Vertex<>(2);
        Edge<Integer> actualEdge = new Edge<>(actualVertexFrom, actualVertexTo, 1);

        Vertex<Integer> expectedVertexTo = new Vertex<>(1);

        Assertions.assertEquals(expectedVertexTo, actualEdge.vertexFrom());
    }

    @Test
    void getWeightTest() {
        Vertex<Integer> actualVertexFrom = new Vertex<>(1);
        Vertex<Integer> actualVertexTo = new Vertex<>(2);
        Edge<Integer> actualEdge = new Edge<>(actualVertexFrom, actualVertexTo, 1);

        Assertions.assertEquals(1, actualEdge.weight());
    }

    @Test
    void equalsTest() {
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Edge<Integer> e1 = new Edge<>(v1, v2, 1);
        Edge<Integer> e2 = new Edge<>(v1, v2, 1);
        Edge<Integer> e3 = new Edge<>(v1, v3, 1);

        Assertions.assertEquals(e1, e2);
        Assertions.assertNotEquals(e1, e3);
    }

}