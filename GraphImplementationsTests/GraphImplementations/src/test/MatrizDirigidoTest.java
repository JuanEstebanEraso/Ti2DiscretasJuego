import static org.junit.jupiter.api.Assertions.*;

import com.example.bomberman.model.MatrizDirigido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.bomberman.model.Vertex;


import java.util.ArrayList;
import java.util.List;

public class MatrizDirigidoTest {

    private MatrizDirigido<Integer> graphWithVertices;
    private MatrizDirigido<Integer> emptyGraph;

    @BeforeEach
    public void setUp1() {
        graphWithVertices = new MatrizDirigido<>(5);
        for (int i = 0; i < 5; i++) {
            graphWithVertices.addVertex(i);
        }
    }

    @BeforeEach
    public void setUp2() {
        emptyGraph = new MatrizDirigido<>(0);
    }

    @Test
    public void testAddVertexToEmptyGraph() {
        setUp2();
        emptyGraph.addVertex(1);
        assertEquals(1, emptyGraph.getVertices().size());
    }

    @Test
    public void testAddVertexToGraphWithExistingVertices() {
        setUp1();
        graphWithVertices.addVertex(7);
        assertEquals(6, graphWithVertices.getVertices().size());
    }

    @Test
    public void testAddVertexWithSameID() {
        setUp1();
        assertThrows(IllegalArgumentException.class, () -> {
            graphWithVertices.addVertex(1);
        });
    }

    @Test
    public void testAddEdgeBetweenExistingVertices() {
        setUp1();
        graphWithVertices.addEdge(1, 2,2);
        assertTrue(graphWithVertices.hasEdge(1, 2));
    }

    @Test
    public void testAddWeightedEdge() {
        setUp1();
        graphWithVertices.addEdge(1, 2, 10);
        assertEquals(10, graphWithVertices.getEdgeWeight(1, 2));
    }

    @Test
    public void testAddEdgeBetweenExistingVerticesWithWeightZero() {
        setUp1();
        graphWithVertices.addEdge(1, 2, 0);
        assertEquals(0, graphWithVertices.getEdgeWeight(1, 2));
    }

    @Test
    public void testAddEdgeBetweenNonExistingVertices() {
        setUp1();
        assertThrows(IllegalArgumentException.class, () -> {
            graphWithVertices.addEdge(1, 10,10);
        });
    }

    @Test
    public void testBFS() {
        setUp1();
        graphWithVertices.addEdge(0, 1,2);
        graphWithVertices.addEdge(1, 2,2);
        graphWithVertices.addEdge(2, 3,2);
        graphWithVertices.addEdge(3, 4,2);

        List<Integer> bfsResult = graphWithVertices.bfs(1);
        assertNotNull(bfsResult);
        assertEquals(4, bfsResult.size());
        for (Integer vertex : bfsResult) {
            assertTrue(graphWithVertices.getVertices().contains(vertex));
        }
    }

    @Test
    public void testFloydWarshall() {
        setUp1();
        graphWithVertices.addEdge(0, 1, 1);
        graphWithVertices.addEdge(1, 2, 2);
        graphWithVertices.addEdge(2, 3, 3);
        graphWithVertices.addEdge(3, 4, 4);

        ArrayList<ArrayList<Integer>> distances = graphWithVertices.floydWarshallM();
        assertNotNull(distances);
        assertEquals(5, distances.size());
        for (ArrayList<Integer> row : distances) {
            assertEquals(5, row.size());
        }

        assertEquals(1, distances.get(0).get(1));
        assertEquals(3, distances.get(0).get(2));
        assertEquals(6, distances.get(0).get(3));
        assertEquals(10, distances.get(0).get(4));
    }
}