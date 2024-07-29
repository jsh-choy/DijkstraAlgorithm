package a5;

import java.util.ArrayList;
import java.util.Objects;

public class NodeImpl implements Node {

    /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any node fields you want to add for the object should go in this file.  */
    private String _name;
    private double _pathLength = Double.POSITIVE_INFINITY;
    private ArrayList<Edge> _edges = new ArrayList<>();

    public NodeImpl(String n) {
        _name = n;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public boolean hasEdge(String dest) {
        for (Edge edge : _edges) {
            if (edge.getSrc().equals(_name) && edge.getDest().equals(dest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(Edge ed) {
        _edges.add(ed);
    }

    @Override
    public void removeEdge(Edge ed) {
        for (Edge edge : _edges) {
            if (edge.getDest().equals(ed.getDest())) {
                _edges.remove(edge);
            }
        }
    }

    @Override
    public int edgeSize() {
        return _edges.size();
    }

    @Override
    public ArrayList<Edge> getEdge() {
        return _edges;
    }

    @Override
    public void clearEdges() {
        _edges.clear();
    }

    @Override
    public ArrayList<String> outgoingEdges() {
        ArrayList<String> outgoingEdges = new ArrayList<>();
        if (_edges.isEmpty()) {
            return outgoingEdges;
        }

        for (Edge edge : _edges) {
            outgoingEdges.add(edge.getDest());
        }
        return outgoingEdges;
    }

    @Override
    public void pathLength(double dub) {
        _pathLength = dub;
    }

    @Override
    public double getPathLength() {
        return _pathLength;
    }
}
