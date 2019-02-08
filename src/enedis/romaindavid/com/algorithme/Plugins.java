package enedis.romaindavid.com.algorithme;

import java.util.Random;

import static enedis.romaindavid.com.param.Parameter.*;

public class Plugins {



    public static String generateRandomString(){
        int randNumber = generateRandom( puissanceDe10( getNumberCasePossible() )) ;
        return formatNumber( randNumber );
    }

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
    /**
     *
     * @param number
     * @return
     */
    public static String formatNumber(int number ){
        return formatNumber( getNumberCasePossible(), number );
    }

    private static String formatNumber(int lenString, int number ){
        return String.format("%0" + getNumberCasePossible() + "d",  number );
    }

    private static int puissanceDe10(int puissance ){
        return (int) Math.pow( 10, puissance ) ;
    }





}
