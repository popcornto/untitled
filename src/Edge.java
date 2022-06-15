public class Edge {
    private Node src;
    private Node dst;
    public Edge(Node node1, Node node2){
        this.src=node1;
        this.dst=node2;
    }
    public Node getSrc(){
        return src;
    }
    public Node getDst(){
        return dst;
    }
    public String toString(){
        return src.getID()+" "+dst.getID();
    }

}
