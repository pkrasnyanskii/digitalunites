package nsu.fit.krasnyanskii;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeTest {

    private final int testAmount = 1000;
    private Tree<Integer> tree2;
    private Tree<Integer> tree = new Tree<>();
    private ArrayList<Integer> list;

    @Test
    public void testAdd() {
        assertTrue(tree.add(5));
        assertEquals(1, tree.size());
    }

    @Test
    public void testAddNull() {
        try {
            tree.add(null);
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
            assertEquals(0, tree.size());
        }
    }

    @Test
    public void testRemoveNull() {
        try {
            tree.remove(null);
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
            assertEquals(0, tree.size());
        }
    }

    @Test
    void testIsEmpty() {
        tree2.clear();
        assertTrue(tree2.isEmpty());
    }

    @BeforeEach
    void forEach() {
        tree2 = new Tree<>();
        list = new ArrayList<>();
        for (int i = 0; i < testAmount; i++) {
            tree2.add(i);
            list.add(i);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void testBFS() {
        var BFS1 = tree2.iteratorBFS();
        for (int i = 0; i < testAmount; i++) {
            assertTrue(BFS1.hasNext());
            assertEquals(i, BFS1.next());
        }
        assertFalse(BFS1.hasNext());
        var BFS2 = tree2.iteratorBFS();
        for (int i = 0; i < testAmount; i++) {
            BFS2.remove();
        }
        assertTrue(tree2.isEmpty());
        assertThrows(ConcurrentModificationException.class, BFS2::hasNext);
    }

    @Test
    void testExceptions() {
        assertThrows(NullPointerException.class, () -> tree2.remove(null));
        assertThrows(NullPointerException.class, () -> tree2.retainAll(null));
        assertThrows(NullPointerException.class, () -> tree2.add(null));
        assertThrows(NullPointerException.class, () -> tree2.addAll(null));
    }

    @Before
    public void setup() {
        tree = new Tree<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);

        // create a tree like this:
        //      1
        //    / | \
        //   2  3  4
        //  /
        // 5
        tree.iteratorDFS();
    }

    @Test
    public void testDFSWithSingleNode() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1);
        List<Integer> expected = Arrays.asList(1);
        List<Integer> actual = new ArrayList<>();
        for (Iterator<Integer> it = tree.iteratorDFS(); it.hasNext(); ) {
            Integer i = it.next();
            actual.add(i);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testDFSWithEmptyTree() {
        Tree<Integer> tree = new Tree<>();
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        for (Iterator<Integer> it = tree.iteratorDFS(); it.hasNext(); ) {
            Integer i = it.next();
            actual.add(i);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testIteratorDFS_EmptyTree() {
        Tree<Integer> tree = new Tree<>();
        Iterator<Integer> it = tree.iteratorDFS();
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorDFS_SingleElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1);
        Iterator<Integer> it = tree.iteratorDFS();
        assertTrue(it.hasNext());
        assertEquals(1, it.next().intValue());
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorDFS_RemovedElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        Iterator<Integer> it = tree.iteratorDFS();


    }
}
