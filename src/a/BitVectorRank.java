package a;

public class BitVectorRank {
    private int c;
    private BitVector bv;
    private int[] data;

    public BitVectorRank(BitVector bv, int c) {
        this.bv = bv;
        this.c = c;
        data = new int[1 + bv.size() / c];
        for (int i = 0; i < data.length; i++) {
            if (bv.get(i)) {

            }
        }
    }

    public int size() {
        return bv.size();
    }

    public int rank(int index) {
        int rank = 0;

        for (int j = 0; j < index; j++) {
            if (this.bv.get(j)) {
                rank++;
            }
        }
        return rank;
    }

    public int count(int start, int end) {
        if (start > end) {
            return 0;
        } else {
            int startt = rank(start);
            int endd = rank(end);
            return endd - startt;
        }
    }
}