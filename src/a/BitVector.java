package a;

public class BitVector{
    private int[] data;
    private int n;
    public BitVector(int n){
        this.n = n;
        if(n<0){
            System.out.println("n ist negativ");
        }
        else{
            this.data= new int[(this.n/32)+1 ];
        }
    }
    public int size(){
        return n;
    }

    public boolean get(int index){
            int word=data[index/32];
            int shifted= word>>index;
            int mask= 1;
            boolean result = (shifted & mask) > 0;
            return result;

    }
    public void set(int index, boolean value) {
        if (value == false) {
            int word = data[index / 32];
            int mask = 1 << index;
            mask = ~mask;
            word = word & mask;
            data[index / 32] = word;
        }
        else{
            int word= data[index/32];
            int mask=1<<index;
            word= word|mask;
            data[index/32]=word;
        }
    }
}