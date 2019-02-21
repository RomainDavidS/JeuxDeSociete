package enedis.romaindavid.com.game;

import java.util.HashMap;
import java.util.Map;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;

/**
 *  Classes of the "Mastermind" game :
 *     - method generateSecretRandomString : generate the secret combination of the computer *
 *     - method generateCombinaisonRandomString : generate an automatic combination of the computer
 *     - method generateCombinaisonPC : generate a combination for the computer based on the result of the previous combination
 *     - method combinaisonResult : result of entering a combination made by the computer
 *     - method combinaisonResult : result of entering a combination made by the player
 *     - method isCombinaisonTrouve : we check if the combination was found either by the computer or by the player
 *
 *   Informations :
 *   Methods to generate the combination of the computer : *
 *   - Step 1 ( method generateCombinaisonFullEqual  ):
 *      We search in a random order the presence of the figures of the secret combination
 *      by generating a combination with identical values. For example
 *      to check the presence of "0" we will generate for 4 cases possible  "0000" *
 *   - Step 2 ( method generateCombinaisonPresent ) :
 *      After finding all the presents we will generate a combination to check
 *      their place in the combination with the number 9 which will never be present.
 *      We will start with the numbers that are most present.
 *      The order of positioning in the combination is done all the way
 *
 */
public class Mastermind extends Game {

    private int present = 0 ;
    private int wellPut = 0 ;
    private int misPlaced = 0 ;

    boolean isTrieOk = false;

    private Map< Integer,Integer > occurenceC = new HashMap< Integer, Integer>();
    private Map< Integer,Integer > occurenceS = new HashMap< Integer, Integer>();
    private Map< Integer,Integer > occurenceB = new HashMap< Integer, Integer>();
    private Map< Integer,Integer > occurenceP = new HashMap< Integer, Integer>();

    private HashMap<String,Integer> mapPresent = new HashMap();
    private String key;
    private Integer value;

    private Map < Integer, String > mapValuePresent = new HashMap<Integer,String>();

    private Map <String, String> mapValueMP = new HashMap<String,String>();

    private  String[] orderArray;
    private int totalPresent = 0;

    public Mastermind() {
        gameName = "Mastermind";
        choiceGame = 2;
        initMapPresent();
        generateOrderArray();
    }

    /**
     * The secret combination of the computer is generated for the "Challenger" and "Duel" modes     *
     * @return The secret combination of the computer
     */
    @Override
    protected String generateSecretRandomString() {
        String generate = "";
        int randNumber = 0;

        for (int i = 0; i < getNumberCasePossible(); i++ ){
            randNumber = generateRandom( getBaseNumberPossible() );
            generate += toStr( randNumber );
        }
        return generate;
    }

    /**
     * The first combination of the computer is generated for the "Defeuser" and "Duel" modes
     * @return The combination of the computer
     */
    @Override
    protected String generateCombinaisonRandomString() {
        return generateCombinaisonEqual( orderArray[ 0 ]  );
    }

    /**
     * The combination of the computer is generated based on the result of the previous combination for the "Defender" and "Duel" modes
     */
    @Override
    protected void generateCombinaisonPC() {

        if( totalPresent != getNumberCasePossible() )
            generateCombinaisonFullEqual( orderArray[ trialPC - 1 ]  );
        else {
            if (!isTrieOk) {
                mapPresent = triAvecValeur(mapPresent);
                isTrieOk = true;
                for ( Map.Entry<String,Integer > entry : mapPresent.entrySet() )
                    mapValueMP.put( entry.getKey(), "" );
            }
            generateCombinaisonPresent();
        }
    }

    /**
     * We check and display the result after entering the combination made by the computer
     * @param secretNumber player's secret number
     * @returnresult after entering the combination
     */
    @Override
    protected String combinaisonResult(String secretNumber){
        generateCombinaisonPC();
        return pcResult;
    }

    /**
     * We check and display the result after entering the combination made by the player
     * @param secretNumber computer's secret number
     * @param combinaisonNumber player combination
     * @return result after entering the combination
     */
    @Override
    protected String combinaisonResult(String secretNumber,String combinaisonNumber) {
        return searchResult( secretNumber,combinaisonNumber );
    }

    /**
     * we check if the combination was found either by the computer or by the player
     * @param result always = "" for the game of Mastermind
     * @return true if the combination was found otherwise false
     */
    @Override
    protected boolean isCombinaisonTrouve(String result) {
        return ( wellPut == getNumberCasePossible() );
    }

    /**
     * we initialize "mapValuePresent" with all possible values for each digit of the combination that will have to enter the computer
     */
    private void initMapPresent(){
        for (int i = 0 ; i < getNumberCasePossible(); i++ )
            mapValuePresent.put( i , getValueNoPresent() );
    }

    /**
     * Step 1 : we generate a combination to verify that it is present
     */
    private void generateCombinaisonFullEqual(String number ) {
        combinaisonNumberPC = generateCombinaisonEqual( number );
        pcResult = searchResult( secretNumberPlayer, combinaisonNumberPC);
        if(present != 0 )
            mapPresent.put( number, present );
        totalPresent += present;
    }

    /**
     * Step 2 : we generate a combination based on the numbers in the combination to find their place
     */
    private void generateCombinaisonPresent(){
        key = "";
        value = 0 ;

        for ( Map.Entry<String,Integer > entry : mapPresent.entrySet() ) {
            key = entry.getKey();
            value = entry.getValue();

            String order = generateOrder( getNumberCasePossible() );
            String[] orderP = order.split("");

            for (String o :orderP )
                if (!mapValueMP.get(key).contains(o))
                    if ( mapValuePresent.get( toInt( o ) ).equals( getValueNoPresent() ) ) {
                        pcSearchResult( o );
                        controlerPresent( o );
                        break;
                    }
            break;
        }
        updateMapPresent();
    }

    /**
     * we generate a computer combination and check the result
     * @param o value that will serve to generate the combination
     */
    private void pcSearchResult( String o ){
        mapValuePresent.replace( toInt( o ), key);
        combinaisonNumberPC = concatMap( mapValuePresent );
        pcResult = searchResult(secretNumberPlayer, combinaisonNumberPC);
    }

    /**
     * we check the presence @param o in the combination. And in function we capitalize it in "mapValuePresent"
     * @param o value that was used to generate the combination
     */
    private void controlerPresent( String o ){
        if ( misPlaced == 1) {
            mapValueMP.replace(key, mapValueMP.get( key ) + o);
            mapValuePresent.replace( toInt( o ), getValueNoPresent());
        } else {
            value--;
            mapPresent.replace( key, value );
        }
    }

    /**
     * empty in "mapValuePresent" the values that were in their respective place in the combination
     */
    private void updateMapPresent(){
        if ( value == 0 )
            mapPresent.remove( key );
    }

    /**
     * we generate a random order to find the digits present in the combination and for the position of the digits to look for in the combination
     */
    private void generateOrderArray(){
        String order = generateOrder( getBaseNumberPossible() );
        orderArray = order.split("");
    }

    /**
     * We display the result by comparing the secret number and the combination that has been entered
     * @param secretNumber secret combination
     * @param combinaisonNumber combination seizure
     * @return We show the presence figures, how many are well placed and how many are misplaced in the combination @param combinaisonNumber
     */
    private String searchResult(String secretNumber,String combinaisonNumber){
        initAttributResult();
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

    /**
     * we initialize the attributes present, wellPut and misPlaced
     */
    private void initAttributResult(){
        present = 0;
        wellPut = 0 ;
        misPlaced = 0 ;
    }

    /**
     * we initialize the attributes :
     *  - occurrenceC occurrence of each digit in the combination entered
     *  - occurrenceS occurrence of each digit in the combination secret
     *  - occurrenceB occurrence of each digit well placed in the secret combination
     *  - occurrenceP occurrence of each digit present in the secret combination
     */
    private void initMapOccurence(){
        for(int i = 0 ; i < getBaseNumberPossible(); i++){
            occurenceC.put( i  , 0 );
            occurenceS.put( i  , 0 );
            occurenceB.put( i  , 0 );
            occurenceP.put( i  , 0 );
        }
    }

    /**
     * update occurrence of each digit in the combination entered
     * @param strArray array of combinaisonNumber
     */
    private void accountOccurenceC( String[] strArray ){
        for (String  c  : strArray ) {
            if( occurenceC.containsKey( toInt(c) ) )
                occurenceC.replace(toInt(c), occurenceC.get(toInt(c)) + 1);
        }
    }

    /**
     * update occurrence of each digit well placed in the secret combination
     * @param strArray1 array of combinaisonNumber
     * @param strArray2 array of secretNumber
     */
    private void accountOccurenceB(String[] strArray1, String[] strArray2 ){
        int n = 0;
        for (String s : strArray2 )
            if ( s.equals( strArray1[ n++ ] ) )
                occurenceB.replace( toInt(s), occurenceB.get( toInt( s ) ) + 1 );
    }

    /**
     * update occurrence of each digit in the combination secret
     * @param strArray array of secretNumber
     */
    private void accountOccurenceS(String[] strArray ){
        for (String s : strArray )
            occurenceS.replace( toInt(s), occurenceS.get( toInt( s ) ) + 1 );
    }

    /**
     * update occurrence of each digit present in the secret combination
     */
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

    /**
     * update occurence wellPut
     */
    private void accountWellPut(){
        for(int i = 0; i <  getBaseNumberPossible(); i++ )
            if( occurenceB.get( i ) >=  occurenceP.get( i ) )
                wellPut += occurenceP.get( i );
            else
                wellPut += occurenceB.get( i );
    }

    /**
     *  the result of the presents
     * @return post the result of the presents
     */
    private String resultPresent(){
        if ( present == 1)
            return present + " présent :";
        else if ( present > 1)
            return present + " présents :";
        else return "";
    }

    /**
     * the result of wellPut
     * @return post the result of the wellPut
     */
    private String resultWellPut(){
        if( wellPut == 1)
            return  " dont " + wellPut + " bien placé";
        else if( wellPut > 1)
            return  " dont " + wellPut + " bien placés";
        else return "";
    }

    /**
     * the result misPlaced
     * @return post the result of the misPlaced
     */
    private String resultMisPlaced(){
        if( misPlaced == 1)
            return  " dont " + misPlaced + " mal placé";
        else if( misPlaced > 1)
            return  " dont " + misPlaced + " mal placés";
        else return "";
    }

    /**
     * the results
     * @return post global result resultPresent, resultWellPut and resultMisPlaced
     */
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

    /**
     * concatenating a String hashmap
     * @param mapEntry hashmap to concatenate
     * @return concatenation of the hasmap
     */
    private static String concatMap(  Map <Integer,String>  mapEntry ){
        String map = "";
        for ( Map.Entry<Integer,String > entry : mapEntry.entrySet() )
            map += entry.getValue();

        return map;
    }

    /**
     * we generate a combination with an identical value
     * @param figure value that will allow to generate the combination
     * @return generated combination
     */
    private static String generateCombinaisonEqual(String figure){
        String combinaison="";
        for (int i = 0 ; i < getNumberCasePossible() ; i++ )
            combinaison += figure;

        return combinaison;
    }

    /**
     * generate a random order combination
     * @param limit valeur limite permetant la génération aléatoire
     * @return generated combination
     */
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
