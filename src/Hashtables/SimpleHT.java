package Hashtables;

import Hashtables.*;

import java.util.*;

public class SimpleHT<K, V> {
    private class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    private class WorstHashKey<K> implements SimpleHashFunction<K> {
        public int getHash(K key){
            return key.hashCode();
        }
    }

    private ArrayList<Pair<K, V>>[] HT;
    private int m;
    private SimpleHashFunction h= new WorstHashKey();

    public SimpleHT(int m, SimpleHashFunction h) {
        this.m = m;
        this.h=h;
        HT = new ArrayList[m];
        for (int i = 0; i < m; ++i) {
            HT[i] = new ArrayList<Pair<K, V>>();
        }
    }
    public SimpleHT(int m) {
        this.m = m;
        HT = new ArrayList[m];

    }

    private int addressOfList(K key) {
        if(h.getHash(key)%m<0){
            return h.getHash(key)%m*-1;
        }
        else{
            return h.getHash(key)%m;
        }
    }

    public void insert(K key, V value) {
        Pair pair = new Pair(key, value);
        int address = addressOfList(key);
        boolean inserted = false;
        if(HT[address] == null)
        {
            HT[address] = new ArrayList<Pair<K,V>>();
        }
        if(HT[address].isEmpty())
        {
            HT[address].add(new Pair(key, value));
        }
        else
        {
            for (int i = 0; i <HT[address].size() ; i++) {
                if(HT[address].get(i).getKey().equals(key))
                {
                    HT[address].get(i).setValue(value);
                    inserted = true;
                }

            }
            if(!inserted)
            {
                HT[address].add(new Pair(key, value));
            }
        }
    }

    public V get(K key) {
        int address = addressOfList(key);
        if(HT[address] == null)
        {
            return null;
        }
        for (int i = 0; i <HT[address].size() ; i++) {
            if(HT[address].get(i).getKey().equals(key))
            {
                return HT[address].get(i).getValue();

            }

        }
        return null;
    }
    public boolean remove(K key){
        for(int i=0;i<HT[addressOfList(key)].size();i++){
            if (HT[addressOfList(key)].get(i).getKey().equals(key)){
                HT[addressOfList(key)].remove(i);
                return true;
            }
        }
        return false;
    }

}