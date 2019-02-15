package enedis.romaindavid.com.algorithme;

import java.util.*;

import static enedis.romaindavid.com.param.Parameter.getNumberCasePossible;

public class Plugin {


    public static int generateRandom(int bound){
        Random rand = new Random();
        return rand.nextInt ( bound );
    }

    public static int generateRandomInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static int toInt( String strInt) {
        return  Integer.valueOf( strInt );
    }

    public static String toStr( int intStr){
        return  String.valueOf( intStr );
    }

    public static String formatNumber(int number ){
        return formatNumber( getNumberCasePossible(), number );
    }

    private static String formatNumber(int lenString, int number ){
        return String.format("%0" + getNumberCasePossible() + "d",  number );
    }

    public static int puissanceDe10(int puissance ){
        return (int) Math.pow( 10, puissance ) ;
    }

    public static String firstUpperCase(String str ){
        return str.substring(0,1).toUpperCase() + str.substring( 1 );
    }
    /**
     * Dans cet exemple, on va trier des éléments de type Double.
     * Les éléments sont copiés dans une LinkedList qui implémente l'interface List,
     * puis ils sont triés avec la méthode Collections.sort que nous avons utilisé
     * dans les articles précédents pour trier d'autres collections d'objets. Après le tri,
     * on recopie les élément triés de LinkedList vers une nouvelle table de hachage qui va être retournée en sortie.
     *
     * @param map
     * @return
     */
    public static HashMap<String, Integer> triAvecValeur(HashMap<String, Integer> map ){
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 ){
                return (o2.getValue()).compareTo( o1.getValue());
            }
        });

        HashMap<String, Integer> map_apres = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entry : list)
            map_apres.put( entry.getKey(), entry.getValue() );
        return map_apres;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
