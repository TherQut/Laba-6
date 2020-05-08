import java.util.LinkedList;
import java.util.Random;

public class CustomBinaryTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private Random ran = new Random();

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }


    public int size() {
        return size(root);
    }
    private int size(Node n) {
        if (n == null) return 0;
        else return n.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (x == null) return null;  // miss
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;  // hit
    }


    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value customGet(Key key) {
        return customGet(root, key).val;
    }
    private Node customGet(Node x, Key key) {
        if (x == null) return null;
        if (x.key.compareTo(key) == 0)
            return x;
        Node result = customGet(x.left, key);
        if (result != null)
            return result;
        return customGet(x.right, key);
    }

    public void customPut(Key key, Value val) {
        root = customPut(root, key, val);
    }
    private Node customPut(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        if (x.right == null && x.left != null) {
            x.right = customPut(x.right, key, val);
        } else if (x.left == null && x.right != null) {
            x.left = customPut(x.left, key, val);
        } else {
            if (ran.nextInt(2) == 1)
                x.right = customPut(x.right, key, val);
            else
                x.left = customPut(x.left, key, val);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> llq = new LinkedList<>();
        keys(root, llq);
        return llq;
    }
    private void keys(Node x, LinkedList<Key> llq) {
        if (x == null) return;
        keys(x.left, llq);
        llq.add(x.key);
        keys(x.right, llq);
    }

    public boolean isBst() {
        return isBst(root, null, 0);
    }
    private boolean isBst(Node x, Key prevKey, int expectedSign) {
        if (x == null) return true;
        if (x == root)
            return isBst(x.left, x.key, 1) && isBst(x.right, x.key, -1);

        if (prevKey.compareTo(x.key) != expectedSign) {
            return false;
        }

        boolean leftBranch = isBst(x.left, x.key, 1);
        if (!leftBranch)
            return false;
        return isBst(x.right, x.key, -1);
    }
}
