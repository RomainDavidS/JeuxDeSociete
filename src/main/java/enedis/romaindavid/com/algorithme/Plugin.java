package enedis.romaindavid.com.algorithme;

import enedis.romaindavid.com.param.Parameter;

import java.io.IOException;
import java.util.*;


/**
 * set of plugin
 */
public class Plugin {

    /**
     * generates a random number
     * @param bound random number limit value
     * @return random number less @param bound
     */
    public static int generateRandom(int bound){
        Random rand = new Random();
        return rand.nextInt ( bound );
    }

    /**
     * generates a random number between 2 values
     * @param min value minimum
     * @param max value maximum
     * @return random number @param min between @param max
     */
    public static int generateRandomInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * converting an Integer to a String
     * @param strInt Integer to convert
     * @return (Integer) @param strInt
     */
    public static int toInt( String strInt) {
        return  Integer.valueOf( strInt );
    }

    /**
     * converting an String to a Integer
     * @param intStr String to convert
     * @return (String) @param intStr
     */
    public static String toStr( int intStr){
        return  String.valueOf( intStr );
    }

    /**
     * Stringing an Integer
     * @param number Integer to format
     * @return Integer @param number formatted
     */
    public static String formatNumber(int number ){
        return formatNumber( Parameter.getNumberCasePossible(), number );
    }

    /**
     * Stringing an Integer
     * @param len ength of the format
     * @param number nteger to format
     * @return Integer @param number formatted according to the defined length @param len
     */
    public static String formatNumber(int len, int number ){

        return String.format("%0" + len + "d",  number );
    }

    /**
     * generates a random number of a base of 10
     * @param puissance length of the number to generate
     * @return random number generated
     */
    public static int puissanceDe10(int puissance ){
        return (int) Math.pow( 10, puissance ) ;
    }

    /**
     * formats the first character of a string in uppercase
     * @param str String to format
     * @return String @param str with the first character of a string in uppercase
     */
    public static String firstUpperCase(String str ){
        return str.substring(0,1).toUpperCase() + str.substring( 1 );
    }

    /**
     * we will sort Integer elements.
     * Items are copied to a LinkedList that implements the List interface,
     * then they are sorted with the Collections.sort method that we used
     * in previous articles to sort other collections of objects. After sorting,
     * we copy the sorted elements of LinkedList to a new hash table that will be returned as output.
     *
     * @param map hashmap to sort
     * @return sorted hashmap @param map
     */
    public static HashMap<String, Integer> sortWithValue(HashMap<String, Integer> map ){
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

    /**
     * empty the console screen
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
