import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class MyHashMap<K, V>{
    
    class HMNode{
        K key;
        V value;

        HMNode(K key, V value){
            this.key = key;
            this.value = value;
        }

    }

    private int n;
    private ArrayList<LinkedList<HMNode>> bucket;

    MyHashMap(){
        initMap(4);
        n=0;
    }

    public void initMap(int size){
        bucket = new ArrayList<>();
        while(size-->0){
            bucket.add(new LinkedList<>());
        }
    }

    public V put(K key, V value){
        
        int N = bucket.size();
        System.out.println(n+" "+N);
        double lambda = (double)n/N;
        System.out.println(lambda);
        int k = 2;
        if(lambda>k){
            rehash();
        }

        int hashcode = hashFunction(key);
        if(hashcode<0){
            hashcode+=bucket.size();
        }

        for(HMNode node: bucket.get(hashcode)){
            if(node.key==key){
                V preval = node.value;
                node.value = value;
                return preval;
            }
        }
        
        bucket.get(hashcode).addFirst(new HMNode(key, value));
        n++;
        return null;
        
    }

    public void rehash(){

        ArrayList<LinkedList<HMNode>> previousBucket = bucket;

        ArrayList<LinkedList<HMNode>> newBucket = new ArrayList<>();

        int newN = previousBucket.size()*2;
        for(int i=0;i<newN;i++){
            newBucket.add(new LinkedList<>());
        }
        bucket = newBucket;

        for(LinkedList<HMNode> llist: previousBucket){
            for(HMNode x: llist){
                int hashcode = hashFunction(x.key);
                newBucket.get(hashcode).addFirst(new HMNode(x.key, x.value));
            }
        }

    }

    public int hashFunction(K key){
        return key.hashCode()%bucket.size();
    }

    public V get(K key){

        int hashcode = hashFunction(key);
        if(hashcode<0){
            hashcode+=bucket.size();
        }

        for(HMNode x : bucket.get(hashcode)){
            if(x.key == key ){
                return x.value;
            }
        }

        return null;

    }

    public boolean containsKey(K key){

        int hashcode = hashFunction(key);
        if(hashcode<0){
            hashcode+=bucket.size();
        }

        for(HMNode x : bucket.get(hashcode)){
            if(x.key == key ){
                return true;
            }
        }

        return false;

    }

    public Set<K> keySet(){
        
        if(n==0){
            return null;
        }

        Set<K> keys = new HashSet<>();
        for(int i=0;i<bucket.size();i++){

            for(HMNode x : bucket.get(i)){
                keys.add(x.key);
            }

        }

        return keys;

    }

    public int size(){
        return n;
    }

    public boolean remove( K key ){
        
        int hashcode = hashFunction(key);
        if(hashcode<0){
            hashcode+=bucket.size();
        }
        for(HMNode x : bucket.get(hashcode)){
            if(x.key == key ){
                bucket.get(hashcode).remove(x);
                n--;
                return true;          
            }
        }        

        return false;
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i=0;i<bucket.size();i++){
            for(HMNode x : bucket.get(i)){
                sb.append(x.key+":"+x.value+",");
            }
        }
        if(sb.length()!=1)
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append("}");

        return sb.toString();

    }

}