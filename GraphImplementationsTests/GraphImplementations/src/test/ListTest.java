
import static org.junit.jupiter.api.Assertions.*;
import  com.example.bomberman.model.DirectedListGraph;
import com.example.bomberman.model.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListTest {

    private DirectedListGraph<Integer> graph;

    private DirectedListGraph<Integer> emptyGraph;



    public void setUp1() {
        graph = new DirectedListGraph<>(5);
        for (int i = 0; i < 5; i++) {
            graph.addVertex(i);
        }
    }

    public void setUp2() {
        emptyGraph = new DirectedListGraph<>(0);
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
        graph.addVertex(7);
        assertEquals(6, graph.getVertices().size());
    }

    @Test
    public void testAddVertexWithSameID() {

    }
    @Test
    public void testAddEdgeBetweenExistingVertices() {
        setUp1();
        graph.addEdge(1, 2);
        Vertex<Integer> source = graph.getVertexOfData(1);
        Vertex<Integer> destination = graph.getVertexOfData(2);

        assertNotNull(source);
        assertNotNull(destination);


        assertEquals(1, graph.getEdges(source).size());
        assertEquals(destination, graph.getEdges(source).get(0).getDestinationVertex());
    }

    @Test
    public void testAddWeightedEdge() {
        setUp1();
        graph.addEdge(1, 2, 10);
        Vertex<Integer> source = graph.getVertexOfData(1);
        Vertex<Integer> destination = graph.getVertexOfData(2);

        assertNotNull(source);
        assertNotNull(destination);

        assertEquals(1, graph.getEdges(source).size());
        assertEquals(destination, graph.getEdges(source).get(0).getDestinationVertex());
        assertEquals(10, graph.getEdges(source).get(0).getWeight());
    }
    @Test
    public void testAddEdgeBetweenExistingVerticesWithWeightZero() {
        setUp1();
        graph.addEdge(1, 2, 0);
        Vertex<Integer> source = graph.getVertexOfData(1);
        Vertex<Integer> destination = graph.getVertexOfData(2);

        assertNotNull(source);
        assertNotNull(destination);

        assertEquals(1, graph.getEdges(source).size());
        assertEquals(destination, graph.getEdges(source).get(0).getDestinationVertex());
        assertEquals(0, graph.getEdges(source).get(0).getWeight());
    }
    @Test
    public void testAddEdgeBetweenNonExistingVertices() {

    }
    @Test
    public void testBFS() {
        setUp1();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        Vertex<Integer> startVertex = graph.getVertexOfData(0);
        graph.bfs(startVertex);

        for (Vertex<Integer> v : graph.auxiliarList) {
            assertNotEquals(Integer.MAX_VALUE, v.getD());
        }
    }


    @Test
    public void testFloydWarshall() {
        setUp1();
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 7);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 0, 2);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 1);

        int[][] expected = {
                {0, Integer.MAX_VALUE/2, Integer.MAX_VALUE/2, Integer.MAX_VALUE/2, Integer.MAX_VALUE/2},
                {4, 0, 1, 3, 3},
                {3, 5, 0, 2, 2},
                {1, Integer.MAX_VALUE/2, Integer.MAX_VALUE/2, 0, Integer.MAX_VALUE/2},
                {7, 3, 4, 6, 0}
        };

        int[][] result = graph.floydWarshall();
        /*for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }*/
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }


}
