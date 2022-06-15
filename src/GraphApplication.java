import java.util.*;
public class GraphApplication {
    public static void main(String[] args) {
        if (args.length != 3) {      //l, r, b m端ssen eingegeben werden
            System.out.println("Exactly three arguments are required.");// die Eingabe wird auf 3 Argumente gepr端ft
            return;
        }
        int k = 0;
        try {
            k = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Eingabe f端r s und/oder t m端ssen Integer sein!");
        }
        Graph graph = Graph.fromFile(args[0]);
        if (Integer.parseInt(args[1]) >= graph.getAnzahlKnoten() || Integer.parseInt(args[2]) >= graph.getAnzahlKnoten()||Integer.parseInt(args[1])<0 || Integer.parseInt(args[2])<0) {
            System.out.println("Diese Knoten gibt es nicht!");
        } else {
            graph.toString();
            int x = graph.kurzesteWegzuKnoten(graph, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            if (x == Integer.MAX_VALUE) {
                System.out.println("Es geht keine pfad von s zu t");
            } else {
                System.out.println("Der kuerzeste Pfad ist " + x + " lang.");
            }
        }
        List<Node> sort=graph.topoSort(graph);
        for(int i=0; i<sort.size();i++){
            System.out.println(sort.get(i).getID());
        }
    }
}