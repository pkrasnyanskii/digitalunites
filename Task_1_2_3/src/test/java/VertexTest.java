import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VertexTest {
    @Test
    void getDistanceTest() {
        Vertex<Integer> actualVertex = new Vertex<>(1);
        actualVertex.setDistance(1);

        Assertions.assertEquals(1, actualVertex.getDistance());
    }

    @Test
    void setDistanceTest() {
        Vertex<Integer> actualVertex = new Vertex<>(1);
        actualVertex.setDistance(1);

        Assertions.assertEquals(1, actualVertex.getDistance());
    }

    @Test
    void getVisitedTest() {
        Vertex<Integer> actualVertex = new Vertex<>(1);
        actualVertex.setVisited(true);

        Assertions.assertTrue(actualVertex.getVisited());
    }

    @Test
    void setVisitedTest() {
        Vertex<Integer> actualVertex = new Vertex<>(1);
        actualVertex.setVisited(true);

        Assertions.assertTrue(actualVertex.getVisited());
    }

    @Test
    void getKeyTest() {
        Vertex<Integer> actualVertex = new Vertex<>(1);

        Assertions.assertEquals(1, actualVertex.getKey());
    }

    @Test
    void equalsTest() {
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(1);
        Vertex<Integer> v3 = new Vertex<>(2);

        Assertions.assertEquals(v1, v2);
        Assertions.assertNotEquals(v1, v3);
    }

    @Test
    void hashCodeTest() {
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(1);
        Vertex<Integer> v3 = new Vertex<>(2);

        Assertions.assertEquals(v1.hashCode(), v2.hashCode());
        Assertions.assertNotEquals(v1.hashCode(), v3.hashCode());
    }
}