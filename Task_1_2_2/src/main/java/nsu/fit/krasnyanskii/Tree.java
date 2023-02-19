package nsu.fit.krasnyanskii;

import java.util.*;

/**
 * Classic tree data structure
 * @param <T> class of values
 */
public class Tree<T> implements Collection<T> {

    /**
     * Class for tree's node
     * @param <T> class of values
     */
    private static class Node<T> {
        private T value;
        private ArrayList<Node<T>> children;
        private Node<T> parent;

        /**
         * Constructs a new node with no parent and no value
         */
        private Node() {
            value = null;
            children = new ArrayList<>();
            parent = null;
        }
    }

    private final Node<T> root;
    private int count;
    private int countModified;

    /**
     * Constructs a new Tree with a root node
     */
    public Tree(){
        root = new Node<>();
        count = 0;
    }

    /**
     * Returns the number of elements in this collection.
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns true if this collection contains the specified element.
     * @param obj the element whose presence in this collection is to be tested
     * @return true if this collection contains the specified element
     */
    @Override
    public boolean contains(Object obj) {
        return stream().anyMatch(elem -> elem.equals(obj));
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    /**
     * Returns an iterator over the elements in this tree in depth-first order.
     * @return an iterator over the elements in this tree in depth-first order
     */
    public Iterator<T> iteratorDFS() {
        return new DFS();
    }

    /**
     * Returns an iterator over the elements in this tree in breadth-first order.
     * @return an iterator over the elements in this tree in breadth-first order
     */
    public Iterator<T> iteratorBFS() {
        return new BFS();
    }

    /**
     * Returns an array containing all the elements in this collection.
     * @return an array containing all the elements in this collection
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[count]);
    }

    /**
     * Returns an array containing all the elements in this collection; the runtime type of the returned array
     * is that of the specified array.
     * @param a the array into which the elements of this collection are to be stored
     * @param <T1> the runtime type of the array to contain the collection
     * @return an array containing all the elements in this collection
     */
    @Override
    @SuppressWarnings("Not checked")
    public <T1> T1[] toArray(T1[] a) {
        List<T> list = new ArrayList<>();
        for (T value : this) {
            list.add(value);
        }
        return (T1[]) list.toArray();
    }

    /**
     * Adds the specified element as a child of the root node. Throws a NullPointerException
     * if the specified element is null.
     *
     * @param t the element to add
     * @return true, always
     * @throws NullPointerException if t is null
     */
    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        Node<T> node = new Node<>();
        node.value = t;
        node.parent = root;
        root.children.add(node);
        count++;
        countModified++;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from the tree if it is present.
     * Returns true if the tree contained the specified element, false otherwise. Throws a
     * NullPointerException if the specified object is null.
     *
     * @param obj the object to remove
     * @return true if the tree contained the specified object, false otherwise
     * @throws NullPointerException if obj is null
     */
    @Override
    public boolean remove(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        var iterator = iterator();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.equals(obj)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this tree contains all of the elements in the specified collection.
     *
     * @param c the collection to check for containment in this tree
     * @return true if this tree contains all of the elements in the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all the elements in the specified collection to the tree. Throws a
     * NullPointerException if the specified collection is null.
     *
     * @param c the collection whose elements are to be added to this tree
     * @return true, always
     * @throws NullPointerException if c is null
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        c.forEach(this::add);
        return true;
    }

    /**
     * Removes all of the elements in the specified collection from the tree. Throws a
     * NullPointerException if the specified collection is null.
     *
     * @param c the collection whose elements are to be removed from this tree
     * @return true, always
     * @throws NullPointerException if c is null
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    /**
     * Retains only the elements in this tree that are contained in the specified collection.
     * In other words, removes from this tree all of its elements that are not contained in the
     * specified collection. Throws a NullPointerException if the specified collection is null.
     *
     * @param c the collection to retain
     * @return true if this tree was modified as a result of this operation, false otherwise
     * @throws NullPointerException if c is null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        ArrayList<T> toRemove = new ArrayList<>();
        for (T elem : this) {
            if (!c.contains(elem)) {
                toRemove.add(elem);
                flag = true;
            }
        }
        for (T elem : toRemove) {
            remove(elem);
        }
        return flag;
    }


    /**
     * Removes all elements from the tree.
     */
    @Override
    public void clear() {
        count = 0;
        countModified = 0;
        root.value = null;
        root.children.clear();
    }

    /**
     * A depth-first search implementation of an iterator over the elements in the tree.
     */
    public class DFS implements Iterator<T> {
        private final Stack<Integer> stackOfIDs = new Stack<>();
        private int expectCountModified = countModified;
        private Node<T> node = root;
        private int id = -1;

        @Override
        public boolean hasNext() {
            if (countModified != expectCountModified) {
                throw new ConcurrentModificationException();
            }
            while (id + 1 == node.children.size()) {
                if (node.parent == null) {
                    return false;
                }
                node = node.parent;
                id = stackOfIDs.pop();
            }
            return true;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (id + 1 == node.children.size()) {
                id = stackOfIDs.pop();
                node = node.parent;
            }
            stackOfIDs.push(id + 1);
            node = node.children.get(id + 1);
            id = -1;
            return node.value;
        }

        @Override
        public void remove() {
            if (node.parent == null) {
                clear();
                return;
            }
            node.parent.children.remove(node);
            for (Node<T> child : node.children) {
                node.parent.children.add(child);
                child.parent = node.parent;
            }
            count--;
            countModified++;
            expectCountModified++;
        }
    }

    /**
     * This class implements the breadth-first search (BFS) algorithm to traverse through the tree in a level-wise manner.
     *
     * It uses a queue to keep track of the nodes and their children in the tree.
     */
    public class BFS implements Iterator<T> {
        private final Queue<Node<T>> queue = new LinkedList<>();
        private int expectedCntChanges = countModified;
        private Node<T> node = root;


        /**
         Constructs a new BFS iterator.
         */
        private BFS() {
            queue.addAll(root.children);
        }
        /**
         Returns true if there is another element to be traversed.
         */
        @Override
        public boolean hasNext() {
            if (countModified != expectedCntChanges) {
                throw new ConcurrentModificationException();
            }
            return (!queue.isEmpty());
        }
        /**
         Returns the next element to be traversed.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            node = queue.remove();
            queue.addAll(node.children);
            return node.value;
        }

         /**
         Removes the last element returned by the iterator from the tree. The node and its children are removed and the
         parent's children list is updated.
         */
        @Override
        public void remove() {
            if (node.parent == null) {
                clear();
                return;
            }
            node.parent.children.remove(node);
            for (Node<T> child : node.children) {
                node.parent.children.add(child);
                child.parent = node.parent;
            }
            count--;
            countModified++;
            expectedCntChanges++;
        }
    }
}