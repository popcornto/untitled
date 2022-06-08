import java.util.*;
import java.io.*;
public class Graph {
    private Node[] graph;
    private int n;
    private int m;
    public Graph(int n,int m){
        this.n=n;
        this.m=m;
        graph= new Node[n];
    }
    public int getAnzahlKnoten(){
        return n;
    }
    public int getAnzahlKanten(){
        return m;
    }
    public boolean containsID(int id){
        for(int i=0;i<n;i++){
            if(graph[i].getID()==id){
                return true;
            }
        }
        return false;
    }
    public Node getNode(int id){
        for(int i=0;i<n;i++){
            if(graph[i].getID()==id){
                return graph[i];
            }
        }
        return null;
    }
    public boolean addEdge(int src, int dsc){
        if(containsID(src)&&containsID(dsc)){
            getNode(src).addEdge(getNode(dsc));
            return true;
        }
        return false;
    }
    public static Graph fromFile(String filepath) {
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

            while(sc.hasNextLine()){
                line = sc.nextLine();
                String[] args = line.split(" ");
                if(args[0].equals("c")){
                    continue;
                }
                if(args[0].equals("p")){
                    if (args.length != 4) {
                        throw new IllegalArgumentException("p line has wrong number of arguments");
                    }
                    if (!args[1].equals("edge")) {
                        throw new IllegalArgumentException("edge is nichts da");
                    }
                    int n = Integer.parseInt(args[2]);
                    int m = Integer.parseInt(args[3]);
                    Graph graph = new Graph(n, m);
                    for (int i = 0; i < n; i++) {
                        graph.graph[i] = new Node(i);
                    }
                    while(sc.hasNextLine()){
                        line = sc.nextLine();
                        args = line.split(" ");
                        if(args[0].equals("e")){
                            int src = Integer.parseInt(args[1]);
                            int dsc = Integer.parseInt(args[2]);
                            graph.addEdge(src, dsc);
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
                System.out.println(graph[i].getAdjanz(j).getDst().getID());
            }
        }
        return "";
    }
    public int kurzesteWegzuKnoten(Graph graph, int s, int t){
        if(s>t){
            int temp=s;
            s=t;
            t=temp;
        }
        int[] distance = new int[graph.getAnzahlKnoten()];
        for(int i = 0; i < distance.length; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(s);
        while(!queue.isEmpty()){
            int u = queue.removeFirst();
            for(int i = 0; i < graph.getNode(u).getAnzahlAdjanzen(); i++){
                int v = graph.getNode(u).getAdjanz(i).getDst().getID();
                if(distance[v] == Integer.MAX_VALUE){
                    distance[v] = distance[u] + 1;
                    queue.addLast(v);
                }
            }
        }
        return distance[t];
    }
}
