import java.util.LinkedList;
public class Node {
    private int id;
    private LinkedList<Edge> Adjanz= new LinkedList<Edge>();
    public Node(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
    public int getAnzahlAdjanzen(){
        return Adjanz.size();
    }
    public Edge getAdjanz(int index){
        return Adjanz.get(index);
    }
    public void addEdge(Node dst){
        Edge edge= new Edge(this, dst);
        Adjanz.add(edge);
    }
    public boolean equals(Object other){
        if(other instanceof Node){
            if(((Node) other).getID()==this.id){
                return true;
            }
        }
        return false;
    }

}
