import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SimpleHT {
    private int mA;
    private ArrayList <Pair>[] table;

    public SimpleHT(int m)
    {
        this.mA = m;
        table = new ArrayList[m];
    }

    private int addressOfList(Integer key)
    {
        if(key.hashCode() < 0)
        {
            return key.hashCode() % mA*-1;
        }
        else
        {
            return key.hashCode() % mA;
        }
    }

    public void insert(Integer key, Integer value){
        int address = addressOfList(key);
        boolean inserted = false;
        if(table[address] == null)
        {
            table[address] = new ArrayList<Pair>();
        }
        if(table[address].isEmpty())
        {
            table[address].add(new Pair(key, value));
        }
        else
        {
            for (int i = 0; i <table[address].size() ; i++) {
                if(table[address].get(i).getKey().equals(key))
                {
                    table[address].get(i).setValue(value);
                    inserted = true;
                }

            }
            if(!inserted)
            {
                table[address].add(new Pair(key, value));
            }
        }

    }

    public Integer get(Integer key){
        int address = addressOfList(key);
        if(table[address] == null)
        {
            return null;
        }
        for (int i = 0; i <table[address].size() ; i++) {
            if(table[address].get(i).getKey().equals(key))
            {
                return table[address].get(i).getValue();

            }

        }
        return null;
    }

    public boolean remove(Integer key){
        int address = addressOfList(key);

        for (int i = 0; i <table[address].size() ; i++) {
            if(table[address].get(i).getKey().equals(key))
            {
                table[address].remove(i);
                return true;
            }
        }
        return false;
    }

    private class Pair{
        private Integer keyA;
        private Integer valueA;

        public Pair(Integer keyP, Integer valueP) {
            keyA = keyP;
            valueA = valueP;
        }

        public Integer getKey() {
            return keyA;
        }

        public Integer getValue() {
            return valueA;
        }

        public void setValue(Integer value) {
            valueA = value;
        }

        public void setKey(Integer key) {
            keyA = key;
        }
    }
}
