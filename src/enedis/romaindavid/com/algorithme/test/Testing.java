package enedis.romaindavid.com.algorithme.test;

import java.util.*;

public class Testing {
    public static void main(String[] args) {

        HashMap map = new HashMap();

        map.put("A",18.5);
        map.put("E",76.8);
        map.put("C",24.1);
        map.put("F",86.2);
        map.put("D",5.7);
        map.put("B",84.6);

        System.out.println("Avant le tri: "+map);
        System.out.println("Après le tri: "+triAvecValeur(map));
    }

    public static HashMap<String, Double> triAvecValeur( HashMap<String, Double> map ){
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>(){
            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 ){
                return (o1.getValue()).compareTo( o2.getValue());
            }
        });

        HashMap<String, Double> map_apres = new LinkedHashMap<String, Double>();
        for(Map.Entry<String, Double> entry : list)
            map_apres.put( entry.getKey(), entry.getValue() );
        return map_apres;
    }
}
