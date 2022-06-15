package WeightedStuff;



import java.util.*;
public class Node {
    private int id;
    private LinkedList<WeightedEdge> Adjanz = new LinkedList<WeightedEdge>();

    public Node(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public int getAnzahlAdjanzen() {
        return Adjanz.size();
    }

    public WeightedEdge getAdjanz(int index) {
        return Adjanz.get(index);
    }

    public void addEdge(Node dst, int weight) {
        WeightedEdge edge = new WeightedEdge(this, dst, weight);
        Adjanz.add(edge);
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            if (((Node) other).getID() == this.id) {
                return true;
            }
        }
        return false;
    }


}
