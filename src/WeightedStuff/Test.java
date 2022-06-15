package WeightedStuff;

public class Test {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Exactly three arguments are required.");// die Eingabe wird auf 3 Argumente geprüft
            return;
        }
        int k = 0;
        try {
            k = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Eingabe für s und/oder t müssen Integer sein!");
        }
        WeightedGraph graph = WeightedGraph.fromFile(args[0]);
        if (Integer.parseInt(args[1]) >= graph.getAnzahlKnoten() || Integer.parseInt(args[2]) >= graph.getAnzahlKnoten()||Integer.parseInt(args[1])<0 || Integer.parseInt(args[2])<0) {
            System.out.println("Diese Knoten gibt es nicht!");
        } else {
            int x= graph.dijkstra(graph,Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            if(x>=0){
                System.out.println("Kuerzester Pfad der Laenge "+x+" gefunden.");
            }
            else{
                System.out.println( "Es gibt keinen Pfad von Knoten "+args[1]+" zu Knoten "+args[2]);
            }
        }
    }
}
