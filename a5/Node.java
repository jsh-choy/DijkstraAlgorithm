package a5;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Node {

     /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /**
     * @return the name of the node
     */
    // return a ArrayList of Edges connected to the node.
    ArrayList<Edge> getEdge();
    ArrayList<String> outgoingEdges();
    void clearEdges();
    String getName(); // name of the Node
    //return the number of outgoing edges
    int edgeSize();
    // get all the edges and return a list of Edges
    double getPathLength();
    boolean hasEdge(String dest);
    void addEdge(Edge edge);
    void removeEdge(Edge e);
    // set the distance to the src
    void pathLength(double pLen);
}
