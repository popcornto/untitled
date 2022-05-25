package a;

public class BitVectorSelect {
    BitVectorRank rank;

    public BitVectorSelect(BitVectorRank rank) {
        this.rank = rank;
    }

    public int size() {
        return rank.size();
    }


    public int select(int k) {
        for (int i = 0; i < size(); i++) {
            if (rank.rank(i) == k) {
                return i;
            }
        }
        return -1;
    }

    public int select(int k, int start, int end) {
        if (k > 1) {
            for (int i = start; i < end; i++) {
                if (rank.rank(i) == k) {
                    return i;
                }
            }
        }

        return -1;
    }
}
