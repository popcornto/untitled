package WeightedStuff;



import java.io.*;
import java.util.*;

public class WeightedGraph {

    private Node[] graph;
    private int n;
    private int m;

    public WeightedGraph(int n, int m) {
        this.n = n;
        this.m = m;
        graph = new Node[n];
    }

    public int getAnzahlKnoten() {
        return n;
    }

    public int getAnzahlKanten() {
        return m;
    }

    public boolean containsID(int id) {
        for (int i = 0; i < n; i++) {
            if (graph[i].getID() == id) {
                return true;
            }
        }
        return false;
    }

    public Node getNode(int id) {
        for (int i = 0; i < n; i++) {
            if (graph[i].getID() == id) {
                return graph[i];
            }
        }
        return null;
    }

    public boolean addEdge(int src, int dst, int weight) {
        if (containsID(src) && containsID(dst)) {
            getNode(src).addEdge(getNode(dst), weight);
            return true;
        }
        return false;
    }

    public static WeightedGraph fromFile(String filepath) {
        try {
            if (filepath == null) {
                throw new IllegalArgumentException("filepath is null");
            }
            if (filepath.isEmpty()) {
                throw new IllegalArgumentException("filepath is empty");
            }
            File file = new File(filepath);
            Scanner sc = new Scanner(file);
            String line;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] args = line.split(" ");
                if (args[0].equals("c")) {
                    continue;
                }
                if (args[0].equals("p")) {
                    if (args.length != 4) {
                        throw new IllegalArgumentException("p line has wrong number of arguments");
                    }
                    if (!args[1].equals("edge")) {
                        throw new IllegalArgumentException("edge is nichts da");
                    }
                    int n = Integer.parseInt(args[2]);
                    int m = Integer.parseInt(args[3]);
                    WeightedGraph graph = new WeightedGraph(n, m);
                    for (int i = 0; i < n; i++) {
                        graph.graph[i] = new Node(i);
                    }
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        args = line.split(" ");
                        if (args[0].equals("e")) {
                            int src = Integer.parseInt(args[1]);
                            int dsc = Integer.parseInt(args[2]);
                            int weight = Integer.parseInt(args[3]);
                            graph.addEdge(src, dsc, weight);
                        }
                    }
                    return graph;
                }

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return null;
    }

    public String toString() {

        System.out.println("Graph with " + n + " nodes and " + m + " edges\n");
        for (int i = 0; i < n; i++) {
            System.out.println("Knoten " + i + ": ");
            for (int j = 0; j < graph[i].getAnzahlAdjanzen(); j++) {
                System.out.println("Kante: " + graph[i].getAdjanz(j).getDst().getID() + " Gewicht: " + graph[i].getAdjanz(j).getWeight());
            }
        }
        return "";
    }

    public static boolean dfs(List<Node> solution, Node currentNode, Mark[] marked) {
        if (marked[currentNode.getID()] == Mark.Permanent) {
            return true;
        } else {
            if (marked[currentNode.getID()] == Mark.Temporary) {
                return false;
            } else {
                if (marked[currentNode.getID()] == Mark.Unmarked) {
                    marked[currentNode.getID()] = Mark.Temporary;
                    for (int i = 0; i < currentNode.getAnzahlAdjanzen(); i++) {
                        if (marked[currentNode.getAdjanz(i).getDst().getID()] == Mark.Permanent) {
                        } else {
                            if (dfs(solution, currentNode.getAdjanz(i).getDst(), marked) == false) {
                                return false;
                            }
                        }
                    }
                    marked[currentNode.getID()] = Mark.Permanent;
                    solution.add(0, currentNode);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public static List<Node> topoSort(WeightedGraph g) {
        List<Node> solution = new ArrayList<Node>();
        Mark[] marked = new Mark[g.getAnzahlKnoten()];
        for (int i = 0; i < marked.length; i++) {
            marked[i] = Mark.Unmarked;
        }
        for (int i = 0; i < g.getAnzahlKnoten(); i++) {
            if (dfs(solution, g.getNode(i), marked) == false) {
                return null;
            }
        }
        return solution;
    }
    /*public static int dijkstra(WeightedGraph g, int s, int t){
        int[] dist= new int[g.getAnzahlKnoten()];                           //Array in dem Distanzen abgespeichert werden
        PriorityQueue<HeapElement> U= new PriorityQueue<HeapElement>();
        PriorityQueue<HeapElement> U1= new PriorityQueue<HeapElement>();
        PriorityQueue<HeapElement> S= new PriorityQueue<HeapElement>();
        for (int i = 0; i < dist.length; i++) {
            dist[i]=Integer.MAX_VALUE;
        }
        HeapElement Element = new HeapElement(0,g.getNode(s));
        HeapElement Element1= new HeapElement();
        dist[s]=0;
        U.add(Element);
        while(!U.isEmpty()){
            U1=U;
            int min=Integer.MAX_VALUE;
            for(int i=0;i<U1.size();i++) {
                Element= U1.remove();
                Node n= Element.getNode();
                if(dist[n.getID()]<=min){
                    min=dist[n.getID()];
                    Element1= Element;
            }
            S.add(Element1);
            }
            U.remove(Element1);
            Node n1=Element1.getNode();
            for(int i=0;i<n1.getAnzahlAdjanzen();i++){
                U.add(new HeapElement(n1.getAdjanz(i).getWeight()+dist[n1.getID()],n1.getAdjanz(i).getDst()));
            }

        }
    }*/
    public static int dijkstra(WeightedGraph g, int s, int t) {

        int[] dist = new int[g.getAnzahlKnoten()];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        PriorityQueue<HeapElement> queue = new PriorityQueue<HeapElement>();
        queue.add(new HeapElement(dist[s],g.getNode(s)));

        while (!queue.isEmpty()) {
            HeapElement current = queue.remove();
            int currentID = current.getNode().getID();
            if (currentID == t) {
                return dist[currentID];
            }
            for (int i = 0; i < g.getNode(currentID).getAnzahlAdjanzen(); i++) {
                int adjanzID = g.getNode(currentID).getAdjanz(i).getDst().getID();
                int newDist = dist[currentID] + g.getNode(currentID).getAdjanz(i).getWeight();
                if (newDist < dist[adjanzID]) {
                    dist[adjanzID] = newDist;
                    queue.add(new HeapElement(dist[adjanzID],g.getNode(currentID).getAdjanz(i).getDst()));
                }
            }
        }
        return -1;
    }
}