package enedis.romaindavid.com.game;


import java.util.HashMap;
import java.util.Map;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;



public class Mastermind extends Game {

    private int present = 0 ;
    private int wellPut = 0 ;
    private int misPlaced = 0 ;

    private int resultWellPut = 0;

   private Map< Integer,Integer > occurenceC = new HashMap< Integer, Integer>();
   private Map< Integer,Integer > occurenceS = new HashMap< Integer, Integer>();
   private Map< Integer,Integer > occurenceB = new HashMap< Integer, Integer>();
   private Map< Integer,Integer > occurenceP = new HashMap< Integer, Integer>();

   private HashMap<String,Integer> mapPresent = new HashMap();
   private Map < Integer, String > mapValuePresent = new HashMap<Integer,String>();

   private  String[] orderArray;
   private int totalPresent = 0;

    public Mastermind() {
        gameName = "Mastermind";
        initMapPresent();
        generateOrderArray();
    }
    @Override
    String combinaisonResult(String secretNumber){
        return "";
    }

    @Override
    String combinaisonResult(String secretNumber,String combinaisonNumber) {
        return searchResult( secretNumber,combinaisonNumber );
    }

    private String searchResult(String secretNumber,String combinaisonNumber){
        initAttributResult();

        trialPC++;

        String[] cArray = combinaisonNumber.split("");
        String[] sArray = secretNumber.split("");

        initMapOccurence();

        accountOccurenceC( cArray );
        accountOccurenceB( cArray,sArray );
        accountOccurenceS( sArray );
        accountOccurenceP();

        accountWellPut();

        misPlaced = present - wellPut ;

        return resultTotal();
    }
    @Override
    String generateSecretRandomString() {
        String generate = "";
        int randNumber = 0;

        for (int i = 0; i < getNumberCasePossible(); i++ ){
            randNumber = generateRandom( getBaseNumberPossible() );
            generate += toStr( randNumber );
        }
        return generate;
    }




    @Override
    String generateCombinaisonRandomString() {
        return generateCombinaisonEqual( orderArray[ 0 ]  );
    }

    private void initMapPresent(){
        for (int i = 0 ; i < getNumberCasePossible(); i++ )
            mapValuePresent.put( i , getValueNoPresent() );
    }

    private void generateOrderArray(){

        String order = generateOrder( getBaseNumberPossible() );
        orderArray = order.split("");
    }

    private void generateCombinaisonFullEqual(String number ) {
        combinaisonNumberPC = generateCombinaisonEqual( number );
        mapPresent.put( number, present );
        totalPresent += present;
    }

    void generateCombinaisonPresent(){
        /*
            exemple en pratique
            "0" => 2
            "3" => 1
            "6" => 1
        */
        for ( Map.Entry<String,Integer > entry : mapPresent.entrySet() ) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if( value != 0 ) {
               String order = generateOrder( getNumberCasePossible() );
               String[] orderP = order.split("");

                int beforeBp = 0;
                int t = 0;

                for (String o : orderP ) {
                    if ( mapValuePresent.get( toInt( o ) ).equals( getValueNoPresent()) ) {
                        mapValuePresent.replace( toInt(o), key);
                        String combinaisonTest = concatMap( mapValuePresent );
                    }
                }
            }


        /*
                        if ( wellPut == 1 )
                            mapValuePresent.replace(toInt(o), getValueNoPresent() );
                        else{
                            beforeBp++;
                            resultWellPut++;


                            if( beforeBp == value )
                                break;
                        }

         */

        }
    }

    @Override
    void generateCombinaisonPC() {
        boolean isTrieOk = false;

        if( totalPresent != getNumberCasePossible() )
            generateCombinaisonFullEqual( orderArray[ trialPC ]  );
        else
            if (!isTrieOk ) {// on fait le trie une seule fois
                mapPresent =  triAvecValeur( mapPresent );
                isTrieOk = true;
            }

            generateCombinaisonPresent();
    }






    @Override
    boolean isCombinaisonTrouve(String result) {
        return ( wellPut == getNumberCasePossible() );
    }



/** Méthodes spécifiques à Mastermind **/

    private void initAttributResult(){
        present = 0;
        wellPut = 0 ;
        misPlaced = 0 ;
    }

    private void initMapOccurence(){
        for(int i = 0 ; i < getBaseNumberPossible(); i++){
            occurenceC.put( i  , 0 );
            occurenceS.put( i  , 0 );
            occurenceB.put( i  , 0 );
            occurenceP.put( i  , 0 );
        }
    }

    private void accountOccurenceC( String[] strArray ){
        for (String  c  : strArray )
            occurenceC.replace( toInt( c ), occurenceC.get( toInt( c ) ) + 1);
    }

    private void accountOccurenceB(String[] strArray1, String[] strArray2 ){
        int n = 0;
        for (String s : strArray2 )
            if ( s.equals( strArray1[ n++ ] ) )
                occurenceB.replace( toInt(s), occurenceB.get( toInt( s ) ) + 1 );
    }

    private void accountOccurenceS(String[] strArray ){
        for (String s : strArray )
            occurenceS.replace( toInt(s), occurenceS.get( toInt( s ) ) + 1 );
    }

    private void accountOccurenceP(){
        for(int i = 0; i < getBaseNumberPossible(); i++ ){
            if( occurenceS.get( i )  < occurenceC.get( i ) ) {
                occurenceP.replace(i, occurenceS.get( i ) );
                present += occurenceS.get( i );
            } else {
                occurenceP.replace( i, occurenceC.get( i ) );
                present += occurenceC.get( i );
            }
        }
    }

    private void accountWellPut(){
        for(int i = 0; i <  getBaseNumberPossible(); i++ )
            if( occurenceB.get( i ) >=  occurenceP.get( i ) )
                wellPut += occurenceP.get( i );
            else
                wellPut += occurenceB.get( i );
    }

    private String resultPresent(){
        if ( present == 1)
            return present + " présent :";
        else if ( present > 1)
            return present + " présents :";
        else return "";
    }

    private String resultWellPut(){
        if( wellPut == 1)
            return  " dont " + wellPut + " bien placé";
        else if( wellPut > 1)
            return  " dont " + wellPut + " bien placés";
        else return "";
    }

    private String resultMisPlaced(){
        if( misPlaced == 1)
            return  " dont " + misPlaced + " mal placé";
        else if( misPlaced > 1)
            return  " dont " + misPlaced + " mal placés";
        else return "";
    }

    private String resultTotal(){
        String postResult = "";
        postResult += resultPresent();
        postResult += resultWellPut();
        postResult += resultMisPlaced();

        if( present == 0 )
            return "Aucun présent";
        else
            return postResult ;
    }


    private static String concatMap(  Map <Integer,String>  mapEntry ){
        String map = "";
        for ( Map.Entry<Integer,String > entry : mapEntry.entrySet() )
            map += entry.getValue();

        return map;
    }

    private static String generateCombinaisonEqual(String figure){
        String combinaison="";
        for (int i = 0 ; i < getNumberCasePossible() ; i++ )
            combinaison += figure;

        return combinaison;
    }

    private static String generateOrder( int limit){
        String order = "";
        for( int i = 1 ; i <= limit ; i++ )
            do{
                int rand = generateRandom( limit );
                if( !order.contains( toStr( rand ) ) )
                    order += toStr( rand );

            }while( order.length() < i  );

        return order ;
    }















}
