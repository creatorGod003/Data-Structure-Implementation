public class Main {
    public static void main(String[] args) {

        MyHashMap<String, Integer> map = new MyHashMap<>();
        System.out.println(map.put("us", 20));
        System.out.println(map.put("India", 4));
        System.out.println(map.get("India"));
        System.out.println(map.put("us", 15));
        System.out.println(map.put("china", 22));
        System.out.println(map.put("uk", 2));
        System.out.println(map.put("canada", 1));
        System.out.println(map.remove("us"));

        
        System.out.println(map);
        
    }
}
