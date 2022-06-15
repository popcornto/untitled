package WeightedStuff;



import java.util.*;

public class WeightedEdge { /*• WeightedStuff.WeightedEdge: Der Konstruktor soll mit zwei Node-Objekten und einem ganzzahligen, positiven Gewicht aufgerufen werden. Das Kantengewicht soll im privaten Attribut weight
abgelegt und über eine Getter-Methode zugänglich gemacht werden.*/

    private Node src;
    private Node dst;
    private int weight;


    public WeightedEdge(Node node1, Node node2, int weight) {
    this.src = node1;
    this.dst = node2;
    if (weight < 0) {
        throw new IllegalArgumentException("weight is negative");
    } else{
        this.weight = weight;}

}

    public Node getSrc() {
        return src;
    }



    public int getWeight() {
        return weight;
    }


    public Node getDst() {
        return dst;
    }
}


