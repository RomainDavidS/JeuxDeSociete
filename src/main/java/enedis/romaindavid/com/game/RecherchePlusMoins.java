package enedis.romaindavid.com.game;


import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;

/**
 *  Classes of the "Recherche +/-" game :
 *     - method generateSecretRandomString : generate the secret combination of the computer
 *     - method generateCombinaisonRandomString : generate an automatic combination of the computer
 *     - method generateCombinaisonPC : generate a combination for the computer based on the result of the previous combination
 *     - method combinaisonResult : result of entering a combination made by the computer
 *     - method combinaisonResult : result of entering a combination made by the player
 *     - method isCombinaisonTrouve : we check if the combination was found either by the computer or by the player
 */
public class RecherchePlusMoins  extends Game {

    public RecherchePlusMoins() {
        gameName = "Recherche +/-" ;
        choiceGame = 1;
        initMapPossible();

    }

    /**
     * The secret combination of the computer is generated for the "Challenger" and "Duel" modes     *
     * @return The secret combination of the computer
     */
    @Override
    protected String generateSecretRandomString(){
        int randNumber = generateRandom( puissanceDe10( getNumberCasePossible() )) ;
        return formatNumber( randNumber );
    }

    /**
     * The first combination of the computer is generated for the "Defeuser" and "Duel" modes
     * @return The combination of the computer
     */
    @Override
    protected String generateCombinaisonRandomString() {
        return generateSecretRandomString();
    }

    /**
     * The combination of the computer is generated based on the result of the previous combination for the "Defender" and "Duel" modes
     */
    @Override
    protected void generateCombinaisonPC(){
        String newCombinaisonPC = "";

        String[] combinaisonNumberPcArray = combinaisonNumberPC.split("");

        String[] resultArray = pcResult.split("");

        for(int i = 0; i < getNumberCasePossible(); i++)
            newCombinaisonPC += newPcCombinaison( i, combinaisonNumberPcArray[ i ], resultArray[ i ]  );

        combinaisonNumberPC = newCombinaisonPC ;
    }

    /**
     * We check and display the result after entering the combination made by the computer
     * @param secretNumber player's secret number
     * @returnresult after entering the combination
     */
    @Override
    protected String combinaisonResult(String secretNumber){
        if(trialPC == 1 )
            combinaisonNumberPC = generateCombinaisonRandomString() ;
        else
            generateCombinaisonPC();

        return combinaisonResult( secretNumber,combinaisonNumberPC);
    }

    /**
     * We check and display the result after entering the combination made by the player
     * @param secretNumber computer's secret number
     * @param combinaisonNumber player combination
     * @return result after entering the combination
     */
    @Override
    protected String combinaisonResult( String secretNumber,String combinaisonNumber ){

        String[] combinaisonSecretArray = secretNumber.split("");
        String[] combinaisonSeizureArray = combinaisonNumber.split("");

        int lenArray = combinaisonSeizureArray.length - 1;

        String resultCombinaison = "" ;

        for(int i = 0 ; i<= lenArray;i++)
            resultCombinaison += resultOne( toInt( combinaisonSeizureArray[ i ] ),toInt( combinaisonSecretArray[ i ] ) );

        return resultCombinaison;
    }

    /**
     * we check if the combination was found either by the computer or by the player
     * @param result result of the entered combination
     * @return true if the combination was found otherwise false
     */
    @Override
    protected boolean isCombinaisonTrouve(String result ){
        boolean trouve = true;
        String[] resultCombinaisonArray = result.split("");
        for (String value : resultCombinaisonArray )
            if( !value.equals( "=" ) )
                return false;

        return trouve ;
    }

    /**
     * we initialize "mapPossible" with all possible values for each digit of the combination that will have to enter the computer
     */
    private void initMapPossible(){
        for ( int i = 0; i < getNumberCasePossible() ; i++ )
            mapPossible.put( i, "0123456789");
    }

    /**
     * we display the result for a specific character of the entered combination
     * @param combinaisonSeizure character seizing
     * @param combinaisonSecret secret character
     * @return "=" if combinationSeizure equals combinationSecret, "+" if combinationSeizure is superior combinationSecret or "-" if combinationSeizure is lower combinationSecret
     */
    private static String resultOne( int combinaisonSeizure, int combinaisonSecret ){
        if( combinaisonSeizure > combinaisonSecret )
            return "+";
        else if ( combinaisonSeizure < combinaisonSecret )
            return "-";
        else return "=";
    }

    /**
     * generates a number based on the result of the previous combination
     * @param index position of the digit in the combination of the computer
     * @param combinaisonNumber 60/5000
     * digit that was entered in the computer combination
     * @param result result of a digit that was entered in the computer combination "=" or "+" or "-"
     * @return new number (0 to 9) to place in the combination of pc
     */
    private String newPcCombinaison(int index,String combinaisonNumber , String result  ){

        String possible = mapPossible.get( index );
        String[] possibleArray = possible.split("");
        if (possible.length() == 1)
            return mapPossible.get( index );

        String newCombinaison  = generateCombinaison( result,  possibleArray,toInt( combinaisonNumber ) );
        String[] newCombinaisonArray = newCombinaison.split("");

        mapPossible.replace( index, newCombinaison );
        int len = newCombinaisonArray.length ;

        if( len == 1)
            return newCombinaison;
        else{
            int newR = generateRandomInRange(toInt( newCombinaisonArray[0] ),toInt( newCombinaisonArray[ len -1 ] ));
            return toStr( newR );
        }
    }

    /**
     * generates the new possible digits of the "mapPossible" according to the result obtained after entering the combination
     * @param result result of a specific figure of the combination
     * @param possibleArray list of possibilities
     * @param intCombinaisonNumber specific figure of the combination
     * @return new possibilities
     */
    private String generateCombinaison(  String result, String[] possibleArray, int intCombinaisonNumber ){

        String combinaison = "";

        for (String str: possibleArray ) {
            int intStr = toInt( str );
            if( result.equals( "+" ) )
                if( intStr < intCombinaisonNumber )
                    combinaison += str;

            if ( result.equals( "-" ) )
                if( intStr > intCombinaisonNumber )
                    combinaison += str;

            if ( result.equals("=") )
                if (intStr == intCombinaisonNumber) {
                    combinaison = str;
                    break;
                }
        }
        return combinaison;
    }
















}
