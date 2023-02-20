package nsu.fit.krasnyanskii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

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

}
