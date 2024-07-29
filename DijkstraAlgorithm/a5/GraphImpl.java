package a5;

import java.lang.reflect.Array;
import java.util.*;

public class GraphImpl implements Graph {
    Map<String, Node> _nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name
    Comparator<ShortestPathQueueObject> compare = Comparator.comparingDouble(a -> a.distance);
    PriorityQueue<ShortestPathQueueObject> _queue = new PriorityQueue<>(compare);
    Map<String, Double> _path = new HashMap<>();

    private int _totEdges; // total number of edges in the graph
    private int _countBadEdges = 0; // if the edge to the destination already exists; bad
    private int _countGoodEdges = 0; // if the edge to the destination doesn't exist and shortest; good

    public GraphImpl() {
        _nodes = new HashMap<>();
        _totEdges = 0;
    }

    @Override
    public boolean addNode(String name) {
        // Works!
        if (_nodes.containsKey(name)) {
            return false;
        } else {
            Node tmp = new NodeImpl(name);
            _nodes.put(name, tmp);
            return true;
        }
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        // Works!
        if (_nodes.containsKey(src) && _nodes.containsKey(dest)) {
            Node curr = _nodes.get(src);
            if (weight < 0) {
                return false;
            }
            ArrayList<String> tmpEdges = curr.outgoingEdges();
            if (tmpEdges.contains(dest)) {
                return false;
            }
            Edge newEdge = new EdgeImpl(src, dest, weight);
            curr.addEdge(newEdge);
            _totEdges++;
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNode(String name) {
        // check
        //Hint: Do you need to remove edges when you delete a node?
        // if the nodes contains name
        if (_nodes.containsKey(name)) {
            // set curr node "name"
            //  remove all incoming edges
            // for each node in nodes
            for (Node node : _nodes.values()) {
                // if the node "name" isn't "name"
                if (!node.getName().equals(name)) {
                    // if _nodes contains node has edges
                    if (node.edgeSize() != 0) {
                        // if node has outgoing edge to "name"
                        if (node.hasEdge(name)) {
                            // edges = all outgoing edges of node
                            for (int i = 0; i < node.getEdge().size(); i++) {
                                // if node outgoing edge goes to name
                                if (node.getEdge().get(i).getDest().equals(name)) {
                                    // remove that edge
                                    node.getEdge().remove(i);
                                    _totEdges--;
                                }
                            }
                        }
                    }
                }
            }
            // after all the incomming edges are removed, delete all the outgoing edges
            _totEdges -= _nodes.get(name).edgeSize();
            _nodes.get(name).clearEdges();
            _nodes.remove(name);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        // I think this works
        if (_nodes.containsKey(src) && _nodes.containsKey(dest)) {
            Node curr = _nodes.get(src);
            ArrayList<Edge> currEdges = curr.getEdge();
            for (int i = 0; i < currEdges.size(); i++) {
                if (curr.getEdge().get(i).getDest().equals(dest)) {
                    curr.getEdge().remove(i);
                    _totEdges--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int numNodes() {
        return _nodes.size();
    }

    @Override
    public int numEdges() {
        return _totEdges;
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        System.out.println();
        System.out.println("------- Start of dijkstra -------");
        //---------------------------------------------;
        // if the path is empty start.
        if (_nodes.containsKey(start)) {
            dijkstra_r(start, 0.0);
            System.out.println("Path: " + _path.entrySet());
            return _path;
        } else {
            return null;
        }
    }

    // recursive function
    public void dijkstra_r(String start, Double pLen) {
        // starting point starts with a distance of: 0.0
        if (_path.size() == 0) {
            Node curr = _nodes.get(start);
            curr.pathLength(0.0);
            _path.put(start, 0.0);
            if (curr.edgeSize() != 0) {
                for (Edge edge : curr.getEdge()) {
                    double newDistance = pLen + edge.getWeight();
                    ShortestPathQueueObject addObject = new ShortestPathQueueObject(edge.getDest(), newDistance);
                    _queue.add(addObject);
                }
                dijkstra_r(_queue.peek().label, _queue.peek().distance);
            }
        } else if ((_countGoodEdges + _countBadEdges) < _totEdges) {
            if (_path.containsKey(_queue.peek().label)) {
                _queue.poll();
                if (_queue.peek() != null) {
                    dijkstra_r(_queue.peek().label, _queue.peek().distance);
                }
            } else {
                Node curr = _nodes.get(start);
                curr.pathLength(pLen);
                _path.put(start, pLen);
                _countGoodEdges++;
                _queue.poll();
                if (curr.edgeSize() != 0) {
                    for (Edge edge : curr.getEdge()) {
                        double newDistance = pLen + edge.getWeight();
                        ShortestPathQueueObject addObject = new ShortestPathQueueObject(edge.getDest(), newDistance);
                        _queue.add(addObject);
                    }
                    if (_queue.peek() != null) {
                        dijkstra_r(_queue.peek().label, _queue.peek().distance);
                    }
                } else if (curr.edgeSize() == 0) {
                    if (_queue.peek() != null) {
                        dijkstra_r(_queue.peek().label, _queue.peek().distance);
                    }
                }
            }
        }
    }

    // print the graph
    public void print_graph() {
        String printed_graph = "";
        for (String name : _nodes.keySet()) {
            printed_graph += name;
            ArrayList<Edge> tmpEdges = _nodes.get(name).getEdge();
            if (tmpEdges != null) {
                for (Edge edge : tmpEdges) {
                    printed_graph += " ---------> " + edge.getDest() + " (weight: " + edge.getWeight() + ") ";
                }
            }
        }
        System.out.println(printed_graph);
    }
}