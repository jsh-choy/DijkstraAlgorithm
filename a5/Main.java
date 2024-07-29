package a5;

public class Main {

    public static void main(String[] args) throws Exception {

        //You are encouraged (but not required) to include your testing code here.

        //Hint: Try to test basic operations (e.g., adding a few nodes and edges to graphs)
        //before you implement more complex methods
        Graph gph = new GraphImpl();

        // List of names for the nodes.
        String[] tmpStr = new String[]{"a", "b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"};

        for (String string : tmpStr) {
            gph.addNode(string);
        }

//        // From PowerPoint
//        gph.addEdge("a", "b", 2.0);
//        gph.addEdge("a", "d", 1.0);
//
//        gph.addEdge("b", "d", 3.0);
//        gph.addEdge("b", "e", 1.0);
//
//        gph.addEdge("c", "a", 4.0);
//        gph.addEdge("c", "f", 5);
//
//        gph.addEdge("d", "c", 2.0);
//        gph.addEdge("d", "e", 3.0);
//        gph.addEdge("d", "f", 8.0);
//        gph.addEdge("d", "g", 4.0);
//
//        gph.addEdge("e", "g", 6.0);
//
//        gph.addEdge("g", "f", 1.0);
//
//        gph.dijkstra("a");

//        // from data visualiation
        gph.addEdge("a","b",5.0);
        gph.addEdge("a","h",4.0);
        gph.addEdge("a","e",4);
        gph.addEdge("b","e",2.0);
        gph.addEdge("c","b",1);
        gph.addEdge("c","f",5);
        gph.addEdge("d","k",1);
        gph.addEdge("e","f",4);
        // f has no edges
        gph.addEdge("g","c",3);
        gph.addEdge("g", "d", 7.0);
        gph.addEdge("h","a",2);
        gph.addEdge("h","i",6);
        gph.addEdge("i","f",7);
        gph.addEdge("i","m",4);
        // j doesn't have edges
        // k doesn't have edges going out
        gph.addEdge("l","h",8);
        gph.addEdge("l","o",5);
        gph.addEdge("l","m",6);
        gph.addEdge("n","m",2);
        gph.addEdge("n", "q", 6);
        gph.addEdge("o", "l", 6.0);
        gph.addEdge("p","r",3);
        // q has no outgoing edges
        gph.addEdge("r","n",1);

        gph.dijkstra("o");
    }
}